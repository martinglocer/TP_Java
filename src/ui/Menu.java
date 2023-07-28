package ui;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import entities.*;
import logic.Login;

public class Menu {
	Scanner s=null;
	Login ctrlLogin = new Login();
	
	private String dateFormat = "dd/MM/yyyy";

	public void start() {
		s = new Scanner(System.in);
		Asistente a= login();
		System.out.println("Bienvenido "+a.getNombre()+" "+a.getApellido());
		System.out.println();
		
		String command;
		do {
			command=getCommand();
			executeCommand(command);
			System.out.println();
			
		}while(!command.equalsIgnoreCase("exit"));
		
		s.close();
	}

	private void executeCommand(String command) {
		switch (command) {
		case "Asistentes":
			MenuAsistentes ma = new MenuAsistentes();
			ma.start();
			break;
		case "Fiestas":
			MenuFiestas mf = new MenuFiestas();
			mf.start();
			break;
		case "Bebidas":
			MenuBebidas mb = new MenuBebidas();
			break;
		case "new":
			add();
			break;
		case "edit":
			edit();
			break;
		case "delete":
			delete();
			break;
		default:
			break;
		}
	}

	private String getCommand() {
		System.out.println("Ingrese a la sección que desea: ");
		System.out.println("Asistentes");
		System.out.println("Fiestas"); 
		System.out.println("Lugares"); 
		System.out.println("Bebidas");
		System.out.println();
		System.out.print("comando: ");
		return s.nextLine();
	}
	
	public Asistente login() {
		Asistente p=new Asistente();
		
		System.out.print("Email: ");
		p.setEmail(s.nextLine());

		System.out.print("password: ");
		p.setPassword(s.nextLine());
		
		p=ctrlLogin.validate(p);
		
		return p;
		
	}
	
	
	
	private Asistente getByUser() {
		System.out.println();
		Asistente a = new Asistente();
		
		System.out.print("Apellido: ");
		a.setApellido(s.nextLine());
		
		return ctrlLogin.getByUser(a);
	}
	
	private void add() {
		System.out.println();
		Asistente a=new Asistente();
		
		System.out.println("Ingrese los datos del nuevo asistente: ");
		
		cargaDatos(a);
		
		ctrlLogin.addAsistente(a);
	}
	
	private void edit() {
		System.out.println();
		Asistente a=new Asistente();

		System.out.print("Tipo doc: ");
		a.setTipo_doc(s.nextLine());

		System.out.print("Nro doc: ");
		a.setNro_doc(Integer.parseInt(s.nextLine()));
		
		System.out.println("Datos actuales: ");
		System.out.println(ctrlLogin.getByDocumento(a));
		
		cargaDatos(a);
		
		ctrlLogin.actualizarDatos(a);
		
	}
	
	private void delete() {
		Asistente delAsist = new Asistente();
		
		System.out.println("Personas actuales: ");
		System.out.println(ctrlLogin.getAll());
		
		System.out.println("Ingrese el tipo de documento del asistente a borrar: ");

		delAsist.setTipo_doc(s.nextLine());
		System.out.println("Ingrese el numero de documento del asistente a borrar: ");
		delAsist.setNro_doc(Integer.parseInt(s.nextLine()));
		
		ctrlLogin.deleteByDoc(delAsist);
	}
	
	private void cargaDatos(Asistente a) {
		
		
		
		System.out.println("Tipo documento: ");
		a.setTipo_doc(s.nextLine());
		
		System.out.println("Número documento: ");
		a.setNro_doc(Integer.parseInt(s.nextLine()));
		
		System.out.println("Nombre: ");
		a.setNombre(s.nextLine());
		
		System.out.println("Apellido: ");
		a.setApellido(s.nextLine());
		
		System.out.println("Email: ");
		a.setEmail(s.nextLine());
		
		
		DateTimeFormatter dFormat = DateTimeFormatter.ofPattern(dateFormat);
		System.out.println("Fecha de nacimiento ("+dateFormat+"): ");
		a.setFecha_nacimiento(LocalDate.parse(s.nextLine(), dFormat));
		
		
		System.out.println("Celular: ");
		a.setCelular(s.nextLine());
		
		System.out.println("Contraseña: ");
		a.setPassword(s.nextLine());
		
		
	}

}