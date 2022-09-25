package operations;
import beans.Categoria;
import beans.Producto;
import beans.Usuario;
import beans.Ventas;
import com.google.gson.Gson;
import connector.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

public class OperationsBD {
    /*public static void main(String[] args) {
        String jsonArr = listCategorias();
        System.out.println(jsonArr);
    }*/
 
    public String removeVenta(int id_venta){
        DBConnection con=new DBConnection();
             
        String sql= "DELETE FROM ventas WHERE id_venta="+id_venta+"";
        
        try{
            Statement st= con.getConnection().createStatement();
            st.executeUpdate(sql);
            st.close();
            return "true";
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{con.disconnect();}
        return "false";
        
    }
    
    public String getVentas(int id_usuario){
        
        Gson json = new Gson();
        DBConnection con = new DBConnection();
        ArrayList<Ventas> jsonArr=new ArrayList<>();
        String sql = "SELECT * FROM ventas WHERE id_usuario='" + id_usuario + "'";
        try {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Ventas venta = new Ventas(
                        rs.getInt("id_venta"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_producto"),
                        rs.getInt("cantidad"),
                        rs.getDouble("valor_total"),
                        rs.getString("fecha")
                );
                jsonArr.add(venta);
            }
                return json.toJson(jsonArr);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            con.disconnect();
        }
        return "false";
        
    }
    
    public String addVenta(int id_usuario, int id_producto,int cantidad,double valor_total,String fecha){
        DBConnection con=new DBConnection();
             
        String sql= String.format("INSERT INTO ventas VALUES(LAST_INSERT_ID(),'%s', '%s', '%s', '%s','%s')",id_usuario,id_producto,cantidad,valor_total,fecha);
        
        try{
            Statement st= con.getConnection().createStatement();
            st.executeUpdate(sql);
            st.close();
            return "true";
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{con.disconnect();}
        
        return "false";
    }
    
    public String listProductos(){
        DBConnection con=new DBConnection();
        
        Gson json = new Gson();
        ArrayList<Producto> jsonArr=new ArrayList<>();
        
        String sql="SELECT * FROM producto";
        
        try{
            Statement st=con.getConnection().createStatement();
            ResultSet rs=st.executeQuery(sql);
            
            while(rs.next()){
                Producto pr=new Producto(
                        rs.getInt("id_producto"),
                        rs.getInt("id_categoria"),
                        rs.getString("nombre_producto"),
                        rs.getString("descripcion"),
                        rs.getDouble("valor"),
                        rs.getString("marca")
                );
                jsonArr.add(pr);
            }
            return json.toJson(jsonArr);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{con.disconnect();}
        
        return "false";
    }
    
    public String getUser(String email){
        Gson json = new Gson();
        DBConnection con = new DBConnection();
        String sql = "SELECT * FROM usuario WHERE email='" + email + "'";
        try {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Usuario user = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("contrasena"),
                        rs.getDouble("saldo")
                );
                return json.toJson(user);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            con.disconnect();
        }
        return "false";
    }
    
    public String login(String email, String password) {
        Gson json = new Gson();
        DBConnection con = new DBConnection();
        String sql = "SELECT * FROM usuario WHERE email='" + email + "' AND contrasena='" + password + "'";
        try {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Usuario user = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("contrasena"),
                        rs.getDouble("saldo")
                );
                return json.toJson(user);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            con.disconnect();
        }
        return "false";
    }
    
    public String register(String nombre,String apellido, String email,String contrasena,double saldo){
        DBConnection con=new DBConnection();
             
        String sql= String.format("INSERT INTO usuario VALUES(LAST_INSERT_ID(),'%s', '%s', '%s', '%s','%s')",nombre,apellido,email,contrasena,saldo);
        
        try{
            Statement st= con.getConnection().createStatement();
            st.executeUpdate(sql);
            st.close();
            return "true";
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{con.disconnect();}
        
        return "false";
    }
    
    public String listCategorias(){
        DBConnection con = new DBConnection();
        Gson json = new Gson();
        ArrayList<Categoria> jsonArr=new ArrayList<>();
        String sql = "SELECT * FROM categoria";
        
        try{
            Statement st=con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                Categoria cat = new Categoria(
                        rs.getInt("id_categoria"),
                        rs.getString("nombre_categoria")
                );
                jsonArr.add(cat);
            }
            return json.toJson(jsonArr);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{con.disconnect();}
        
        return "false";
    }
    
    public String update(int id_usuario,String nombre,String apellido, String email,String contrasena,double saldo){
        DBConnection con=new DBConnection();
             
        String sql= String.format("UPDATE usuario SET nombre='%s', apellido='%s', email='%s', contrasena='%s',saldo='%s'"
                + "WHERE id_usuario=%s",nombre,apellido,email,contrasena,saldo,id_usuario);
        
        try{
            Statement st= con.getConnection().createStatement();
            st.executeUpdate(sql);
            st.close();
            return "true";
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{con.disconnect();}
        return "false";
    }
    
    
}
