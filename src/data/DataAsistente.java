package data;

import entities.*;
//import java.time.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;

public class DataAsistente {
	
	public LinkedList<Asistente> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Asistente> asis= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select tipo_doc, nro_doc, nombre, apellido, email, fecha_nacimiento, celular, saldo, "
					+ "password  from asistente");
			//intencionalmente no se recupera la password
			if(rs!=null) {
				while(rs.next()) {
					Asistente a=new Asistente();
					a.setTipo_doc(rs.getString("tipo_doc"));
					a.setNro_doc(rs.getInt("nro_doc"));
					a.setNombre(rs.getString("nombre"));
					a.setApellido(rs.getString("apellido"));
					a.setEmail(rs.getString("email"));
					a.setFecha_nacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
					a.setCelular(rs.getString("celular"));
					a.setSaldo(rs.getFloat("saldo"));
					a.setPassword(rs.getString("password"));
					
					asis.add(a);
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
		
		
		return asis;
	}
	
	public Asistente getByUser(Asistente asi) {
		Asistente a=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select tipo_doc, nro_doc, nombre, apellido, email, fecha_nacimiento, celular, saldo, "
					+ "password  from asistente where email=? and password=?"
					);
			stmt.setString(1, asi.getEmail());
			stmt.setString(2, asi.getPassword());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				a=new Asistente();
				a.setTipo_doc(rs.getString("tipo_doc"));
				a.setNro_doc(rs.getInt("nro_doc"));
				a.setNombre(rs.getString("nombre"));
				a.setApellido(rs.getString("apellido"));
				a.setEmail(rs.getString("email"));
				a.setFecha_nacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
				a.setCelular(rs.getString("celular"));
				a.setSaldo(rs.getFloat("saldo"));
				a.setPassword(rs.getString("password"));
				//
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
		
		return a;
	}
	
	public Asistente getByDocumento(Asistente asi) {
		Asistente a=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select tipo_doc, nro_doc, nombre, apellido, email, fecha_nacimiento, celular, saldo, "
					+ "password  from asistente where tipo_doc=? and nro_doc=?"
					);
			stmt.setString(1, asi.getTipo_doc());
			stmt.setInt(2, asi.getNro_doc());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				a=new Asistente();
				a.setTipo_doc(rs.getString("tipo_doc"));
				a.setNro_doc(rs.getInt("nro_doc"));
				a.setNombre(rs.getString("nombre"));
				a.setApellido(rs.getString("apellido"));
				a.setEmail(rs.getString("email"));
				a.setFecha_nacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
				a.setCelular(rs.getString("celular"));
				a.setSaldo(rs.getFloat("saldo"));
				a.setPassword(rs.getString("password"));
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
		
		return a;
	}
	
	public void add(Asistente a) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into asistente(tipo_doc, nro_doc, nombre, apellido, email, fecha_nacimiento, celular, password)"
							+ " values(?,?,?,?,?,?,?,?)"
							);
			stmt.setString(1, a.getTipo_doc());
			stmt.setInt(2, a.getNro_doc());
			stmt.setString(3, a.getNombre());
			stmt.setString(4, a.getApellido());
			stmt.setString(5, a.getEmail());
			stmt.setObject(6, a.getFecha_nacimiento());
			stmt.setString(7, a.getCelular());
			stmt.setString(8, a.getPassword());
			stmt.executeUpdate();

			
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
	
	public void actualizarAsist(Asistente asi) {
        PreparedStatement stmt= null;
        try {
            stmt=DbConnector.getInstancia().getConn().
                    prepareStatement("update persona set nombre = ?, apellido = ?, email = ?, password = ?, celular = ?, " 
                    				 +"fecha_nacimiento= ?, tipo_doc = ?, nro_doc = ? where tipo_doc = ? and nro_doc = ?");
			stmt.setString(1, asi.getNombre());
			stmt.setString(2, asi.getApellido());
			stmt.setString(3, asi.getEmail());
			stmt.setString(4, asi.getPassword());
			stmt.setString(5, asi.getCelular());
			stmt.setObject(6, asi.getFecha_nacimiento());
			stmt.setString(7, asi.getTipo_doc());
			stmt.setInt(8, asi.getNro_doc());
			stmt.setString(9, asi.getTipo_doc());
			stmt.setInt(10, asi.getNro_doc());

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

	public void deleteByDoc(Asistente delAsi) { 
		PreparedStatement stmt = null;

        try {
        stmt = DbConnector.getInstancia().getConn().prepareStatement("delete from persona where tipo_doc=? and nro_doc=?");

        stmt.setString(1, delAsi.getTipo_doc());
        stmt.setInt(2, delAsi.getNro_doc());

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
	
