/**
 * 
 */
package edu.gitt.is.magiclibrary.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.text.DateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.gitt.is.magiclibrary.controller.CrudListener;

/**
 * @author Isabel  Rom�n
 * <p>Interfaz para la simplificaci�n de la gesti�n y composici�n de un JFrame</p>
 *
 */
public interface FrameManagerI {
	/**
	 * A�ade a la vista principal un JFrame arriba
	 * @param frame JFrame a a�adir
	 */
	  public void addNorth(JFrame frame);
	  /**
	   * A�ade a la vista principal un JFrame abajo
	   * @param frame JFrame a a�adir
	   */
	  public void addSouth(JFrame frame);
	  /**
	   * A�ade a la vista principal un JPanel abajo
	   * @param panel JPanel a a�adir
	   */
	  public void addSouth(JPanel panel);
	  /**
		 * A�ade a la vista principal un JFrame en el centro
		 * @param frame JFrame a a�adir
		 */
	  public void addCenter(JFrame frame);
	  /**
	   * A�ade a la vista principal un JPanel en el centro
	   * @param panel JPanel a a�adir
	   */
	  public void addCenter(JPanel panel);
	  /**
	   * Limpia la vista principal y elimina un panel espec�fico
	   * @param panel panel que se desea eliminar
	   */
	  public void discard(JPanel panel);
	  /**
	   * Limpia la vista principal de todos los paneles que tenga para a�adir uno nuevo
	   * 
	   */
	 
	  public void reset();
	  /**
	   * A�ade un men� tipo CRUD para una entidad concreta y le asocia un controlador CRUD particular
	   * @param entityName Nombre de la entidad a manejar
	   * @param listener Controlador que se le va a asociar al men�
	   */
	  public void setEntityMenu(String entityName, CrudListener listener);
	  /**
	   * Busca un componente por su nombre
	   * @param componentName El nombre del componente buscado
	   * @return El componente que corresponde al nombre
	   */
	  public Component getComponentByName(String componentName);
	  /**
	   * Devuelve el formato usado para la fecha
	   * @return Formato de fecha
	   */
	  public DateFormat getDateFormat();
	
}
