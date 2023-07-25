package entities;

public class Ciudad {

	private int cod_postal;
	private String nombre_ciudad ;
	
	public int getCod_postal() {
		return cod_postal;
	}
	public void setCod_postal(int cod_postal) {
		this.cod_postal = cod_postal;
	}
	public String getNombre_ciudad() {
		return nombre_ciudad;
	}
	public void setNombre_ciudad(String nombre_ciudad) {
		this.nombre_ciudad = nombre_ciudad;
	}
	@Override
	public String toString() {
		return "ciudad [cod_postal=" + cod_postal + ", nombre_ciudad=" + nombre_ciudad + "]";
	}
	
	
	
}
