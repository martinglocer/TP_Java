package entities;

public class Bebida {
	
	private int idbebida; 
	private String nombre_bebida; 
	private String descripcion;
	
	public int getIdbebida() {
		return idbebida;
	}
	public void setIdbebida(int idbebida) {
		this.idbebida = idbebida;
	}
	public String getNombre_bebida() {
		return nombre_bebida;
	}
	public void setNombre_bebida(String nombre_bebida) {
		this.nombre_bebida = nombre_bebida;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "Bebida [idbebida=" + idbebida + ", nombre_bebida=" + nombre_bebida + ", descripcion=" + descripcion
				+ "]";
	}
	
	
	
	

}
