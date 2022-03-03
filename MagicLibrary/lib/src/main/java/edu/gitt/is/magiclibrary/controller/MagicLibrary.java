/**
 * Controlador principal para arrancar la aplicaciï¿½n Magic Library, construye la vista principal y los controladores para cada tipo de entidad
 */
package edu.gitt.is.magiclibrary.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import edu.gitt.is.magiclibrary.view.MLView;

/**
<<<<<<< HEAD
 * <p>Clase principal de la aplicación</p>
 * @author Isabel Román
=======
 * @author Isabel Romï¿½n
>>>>>>> refs/heads/vista
 *
 */
public class MagicLibrary {
	
	private static BookListener bookListener;
	private static ItemListener itemListener;
	/**
	 * <p>Con esta operación se indica a la aplicación donde está el fichero de propiedades de trazado</p>
	 */
	
	/*
	static {
	      String path = MagicLibrary.class.getClassLoader()
	                                  .getResource("log.properties")
	                                  .getFile();
	      System.setProperty("java.util.logging.config.file", path);
	     
	}
	*/
	private static Logger log=Logger.getLogger(MagicLibrary.class.getName());
	
	/**
	 * @param args Actualmente el programa no analiza los parámetros de entrada al main
	 */
	public static void main(String[] args) {
<<<<<<< HEAD
		
		log.info("Arranco la aplicación y creo el controlador para manejar entidades desde la interfaz");
		log.log(Level.INFO, "Arranco la aplicación y creo el controlador para manejar entidades desde la interfaz");
=======


		log.info("Arranco la aplicaciï¿½n y creo el controlador para manejar entidades desde la interfaz");
>>>>>>> refs/heads/vista

		bookListener=new BookListener();
		log.finest("Acabo de crear un objeto de tipo "+BookListener.class.getName());
		MLView.getFrameManager().setEntityMenu("Book", bookListener);
		
		itemListener=new ItemListener();
		log.finest("Acabo de crear un objeto de tipo "+ItemListener.class.getName());
		MLView.getFrameManager().setEntityMenu("Item", itemListener);
		log.fine("Terminando el método main");
	}

}
