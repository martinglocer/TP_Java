package data;

import entities.*;
//import java.time.*;

import java.sql.*;
import java.util.LinkedList;

public class DataFiesta {
	
	public LinkedList<Fiesta> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Fiesta> fie= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select idfiesta, nombre_fiesta, descripcion from fiesta");
			//intencionalmente no se recupera la password
			if(rs!=null) {
				while(rs.next()) {
					Fiesta f=new Fiesta();
					f.setIdfiesta(rs.getInt("idfiesta"));
					f.setNombre_fiesta(rs.getString("nombre_fiesta"));
					f.setDescripcion(rs.getString("descripcion"));					
					
					fie.add(f);
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
		
		
		return fie;
	}
	
	public Fiesta getById(Fiesta fie) {
		Fiesta f=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select idfiesta, nombre_fiesta, descripcion from fiesta from asistente where idfiesta = ? "
					);
			stmt.setInt(1, fie.getIdfiesta());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				f=new Fiesta();
				f.setIdfiesta(rs.getInt("idfiesta"));
				f.setNombre_fiesta(rs.getString("nombre_fiesta"));
				f.setDescripcion(rs.getString("descripcion"));
				
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
		
		return f;
	}
	
	public void add(Fiesta f) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
							"insert into fiesta(nombre_fiesta, descripcion) values(?,?)"
							,PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, f.getNombre_fiesta());
			stmt.setString(2, f.getDescripcion());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			
			if (keyResultSet!= null && keyResultSet.next()) {
				f.setIdfiesta(keyResultSet.getInt(1));
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
	
	public void actualizarFiesta(Fiesta fie) {
        PreparedStatement stmt= null;
        try {
            stmt=DbConnector.getInstancia().getConn().
                    prepareStatement("update fiesta set idfiesta = ?, nombre_fiesta=?, descripcion= ? where idfiesta = ?");
			stmt.setInt(1, fie.getIdfiesta());
			stmt.setString(2, fie.getNombre_fiesta());
			stmt.setString(3, fie.getDescripcion());
			stmt.setInt(4, fie.getIdfiesta());

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

	public void deleteByID(Fiesta delfie) {
        PreparedStatement stmt = null;

        try {
        stmt = DbConnector.getInstancia().getConn().prepareStatement("delete from fiesta where idfiesta=?");

        stmt.setInt(1, delfie.getIdfiesta());
        
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
