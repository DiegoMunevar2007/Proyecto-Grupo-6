package lprs.consola;

import java.util.Scanner;

public class ConsolaPrincipal {
	
	Scanner lectura = new Scanner (System.in);
	
	
	public void mostrarConsolaPrincipal() throws Exception {
		
		System.out.println("Bienvenido a learning Path Recommendation System :) ");
		System.out.println("Iniciar Sesion: 1 , Crear cuenta: 2");
		
		System.out.println("Ingrese una opcion (1   o   2): ");
		
		int opcion = lectura.nextInt();		
		
		if(opcion == 1) {
			System.out.println("----------------Iniciar Sesion------------------");
			
			System.out.println("Ingrese su Usuario: ");
	
			String usuario = lectura.next();
	
			System.out.println("Ingrese su contraseña: ");
	
			String contrasena = lectura.next();
			
			if (contrasena=="b") {
				System.out.println("Bienvenido " + usuario);
			}
		}else if(opcion == 2) {
			System.out.println("----------------Crear Usuario------------------");
			System.out.println("Ingrese su Usuario: ");
		
			//String usuario = lectura.next();
	
			System.out.println("Ingrese su contraseña: ");
	
			//String contrasena = lectura.next();
			
			System.out.println("Estudiante: 1 , Profesor: 2 ");
			
			int tipo = lectura.nextInt();
			
			if(tipo == 1) {
				System.out.println("Est");
			}else if(tipo == 2){
				System.out.println("Prof");
			}else {
				System.out.println("La opcion " + tipo + " no es una opcion valida");
				
			}
			
			
		}else {
			System.out.println("La opcion " + opcion + " no es una opcion valida");
			
		}
		
	}
	

	
}
