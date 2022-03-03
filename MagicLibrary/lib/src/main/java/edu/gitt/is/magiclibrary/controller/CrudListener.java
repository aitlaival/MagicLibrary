/**
 * Clase para los aspectos comunes a todos los listeners para operaciones CRUD
 * 
 */
package edu.gitt.is.magiclibrary.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.*;


import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.gitt.is.magiclibrary.model.Dao;

import edu.gitt.is.magiclibrary.view.*;



/**
 * <p>Clase gen�rica para los controladores que atienden a la vista de cualquier tipo de entidad</p>
 * <p>Implementar� las interfaces {@link java.awt.event.ActionListener} y {@link javax.swing.event.ListSelectionListener}</p>
 * <p>Participa en el patr�n observador como observador</p>
 * @author Isabel Rom�n
 *
 */
public abstract class CrudListener<T> implements ActionListener, ListSelectionListener{
	private static Logger log=Logger.getLogger(CrudListener.class.getName());
	/**
	 * <p>Vista concreta con la que se asociar� este controlador. Participa en el patr�n observador como observado</p>
	 */
	protected EntityDetails view;
	private CrudOperation operation;
	/**
	 * <p>Entidad manejada por el controlador</p>
	 */
	protected T entity;
	/**
	 * <p>Objeto de acceso a datos, que permitir� realizar las acciones CRUD para la entidad manejada</p>
	 */
	protected Dao entityDao;
   
/**
 * <p>M�todo correspondiente a la interfaz ActionListener, responde a los eventos lanzados por la vista de entidad</p>
 * {@link java.awt.event.ActionListener}
 * @param e {@link java.awt.event.ActionEvent}   
 */
	public void actionPerformed(ActionEvent e) {
		    log.info("Recibido evento "+e+" en el CrudListener\n");
		    entityDao = newDao();
		
		    String cmd=e.getActionCommand();
		    log.info("Action command= "+cmd);
		    switch(cmd) {
		    	case "Create":
		    		log.info("Implementar la creaci�n");
		    		operation = CrudOperation.CREATE;
		    
		    		setCreateView();
		    	  
		    		break;
		    	case "Save":
		    		log.info("Implementar guardar");
		    	
		    		save();
		    		
		    		MLView.getFrameManager().discard(view);
		    		break;
		    	case "Read":
		    		log.info("Implementar la lectura");
		    	
		    		operation=CrudOperation.READ;
		    		setSearchView();
		    		break;
		    	case "Update":
		    		log.info("Implementar la actualizaci�n");
		    	
		    		operation=CrudOperation.UPDATE;
		    		setSearchView();
		    		break;
		    	case "Delete":
		    		log.info("Implementar el borrado");
		    	
		    		operation=CrudOperation.DELETE;
		    		setSearchView();
		    		break;
		    	case "Search":
		    		
		    		log.info("Implementar la b�squeda");
		    	
		    		search();
		    			
		    		break;
		    	case "Remove":		 
		    		log.info("Eliminar definitivamente");		    	
		    		entityDao.delete(view.getEntity());
		    		MLView.getFrameManager().discard(view);
		    		break;
		    	case "Discard":
		    		log.info("Implementar Discard");
		    		MLView.getFrameManager().discard(view);  		
		    		
		    }
	        
	    }
	/**
	 * <p>M�todo correspondiente a la interfaz ListSelectionListener responde a los cambios en la selecci�n en la lista de entidades m�ltiples de la vista correspondiente</p>
	 * {@link javax.swing.event.ListSelectionListener}
	 * @param e {@link javax.swing.event.ListSelectionEvent}
	 */
	
	public void valueChanged(ListSelectionEvent e) {
		log.info("Cambia la selecci�n en la lista");
		
		view.setEntity(view.getSelectedValue());
    }

