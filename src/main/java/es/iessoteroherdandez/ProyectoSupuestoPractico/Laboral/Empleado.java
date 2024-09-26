package es.iessoteroherdandez.ProyectoSupuestoPractico.Laboral;

public class Empleado extends Persona {

	private int categoria;
	public int anyos;
/**
 * Crea un empleado recibiendo como parametro nombre, dni, sexo, categoria, anyos
 * @param nombre
 * @param dni
 * @param sexo
 * @param categoria
 * @param anyos
 * @throws DatosNoCorrectosException
 */
	public Empleado(String nombre, String dni, char sexo, int categoria, int anyos) throws DatosNoCorrectosException {
		super(nombre, dni, sexo);

		if (categoria > 0 && categoria <= 10) {
			this.categoria = categoria;
		} else {
			throw new DatosNoCorrectosException("Datos no correctos");
		}

		if (anyos >= 0) {
			this.anyos = anyos;
		}

	}
/**
 * Crea un empleado recibiendo como parametro nombre, dni y sexo
 * @param nombre
 * @param dni
 * @param sexo
 */
	public Empleado(String nombre, String dni, char sexo) {
		super(nombre, dni, sexo);
		this.categoria = 1;
		this.anyos = 0;
		// TODO Auto-generated constructor stub
	}
/**
 * Este metodo retorna  categoria
 * @return
 */
	public int getCategoria() {
		return categoria;
	}
/**
 * Con este metodo cambiaremos la categoria del empleado
 * @param categoria
 * @throws DatosNoCorrectosException
 */
	public void setCategoria(int categoria) throws DatosNoCorrectosException {
		if (categoria > 0 && categoria <= 10) {
			this.categoria = categoria;
		} else {
			throw new DatosNoCorrectosException("Datos no correctos");
		}
	}
/**
 * Este metodo incrementa el año del empleado
 */
	public void incrAnyo() {
		this.anyos++;
	}

/**
 * Con este metodo imprimiremos los datos de empleado
 */
	public void imprime() {
		System.out.println("Nombre: " + nombre + "  DNI: " + dni + "  Categoria: " + categoria + "  Años: " + anyos);

	}
}
