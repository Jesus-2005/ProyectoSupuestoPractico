package es.iessoteroherdandez.ProyectoSupuestoPractico.Laboral;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class CalculaNomina {
	static Nomina n = new Nomina();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();
		String txtEmpleados = "../ProyectoSupuestoPractico_parte2/empledos.txt";
		String datSalarios = "../ProyectoSupuestoPractico_parte2/salarios.dat";
		final String SQL = "SELECT Nombre,Dni,Sexo,Categoria,Anyos FROM empleado";
//para realizar lo siguiente debera realizar lo comentado en el medio del punto 2 para que se creen los sueldos
		Scanner scanner = new Scanner(System.in);
		int opcion;

		do {
			System.out.println("----- Menú -----");
			System.out.println("1. Informacion empleados");
			System.out.println("2. Informacion salario");
			System.out.println("3. Modificacion de datos");
			System.out.println("4. Actualizacion del sueldo de un empleado");
			System.out.println("5. Actualizacion del sueldo de todos los empleados");
			System.out.println("6. Hacer una copia de seguridad de la base datos");
			System.out.println("0. Salir");
			System.out.print("Selecciona una opción: ");
			opcion = scanner.nextInt();
			scanner.nextLine();
			switch (opcion) {
			case 1:
				System.out.println("Has seleccionado Opción 1.");

				try (Connection conn = DBUtils.getConnection();
						Statement st = conn.createStatement();
						ResultSet rs = st.executeQuery(SQL);) {

					while (rs.next()) {
						String nombre = rs.getString("Nombre");
						String dni = rs.getString("Dni");
						char sexo = rs.getString("Sexo").charAt(0);
						int categoria = rs.getInt("Categoria");
						int anyos = rs.getInt("Anyos");

						System.out.println("Nombre: " + nombre + "  DNI: " + dni + " Sexo: " + sexo + "  Categoria: "
								+ categoria + "  Años: " + anyos + "/n");

					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("Has seleccionado Opción 2.");
				System.out.print("Escribe el dni: ");
				String dniScanner = scanner.nextLine();
				System.out.println(dniScanner);
				String salarioSelect = "SELECT sueldo FROM nomina where Dni = '" + dniScanner + "';";
				try (Connection conn = DBUtils.getConnection();
						Statement st = conn.createStatement();
						ResultSet rs = st.executeQuery(salarioSelect);) {

					while(rs.next()) {
						int sueldo = rs.getInt("sueldo");
						System.out.println(" Sueldo: " + sueldo);
						
					}
					
					

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				System.out.println("Has seleccionado Opción 3.");
				do {
					System.out.println("----- Modificacion de datos -----");
					System.out.println("1. Nombre");
					System.out.println("2. Dni");
					System.out.println("3. Sexo");
					System.out.println("4. Categoria");
					System.out.println("5. Años");
					System.out.println("0. Salir");
					System.out.print("Selecciona una opción: ");
					opcion = scanner.nextInt();
					scanner.nextLine();
					
					switch (opcion) {
					case 1:
						System.out.println("Has seleccionado Opción 1.");
						System.out.print("Dime tu dni: ");
						 dniScanner = scanner.nextLine();
						 System.out.print("Introduzca el nuevo nombre: ");
						 String nombreScanner = scanner.nextLine();
						 
						 try (Connection conn = DBUtils.getConnection();
								Statement st = conn.createStatement();) {
							 st.executeUpdate("UPDATE empleado SET Nombre = '"+nombreScanner+"' WHERE Dni ='"+dniScanner+"';");
							} catch (SQLException e) {
					
							} 
						 
						break;
					case 2:
						System.out.println("Has seleccionado Opción 2.");
						System.out.print("Dime tu dni: ");
						 dniScanner = scanner.nextLine();
						 System.out.print("Introduzca el nuevo dni: ");
						 String nuevoDni = scanner.nextLine();
						 
						 try (Connection conn = DBUtils.getConnection();
								Statement st = conn.createStatement();) {
							 st.executeUpdate("UPDATE empleado SET Dni = '"+nuevoDni+"' WHERE Dni ='"+dniScanner+"';");
							 st.executeUpdate("UPDATE nomina SET Dni = '"+nuevoDni+"' WHERE Dni ='"+dniScanner+"';");
							} catch (SQLException e) {
					
							} 
						break;
					case 3:
						System.out.println("Has seleccionado Opción 3.");
						System.out.print("Dime tu dni: ");
						 dniScanner = scanner.nextLine();
						 System.out.print("Introduzca Tu sexo: ");
						 String sexoScanner = scanner.nextLine();
						 char sexoCara = sexoScanner.charAt(0); 
					            
						 
						 try (Connection conn = DBUtils.getConnection();
								Statement st = conn.createStatement();) {
							 st.executeUpdate("UPDATE empleado SET Sexo = '"+sexoCara+"' WHERE Dni ='"+dniScanner+"';");
							} catch (SQLException e) {
					
							} 
						 
						break;
					case 4:
						System.out.println("Has seleccionado Opción 3.");
						System.out.print("Dime tu dni: ");
						 dniScanner = scanner.nextLine();
						 System.out.print("Introduzca Tu Categoria: ");
						 int categoriaScanner = scanner.nextInt();
					            
						 String selectSQL = "SELECT Nombre,Dni,Sexo,Categoria,Anyos FROM empleado where dni = '"+dniScanner+"';";
						 try (Connection conn = DBUtils.getConnection();
								Statement st = conn.createStatement();) {
							 st.executeUpdate("UPDATE empleado SET Categoria = '"+categoriaScanner+"' WHERE Dni ='"+dniScanner+"';");
							 ResultSet rs = st.executeQuery(selectSQL);
							 while (rs.next()) {
									Empleado empleado;
									String nombre = rs.getString("Nombre");
									String dni = rs.getString("Dni");
									char sexo = rs.getString("Sexo").charAt(0);
									int categoria = rs.getInt("Categoria");
									int anyo = rs.getInt("Anyos");
									empleado = new Empleado(nombre, dni, sexo, categoria, anyo);
									st.executeUpdate("UPDATE nomina SET sueldo = " + n.sueldo(empleado) + " WHERE Dni ='" + empleado.dni + "';");
					
								}
							 
							} catch (SQLException e) {
					
							} catch (DatosNoCorrectosException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						break;
					case 5:
						System.out.println("Has seleccionado Opción 3.");
						System.out.print("Dime tu dni: ");
						dniScanner = scanner.nextLine();
						System.out.print("Introduzca tus años trabajados: ");
						 int anyoScanner = scanner.nextInt();
					            
						    selectSQL = "SELECT Nombre,Dni,Sexo,Categoria,Anyos FROM empleado where dni = '"+dniScanner+"';";
						 try (Connection conn = DBUtils.getConnection();
								Statement st = conn.createStatement();) {
							 st.executeUpdate("UPDATE empleado SET Anyos = '"+anyoScanner+"' WHERE Dni ='"+dniScanner+"';");
							 ResultSet rs = st.executeQuery(selectSQL);
							 while (rs.next()) {
									Empleado empleado;
									String nombre = rs.getString("Nombre");
									String dni = rs.getString("Dni");
									char sexo = rs.getString("Sexo").charAt(0);
									int categoria = rs.getInt("Categoria");
									int anyo = rs.getInt("Anyos");
									empleado = new Empleado(nombre, dni, sexo, categoria, anyo);
									st.executeUpdate("UPDATE nomina SET sueldo = " + n.sueldo(empleado) + " WHERE Dni ='" + empleado.dni + "';");
					
								}
							 
							} catch (SQLException e) {
					
							} catch (DatosNoCorrectosException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						break;
					case 0:
						System.out.println("Saliendo...");
						break;
					default:
						System.out.println("Opción no válida. Inténtalo de nuevo.");
					}
					System.out.println();
				} while (opcion != 0);
				break;
			case 4:
				System.out.println("Introduce el dni del empleado:");
				dniScanner = scanner.nextLine();
				try(Connection conn = DBUtils.getConnection();
						Statement st = conn.createStatement();
						ResultSet rs = st.executeQuery(SQL);){
					while (rs.next()) {
						Integer sueldo = n.sueldo(new Empleado(rs.getString("nombre"),
								rs.getString("dni"), rs.getString("sexo").charAt(0),
								rs.getInt("categoria"), rs.getInt("anyos")));

						
						st.executeUpdate(
								"UPDATE nomina SET sueldo = " + sueldo + " WHERE dni = '" + dniScanner + "'");
					}
					
				}catch(SQLException e) {
					
				} catch (DatosNoCorrectosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Sueldo actualizado con exito");
				break;
			case 5:
				try(Connection conn = DBUtils.getConnection();
						Statement st = conn.createStatement();
						ResultSet rs = st.executeQuery(SQL);){
					while (rs.next()) {
						Integer sueldo = n.sueldo(new Empleado(rs.getString("nombre"),
								rs.getString("dni"), rs.getString("sexo").charAt(0),
								rs.getInt("categoria"), rs.getInt("anyos")));

						
						st.executeUpdate(
								"UPDATE nomina SET sueldo = " + sueldo + " WHERE dni = '" + rs.getString("dni") + "'");
					}
					
				}catch(SQLException e) {
					
				} catch (DatosNoCorrectosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Sueldos actualizados con exito");
				
				break;
			case 6:
				try(Connection conn = DBUtils.getConnection();
						Statement st = conn.createStatement();
						ResultSet rs = st.executeQuery(SQL);
						 ){
					FileWriter fcs = new FileWriter("copiaSeguridad.txt");
					String text = "EMPLEADOS:\n---------------";
					while (rs.next()) {
						text += "\n" + rs.getString("nombre") + ", "
								+ rs.getString("dni") + ", "
								+ rs.getString("sexo").charAt(0) + ", "
								+ rs.getInt("categoria") + ", "
								+ rs.getInt("anyos") + "\n";
					}

					text += "\nNOMINAS:\n---------------";
					ResultSet nominas = st.executeQuery("SELECT dni, sueldo FROM nomina");
					while (nominas.next()) {
						text += "\n" + nominas.getString("dni") + ", " + nominas.getInt("sueldo") + "\n";
					}

					fcs.write(text);
					fcs.close();

					System.out.println("\nBackup completado");
				}catch(SQLException e) {
					
				}catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 0:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Opción no válida. Inténtalo de nuevo.");
			}
			System.out.println();
		} while (opcion != 0);
		
//PUNTO2		
//		try (Connection conn = DBUtils.getConnection();
//				Statement st = conn.createStatement();
//				ResultSet rs = st.executeQuery(SQL);FileReader f = new FileReader(txtEmpleados);
//			    BufferedReader b = new BufferedReader(f);
//			    FileWriter fws = new FileWriter(datSalarios)) {
//
//			while (rs.next()) {
//				Empleado empleado;
//				String nombre = rs.getString("Nombre");
//				String dni = rs.getString("Dni");
//				char sexo = rs.getString("Sexo").charAt(0);
//				int categoria = rs.getInt("Categoria");
//				int anyo = rs.getInt("Anyos");
//				empleado = new Empleado(nombre, dni, sexo, categoria, anyo);
//				empleados.add(empleado);
//
//			}
//
//			for (Empleado empleado : empleados) {
//				FileWriter fwe = new FileWriter(txtEmpleados);
//				fwe.write(empleado.nombre + ", " + empleado.dni + ", " + empleado.sexo + ", "
//						+ empleado.getCategoria() + ", " + empleado.anyos + "\n");
//				fws.write(empleado.dni + ", " + n.sueldo(empleado) + "\n");
//				
//				escribe(empleado);
//				st.executeUpdate(
//						"INSERT INTO nomina(Dni,sueldo) VALUES('" + empleado.dni + "'," + n.sueldo(empleado) + ");");
//			}
//			fws.close();
//			empleados.get(0).setCategoria(9);
//			empleados.get(1).incrAnyo();
//
//			for (Empleado empleado : empleados) {
//				escribe(empleado);
//				st.executeUpdate("UPDATE empleado SET Categoria = " + empleado.getCategoria() + ", Anyos = "
//						+ empleado.anyos + " WHERE Dni ='" + empleado.dni + "';");
//				st.executeUpdate(
//						"UPDATE nomina SET sueldo = " + n.sueldo(empleado) + " WHERE Dni ='" + empleado.dni + "';");
//			}
//
//		} catch (SQLException e) {
//
//		} catch (DatosNoCorrectosException e) {
//			System.out.println("\n" + e.getMessage());
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		
//PUNTO 1
//		String txtEmpleados = "../ProyectoSupuestoPractico_parte2/empledos.txt";
//		String datSalarios = "../ProyectoSupuestoPractico_parte2/salarios.dat";
//		
//		try (FileReader f = new FileReader(txtEmpleados);
//				BufferedReader b = new BufferedReader(f);
//				FileWriter fws = new FileWriter(datSalarios)) {
//
//			String cadena;
//			while ((cadena = b.readLine()) != null) {
//				String[] bloques = cadena.split(", ");
//
//				String nombre = bloques[0];
//				String dni = bloques[1];
//				char sexo = bloques[2].charAt(0);
//				Empleado empleado;
//
//				if (bloques.length == 5) {
//
//					int categoria = Integer.parseInt(bloques[3]);
//					int anyo = Integer.parseInt(bloques[4]);
//					empleado = new Empleado(nombre, dni, sexo, categoria, anyo);
//					empleados.add(empleado);
//				} else {
//					empleado = new Empleado(nombre, dni, sexo);
//					empleados.add(empleado);
//				}
//
//				escribe(empleado);
//			}
//
//			empleados.get(1).incrAnyo();
//			empleados.get(0).setCategoria(9);
//
//			FileWriter fwe = new FileWriter(txtEmpleados);
//			fwe.write(empleados.get(0).nombre + ", " + empleados.get(0).dni + ", " + empleados.get(0).sexo + ", "
//					+ empleados.get(0).getCategoria() + ", " + empleados.get(0).anyos + "\n");
//			fwe.write(empleados.get(1).nombre + ", " + empleados.get(1).dni + ", " + empleados.get(1).sexo + ", "
//					+ empleados.get(1).getCategoria() + ", " + empleados.get(1).anyos + "\n");
//			
//
//			fws.write(empleados.get(0).dni + ", " + n.sueldo(empleados.get(0)) + "\n");
//			fws.write(empleados.get(1).dni + ", " + n.sueldo(empleados.get(1)) + "\n");
//			fws.close();
//		} catch (IOException e) {
//
//		} catch (DatosNoCorrectosException e) {
//			System.out.println("\n" + e.getMessage());
//		}
//		
	}

	private static void escribe(Empleado e) {
		e.imprime();
		System.out.println("Sueldo: " + n.sueldo(e));
	}

	private static void altaEmpleado(Empleado empleado) {
		try (Connection conn = DBUtils.getConnection(); Statement st = conn.createStatement();) {
			st.executeUpdate("INSERT INTO empleado(nombre,dni,sexo,categoria,anyos) VALUES ('" + empleado.nombre + "','"
					+ empleado.dni + "','" + empleado.sexo + "'," + empleado.getCategoria() + "," + empleado.anyos
					+ ");");
			st.executeUpdate(
					"INSERT INTO nomina(Dni,sueldo) VALUES('" + empleado.dni + "'," + n.sueldo(empleado) + ");");
		} catch (SQLException e) {

		}

	}

	private static void altaEmpleado(String fichero) {
		String txtNuevosEmpleados = "../ProyectoSupuestoPractico_parte2/" + fichero + "";
		try (Connection conn = DBUtils.getConnection();
				Statement st = conn.createStatement();
				FileReader f = new FileReader(txtNuevosEmpleados);
				BufferedReader b = new BufferedReader(f)) {

			String cadena;
			while ((cadena = b.readLine()) != null) {
				String[] bloques = cadena.split(", ");

				String nombre = bloques[0];
				String dni = bloques[1];
				char sexo = bloques[2].charAt(0);
				Empleado empleado;

				if (bloques.length == 5) {

					int categoria = Integer.parseInt(bloques[3]);
					int anyo = Integer.parseInt(bloques[4]);
					empleado = new Empleado(nombre, dni, sexo, categoria, anyo);

				} else {
					empleado = new Empleado(nombre, dni, sexo);

				}

				st.executeUpdate("INSERT INTO empleado(nombre,dni,sexo,categoria,anyos) VALUES ('" + empleado.nombre
						+ "','" + empleado.dni + "','" + empleado.sexo + "'," + empleado.getCategoria() + ","
						+ empleado.anyos + ");");
				st.executeUpdate(
						"INSERT INTO nomina(Dni,sueldo) VALUES('" + empleado.dni + "'," + n.sueldo(empleado) + ");");
			}

		} catch (SQLException e) {

		} catch (IOException e) {

		} catch (DatosNoCorrectosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
