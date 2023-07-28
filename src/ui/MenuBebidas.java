package ui;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import entities.*;
import data.DataBebida;

public class MenuBebidas {
	Scanner s=null;
	DataBebida dbeb = new DataBebida();
	
	private String dateFormat = "dd/MM/yyyy";

	public void start() {
		s = new Scanner(System.in);
		
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
		case "list":
			System.out.println(dbeb.getAll());
			break;
		case "find":
			System.out.println(find());
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
		System.out.println("Ingrese el comando según la opción que desee realizar");
		System.out.println("list\t\tlistar todos");
		System.out.println("find\t\tbuscar por nombre de bebida"); 
		System.out.println("new\t\tcrea una nueva bebida");
		System.out.println("edit\t\tbusca por nombre de bebida y actualiza los datos");
		System.out.println("delete\t\tborra por nombre de bebida");
		System.out.println();
		System.out.print("comando: ");
		return s.nextLine();
	}
	
	
	private Bebida find() {
		System.out.println();
		Bebida beb=new Bebida();
		System.out.print("Nombre bebida: ");
		beb.setNombre_bebida(s.nextLine());
		
		return dbeb.getByUser(beb);
	}
	
	
	private void add() {
		System.out.println();
		Bebida beb=new Bebida();
		
		System.out.println("Ingrese los datos de la nueva bebida: ");
		
		cargaDatos(beb);
		
		dbeb.addBebida(beb);
	}
	
	private void edit() {
		System.out.println();
		Bebida beb=new Bebida();

		System.out.print("Nombre bebida: ");
		beb.setNombre_bebida(s.nextLine());
		
		System.out.println("Datos actuales: ");
		System.out.println(dbeb.getByUser(beb));
		
		cargaDatos(beb);
		
		dbeb.actualizarBeb(beb);
		
	}
	
	private void delete() {
		Bebida delBeb = new Bebida();
		
		System.out.println("Bebidas actuales: ");
		System.out.println(dbeb.getAll());
		
		System.out.println("Ingrese el nombre de la bebida a borrar: ");

		delBeb.setNombre_bebida(s.nextLine());
		
		dbeb.deleteByNombre(delBeb);
	}
	
	private void cargaDatos(Bebida a) {
		
		
		System.out.println("Nombre bebida: ");
		a.setNombre_bebida(s.nextLine());
		
		System.out.println("Descripción bebida: ");
		a.setDescripcion(s.nextLine());
		
		
		
	}

}