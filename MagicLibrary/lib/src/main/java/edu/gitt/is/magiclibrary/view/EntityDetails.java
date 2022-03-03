/**
 * <p>Clase abstracta que contiene todo lo com�n a las vistas de entidades</p>
 * <p>Ser�a conveniente separar la creaci�n de la vista de la propia vista, usando el patr�n constructor posiblemente</p>
 */
package edu.gitt.is.magiclibrary.view;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;




/**
 * <p>Clase gen�rica para las vistas de entidad</p>
 * @author Isabel Rom�n
 *
 */
public abstract class EntityDetails<T> extends JPanel {
	/**
	 * N�mero de serie por defecto
	 */
	private static final long serialVersionUID = 1L;


	private static Logger log=Logger.getLogger(EntityDetails.class.getName());
	

	private JButton searchButton;
	private JButton saveButton;
	private JButton deleteButton;
	private JButton discardButton;
	private JList<T> list;
	/**
	 * Entidad principal asociada a la vista
	 */
    protected T entity=null;
    /**
     * Lista de entidades asociadas a la vista
     */
    protected List<T> entities=null;
	


	/**
	 * Creaci�n de la vista sin establecer la entidad
	 */
	public EntityDetails() {
		
		log.info("Creando Vista vac�a");		
		
	}
	/**
	 * <p>Creaci�n de la vista estableciendo una �nica entidad</p>
	 * @param entity La entidad correspondiente a esta vista
	 */
	public EntityDetails(T entity) {	
		log.info("Creando vista con 1 entidad");
		this.entity=entity;
	}
	/**
	 * <p>A�ade los botones de creaci�n y descarte, le asocia el listener correspondiente</p>
	 * @param listener controlador que responder� a las acciones en el bot�n de crear y descartar
	 */
	public void addCreateButtons(ActionListener listener) {
		log.info("A�adiendo los botones de Guardar y Descartar");
		saveButton = new JButton("Save");
		

		saveButton.setBounds(56, 275, 89, 23);
		saveButton.addActionListener(listener);
		add(saveButton);
		
		
		addDiscardButton(listener);
		setVisible(true);
		
	}
	/**
	 * <p>A�ade el bot�n de borrado y le asocia el listener correspondiente</p>
	 * @param listener controlador que responder� a las acciones en el bot�n de borrar
	 */
	public void addDeleteButtons(ActionListener listener) {
		log.info("A�adiendo los botones de eliminar y descartar");
		deleteButton = new JButton("Remove");
		

		deleteButton.setBounds(56, 275, 89, 23);
		deleteButton.addActionListener(listener);
		add(deleteButton);
		
		
		addDiscardButton(listener);
		
		setVisible(true);
		
	}
	/**
	 * <p>A�ade el bot�n de descartar y le asocia el listener correspondiente</p>
	 * @param listener controlador que responder� a las acciones en el bot�n de descartar
	 */
	public void addDiscardButton(ActionListener listener) {
		log.info("A�adiendo el bot�n de descartar");
			
		discardButton = new JButton("Discard");
		discardButton.addActionListener(listener);
		discardButton.setBounds(350, 275, 89, 23);
		add(discardButton);
		
		setVisible(true);
		
	}
	/**
	 * <p>A�ade los botones de buscar y descartar, le asocia el listener correspondiente</p>
	 * @param listener controlador que responder� a las acciones en el bot�n de buscar y descartar
	 */
	public void addSearchButtons(ActionListener listener) {
		
		searchButton = new JButton("Search");
		searchButton.addActionListener(listener);
		searchButton.setBounds(56, 275, 89, 23);
		add(searchButton);
		addDiscardButton(listener);
		setVisible(true);
		
	}
	/**
	 * <p>A�ade la lista para entidades m�ltiples, le asocia el listener correspondiente</p>
	 * @param listener controlador que responder� a las selecciones en la lista
	 */
	public void addList(ListSelectionListener listener) {
		list = new JList(entities.toArray());
		
		list.setName("entities");
		list.setBounds(56, 350, 400, 150);
		list.setSelectedIndex(0);
		list.addListSelectionListener(listener);
		add(list);
		setVisible(true);
	}
	/**
	 * Devuelve el valor seleccionado en la lista de entidades m�ltiples
	 * @return El valor seleccionado en la lista
	 */
	public T getSelectedValue() {
		return list.getSelectedValue();
	}

	/**
	 * <p>M�todo para establecer la entidad principal</p>
	 * @param entity entidad correspondiente a esta vista
	 */
	public void setEntity(T entity) {
		this.entity=entity;
		}
	/**
	 * <p>M�todo par establecer la entidad principal</p>
	 * @param entities lista de entidades correspondientes a esta vista
	 */
	public void setEntity(List<T> entities) {
		this.entities=entities;
		}
	
	/**
	 * 
	 * @return Devuelve la entidad correspondiente
	 */
	
	public T getEntity() {
		
		return entity;
	}
	
	/**
	 * Deshabilita la entrada de todos los campos de texto
	 */
	public void disableAllAttributes() {
		Component[] components = this.getComponents();
		for (Component c : components){
			if((c.getClass()==JTextField.class)||(c.getClass()==JFormattedTextField.class)) {
				log.info("Encontrado campo de texto con nombre "+c.getName());
				((JTextField) c).setEditable(false);
			} 
		}
	}
	/**
	 * Habilita la entrada de todos los campos de texto
	 */
	public void enableAllAttributes() {
		Component[] components = this.getComponents();
		for (Component c : components){
			if((c.getClass()==JTextField.class)||(c.getClass()==JFormattedTextField.class)) {
				((JTextField) c).setEditable(true);
			}
		}
	}
	/**
	 * Deshabilita la entrada para uno de los atributos
	 * @param name Atributo para el que quiero deshabilitar la edici�n
	 */
	public void disableAttribute(String name) {

	
		Component[] components = this.getComponents();
		for (Component c : components){
			if(c.getName()==name) {
				((JTextField) c).setEditable(false);
			}
		}
	}
	/**
	 * Habilita la entrada de un campo de texto espec�fico
	 * @param name Atributo para el que quiero habilitar la edici�n
	 */
	public void enableAttribute(String name) {
		
		Component[] components = this.getComponents();
		for (Component c : components){
			if(c.getName()==name) {
				((JTextField) c).setEditable(true);
			}
		}
	}
	/**
	 * Los campos de texto en la vista siempre tendr�n el mismo nombre que el atributo correspondiente de la entidad
	 * @param name El nombre del atributo que queremos recuperar
	 * @return el valor del campo correspondiente como String
	 */
	public String getAttributeAsString(String name) {
		String value="";
		
		log.info("Buscando valor del campo "+name);
		Component[] components = this.getComponents();
		for (Component c : components){
		
			if(c.getName()==name) {
				value=((JTextField) c).getText();
			}
		}
		return value;
	}

	

}

