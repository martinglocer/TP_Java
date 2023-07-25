package data;

import entities.*;
//import java.time.*;

import java.sql.*;
import java.util.LinkedList;

public class DataLugar {
		
		public LinkedList<Lugar> getAll(){
			Statement stmt=null;
			ResultSet rs=null;
			LinkedList<Lugar> lug= new LinkedList<>();
			
			try {
				stmt= DbConnector.getInstancia().getConn().createStatement();
				rs= stmt.executeQuery("select idlugar, nombre_lugar, direccion, capacidad, cod_postal from lugar");
				//intencionalmente no se recupera la password
				if(rs!=null) {
					while(rs.next()) {
						Lugar l =new Lugar();
						Ciudad c = new Ciudad();
						l.setIdlugar(rs.getInt("idlugar"));
						l.setNombre_lugar(rs.getString("nombre_lugar"));
						l.setDireccion(rs.getString("direccion"));	
						l.setCapacidad(rs.getInt("capacidad"));
						c.setCod_postal(rs.getInt("cod_postal"));
						l.setCiudad(c);
						
						lug.add(l);
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			} finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					DbConnector.getInstancia().releaseConn();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
			return lug;
		}
		
		public Lugar getById(Lugar lug) {
			Lugar l=null;
			PreparedStatement stmt=null;
			ResultSet rs=null;
			try {
				stmt=DbConnector.getInstancia().getConn().prepareStatement(
						"select idlugar, nombre_lugar, direccion, capacidad, cod_postal from lugar where idlugar = ? "
						);
				stmt.setInt(1, lug.getIdlugar());
				rs=stmt.executeQuery();
				if(rs!=null && rs.next()) {
					l=new Lugar();
					Ciudad c = new Ciudad();
					l.setIdlugar(rs.getInt("idlugar"));
					l.setNombre_lugar(rs.getString("nombre_lugar"));
					l.setDireccion(rs.getString("direccion"));
					l.setCapacidad(rs.getInt("capacidad"));
					c.setCod_postal(rs.getInt("cod_postal"));
					l.setCiudad(c);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					DbConnector.getInstancia().releaseConn();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return l;
		}
		
		public void add(Lugar l) {
			PreparedStatement stmt= null;
			ResultSet keyResultSet=null;
			try {
				stmt=DbConnector.getInstancia().getConn().prepareStatement(
								"insert into lugar(nombre_lugar, direccion, capacidad, cod_postal) values(?,?,?,?)"
								,PreparedStatement.RETURN_GENERATED_KEYS);
				stmt.setString(1, l.getNombre_lugar());
				stmt.setString(2, l.getDireccion());
				stmt.setInt(3, l.getCapacidad());
				stmt.setInt(4, l.getCiudad().getCod_postal());
				stmt.executeUpdate();
				keyResultSet=stmt.getGeneratedKeys();
				
				if (keyResultSet!= null && keyResultSet.next()) {
					l.setIdlugar(keyResultSet.getInt(1));
				} 

				
			}  catch (SQLException e) {
	            e.printStackTrace();
			} finally {
	            try {
	                if(keyResultSet!=null)keyResultSet.close();
	                if(stmt!=null)stmt.close();
	                DbConnector.getInstancia().releaseConn();
	            } catch (SQLException e) {
	            	e.printStackTrace();
	            }
			}
	    }
		
		public void actualizarLugar(Lugar lug) {
	        PreparedStatement stmt= null;
	        try {
	            stmt=DbConnector.getInstancia().getConn().
	                    prepareStatement("update lugar set idlugar = ?, nombre_lugar=?, direccion= ?, "
	                    		+ "capacidad = ?, cod_postal = ? where idlugar = ?");
				stmt.setInt(1, lug.getIdlugar());
				stmt.setString(2, lug.getNombre_lugar());
				stmt.setString(3, lug.getDireccion());
				stmt.setInt(4, lug.getCapacidad());
				stmt.setInt(5, lug.getCiudad().getCod_postal());
				stmt.setInt(6, lug.getIdlugar());

	            stmt.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Se produjo un error, revise los datos ingresados.");

	        } finally {
	            try {
	                if(stmt!=null) {stmt.close();}
	                DbConnector.getInstancia().releaseConn();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

		public void deleteByID(Lugar delug) {
	        PreparedStatement stmt = null;

	        try {
	        stmt = DbConnector.getInstancia().getConn().prepareStatement("delete from lugar where idlugar=?");

	        stmt.setInt(1, delug.getIdlugar());
	        
	        stmt.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Documento inexistente");

	        } finally {
	            try {
	                if(stmt!=null) {stmt.close();}
	                DbConnector.getInstancia().releaseConn();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	}