	/**
	 * <p>Crea la vista con una �nica entidad</p>
	 * <p>La dependencia del estado hace ver que deber�a utilizarse el patr�n "Estado como objeto"</p>
	 * @param entity Entidad que se quiere presentar por pantalla
	 */
	protected void setView(T entity) {
		log.info("Creando vista con una entidad");
		/**Datos del t�tulo*/
		view=newView(entity);
		switch(operation) {
		case READ:
			view.disableAllAttributes();
			view.addDiscardButton(this);
			break;
		case UPDATE:
			view.addCreateButtons(this);
			break;
		case DELETE:
			view.addDeleteButtons(this);
		}	
		MLView.getFrameManager().addCenter(view);
		
	}
	/**
	 * <p>Crea la vista con un conjunto de entidades/p>
	 * <p>La dependencia del estado hace ver que deber�a utilizarse el patr�n "Estado como objeto"</p>
	 * @param entities Lista de entidades que se quieren presentar por pantalla
	 */
	protected void setView(List<T> entities) {
		log.info("Creando vista con m�ltiples entidades");
		/**Datos del t�tulo*/
		view=newView(entities.get(0));
		view.setEntity(entities);
		switch(operation) {
		case READ:
			view.disableAllAttributes();
			view.addDiscardButton(this);
			break;
		case UPDATE:
			view.addCreateButtons(this);
			break;
		case DELETE:
			view.addDeleteButtons(this);
		}	
		view.addList(this);
		MLView.getFrameManager().addCenter(view);
		
	}
	/**
	 * Establece la vista vac�a para crear una entidad nueva
	 */
	private void setCreateView() {	
		log.info("Estableciendo vista vac�a para crear");
		MLView.getFrameManager().discard(view);
		MLView.getFrameManager().reset();
		view=newView();
		view.addCreateButtons(this);
		view.setVisible(true);
		MLView.getFrameManager().addCenter(view);
	}
	/**
	 * Establece la vista vac�a para buscar una entidad nueva, todos los campos habilitados para buscar
	 */
	protected void setSearchView() {	
		log.info("Estableciendo vista vac�a para buscar");
		MLView.getFrameManager().discard(view);
		view=newView();
		view.addSearchButtons(this);		
	
		MLView.getFrameManager().addCenter(view);
	
	}
	/**
	 * <p>Establece la vista vac�a para buscar una entidad, s�lo habilita el campo elegido para buscar, indicado en el par�metro</p>
	 * @param query Nombre del atributo que se usar� para la b�squeda
	 */
	protected void setSearchView(String query) {	
		log.info("Estableciendo vista vac�a para buscar");
		MLView.getFrameManager().discard(view);
		view=newView();
		view.addSearchButtons(this);		
		view.disableAllAttributes();
		view.enableAttribute(query);
		MLView.getFrameManager().addCenter(view);
	
	}
	/**
	 * <p>M�todo de b�squeda, espec�fico de las clases hijas</p>
	 */
	protected abstract void search();
	/**
	 * <p>M�todo para guardar, espec�fico de las clases hijas</p>
	 */
	protected abstract void save();
	/**
	 * <p>M�todo factor�a, ser�n los hijos los que decidan la clase concreta del objeto vista creado, en este caso vac�a (sin relacionar con una instancia de entidad concreta)</p> 
	 * @return una nueva vista para un tipo de entidad concreto
	 */
	protected abstract EntityDetails newView();
	/**
	 * <p>M�todo factor�a, ser�n los hijos los que decidan la clase concreta del objeto vista creado, en este caso rellena (con los datos de la entidad pasada como par�metro</p> 
	 * @return una nueva vista para un tipo de entidad concreto
	 * @param entity entidad asociada a la vista
	 */
	protected abstract EntityDetails newView(T entity);
	/**
	 * <p>M�todo factor�a, ser�n los hijos los que decidan la clase concreta del Dao asociado</p> 
	 * @return un nuevo objeto dao para manejar un tipo de entidad concreto
	 *
	 */
	protected abstract Dao newDao();
	
	
}

