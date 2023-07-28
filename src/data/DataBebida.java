package data;

import entities.*;
//import java.time.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;

public class DataBebida {
	
	public LinkedList<Bebida> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Bebida> bebs = new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select idBebida, nombre_bebida, descripcion");
			if(rs!=null) {
				while(rs.next()) {
					
					Bebida beb = new Bebida();
					beb.setIdbebida(rs.getInt("idBebida"));
					beb.setNombre_bebida(rs.getString("nombre_bebida"));
					beb.setDescripcion(rs.getString("descripcion"));
					
					
					bebs.add(beb);
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
		
		
		return bebs;
	}
	
	public Bebida getByUser(Bebida beb) {
		Bebida b=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select idBebida, nombre_bebida, descripcion from bebida where nombre_bebida=?"
					);
			stmt.setString(2, beb.getNombre_bebida());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				
				b=new Bebida();
				
				b.setIdbebida(rs.getInt("idBebida"));
				b.setNombre_bebida(rs.getString("nombre_bebida"));
				b.setDescripcion(rs.getString("descripcion"));
				
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
		
		return b;
	}
	
	
	public void addBebida(Bebida beb) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into product(nombre_bebida, descripcion) values(?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			
			stmt.setString(1, beb.getNombre_bebida());
			stmt.setString(2, beb.getDescripcion());
			
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                beb.setIdbebida(keyResultSet.getInt(1));
                System.out.println("Id bebida generado: ");
                System.out.println(beb.getIdbebida());
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
	
	public void actualizarBeb(Bebida beb) {
        PreparedStatement stmt= null;
        try {
            stmt=DbConnector.getInstancia().getConn().
                    prepareStatement("update bebida set nombre_bebida = ? and descripcion = ?");
            
			stmt.setString(1, beb.getNombre_bebida());
			stmt.setString(2, beb.getDescripcion());

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

	public void deleteByNombre(Bebida delBeb) { 
		PreparedStatement stmt = null;

        try {
        stmt = DbConnector.getInstancia().getConn().prepareStatement("delete from bebida where nombre_bebida=? and descripcion=?");

        stmt.setString(1, delBeb.getNombre_bebida());
        stmt.setString(2, delBeb.getDescripcion());

        stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nombre inexistente");

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
	
