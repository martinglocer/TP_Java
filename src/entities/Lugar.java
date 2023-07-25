package entities;

import entities.Ciudad;

public class Lugar {
	
	private int idlugar;
	private String nombre_lugar;
	private String direccion;
	private int capacidad;
	private Ciudad ciudad;
	public int getIdlugar() {
		return idlugar;
	}
	public void setIdlugar(int idlugar) {
		this.idlugar = idlugar;
	}
	public String getNombre_lugar() {
		return nombre_lugar;
	}
	public void setNombre_lugar(String nombre_lugar) {
		this.nombre_lugar = nombre_lugar;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public Ciudad getCiudad() {
		return ciudad;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	@Override
	public String toString() {
		return "Lugar [idlugar=" + idlugar + ", nombre_lugar=" + nombre_lugar + ", direccion=" + direccion
				+ ", capacidad=" + capacidad + ", ciudad=" + ciudad + "]";
	}
	
	

}
