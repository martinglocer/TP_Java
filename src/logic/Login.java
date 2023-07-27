package logic;

import java.util.LinkedList;

import data.*;
import entities.*;

public class Login {
	private DataAsistente da;
	
	public Login() {
		da=new DataAsistente();
	}
	
	public Asistente validate(Asistente a) {
		/* para hacer más seguro el manejo de passwords este sería un lugar 
		 * adecuado para generar un hash de la password utilizando un cifrado
		 * asimétrico como sha256 y utilizar el hash en lugar de la password en plano 
		 */
		return da.getByUser(a);
	}

	public LinkedList<Asistente> getAll(){
		return da.getAll();
	}

	public Asistente getByDocumento(Asistente a) {
		return da.getByDocumento(a);	
	}
	
	public Asistente getByUser(Asistente asist) {
		return da.getByUser(asist);
	}
	
	public void addAsistente(Asistente asist) {
		da.add(asist);
	}
	
	public void actualizarDatos(Asistente a) {
		da.actualizarAsist(a);
	}
	
	public void deleteByDoc(Asistente a) {
		da.deleteByDoc(a);
	}
}
