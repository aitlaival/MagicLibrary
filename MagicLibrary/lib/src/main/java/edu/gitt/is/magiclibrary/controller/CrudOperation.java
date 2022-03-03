/**
 * Cada una de las operaciones Crud
 */
package edu.gitt.is.magiclibrary.controller;

/**
 * <p>Enumeraci�n de las operaciones posibles</p>
 * @author Isabel Rom�n
 *
 */
public enum CrudOperation {
	/**
	 * Creaci�n y registro de una entidad nueva
	 */
	CREATE,
	/**
	 * Lectura de los datos registrados sobre una entidad
	 */
	READ,
	/**
	 * Actualizaci�n de los datos registrados sobre una entidad
	 */
	UPDATE,
	/**
	 * Eliminaci�n de una entidad
	 */
	DELETE
}
