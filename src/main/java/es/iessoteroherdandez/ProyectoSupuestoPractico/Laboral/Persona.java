package es.iessoteroherdandez.ProyectoSupuestoPractico.Laboral;
/**
 * Persona va a representar un objeto al cual va a extender empleado
 * @author Jesus Valle
 */
public class Persona {

	public String nombre;
	public String dni;
	public char sexo;
/**
 * Crea una persona recibiendo como parametro el nombre , el dni y el sexo
 * @param nombre
 * @param dni
 * @param sexo
 */
	public Persona(String nombre, String dni, char sexo) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.sexo = sexo;
	}
/**
 * Recibe solo el nombre y el sexo
 * @param nombre
 * @param sexo
 */
	public Persona(String nombre, char sexo) {
		super();
		this.nombre = nombre;
		this.sexo = sexo;
	}
/**
 * Permite cambiar el dni
 * @param dni recibe el dni nuevo
 */
	public void setDni(String dni) {
		this.dni = dni;
	}
/**
 * Imprime nombre y dni de la persona
 */
	public void imprime() {
		System.out.println("Nombre: " + nombre + "  DNI: " + dni);
	}
}
