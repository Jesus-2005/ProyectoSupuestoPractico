package es.iessoteroherdandez.ProyectoSupuestoPractico.Laboral;

public class Nomina {
/**
 * Esto sera el dueldo base segun la ctegoria
 */
	private static final int SUELDO_BASE[] = { 5000, 7000, 9000, 11000, 13000, 15000, 17000, 19000, 21000, 23000 };
/**
 * Con este metodo calcularemos el suedo del empleado que le pacemos como parametro
 * @param e
 * @return
 */
	public int sueldo(Empleado e) {
		int sueldo = SUELDO_BASE[e.getCategoria()] + 5000 * e.anyos;
		return sueldo;
	}

}
