package ui;


import java.util.Scanner;

import data.DataFiesta;
import entities.Fiesta;

public class MenuFiestas {
	
	Scanner s=null;
	DataFiesta df = new DataFiesta();

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
			list();
			break;
		case "find":
			find();
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
		System.out.println("list\t\tListar todas las fiestas");
		System.out.println("find\t\tBuscar fiesta"); //solo debe devolver 1
		System.out.println("new\t\tCrear una nueva fiesta");
		System.out.println("edit\t\tActualizar una fiesta existente");
		System.out.println("delete\t\tBorrar una fiesta");
		System.out.println();
		System.out.print("comando: ");
		return s.nextLine();
	}
	
	private void list(){
		
		for (Fiesta f: df.getAll()) {
			System.out.println(f);
		}
	}
	
	private void find(){
		System.out.println();
		System.out.println("Estos son las fiestas actuales: ");
		this.list();
		Fiesta f= new Fiesta();
		System.out.print(df.getAll());
		
		System.out.println("Ingrese id de la fiesta que desea encontrar: ");
		f.setIdfiesta(Integer.parseInt(s.nextLine()));
		
		Fiesta fie = df.getById(f);
		if (fie != null) {
		System.out.println(fie);
		} else {System.out.println("No existe ninguna fiesta con ese ID");
			}
	}
	
	
	private void add() {
		System.out.println();
		Fiesta f = new Fiesta();
		
		System.out.println("Ingrese los datos de la nueva fiesta: ");
		
		cargaDatos(f);
		df.add(f);
	}
	
	private void edit() {
		System.out.println();
		Fiesta f = new Fiesta();
		
		this.list();
		System.out.print(df.getAll());
		
		System.out.println("Ingrese id de la fiesta que desea editar: ");
		f.setIdfiesta(Integer.parseInt(s.nextLine()));
		
		Fiesta fie = df.getById(f);
		if (fie != null) {
			System.out.println("Estos son los datos actuales de la fiesta");
			System.out.println(fie);
			cargaDatos(fie);
			df.actualizarFiesta(fie);
		} else {System.out.println("No existe ninguna fiesta con ese ID");
			}
		
	}
	
	private void delete() {
		Fiesta delfi = new Fiesta();
		
		System.out.println("Fiestas actuales: ");
		this.list();
		System.out.println("Ingrese id de la fiesta que desea editar: ");
		delfi.setIdfiesta(Integer.parseInt(s.nextLine()));
		
		Fiesta fie = df.getById(delfi);
		if (fie != null) {
			System.out.println("Esta es la fiesta que va a ser eliminada");
			System.out.println(fie);
			df.deleteByID(fie);
			System.out.println("Fiesta eliminada");
		} else {System.out.println("No existe ninguna fiesta con ese ID");
			}
	}
	
	private void cargaDatos(Fiesta f) {
		
		System.out.println("Nombre de la fiesta: ");
		f.setNombre_fiesta(s.nextLine());
		
		System.out.println("Descripcion de la fiesta: ");
		f.setDescripcion(s.nextLine());
		
	}
	
}


