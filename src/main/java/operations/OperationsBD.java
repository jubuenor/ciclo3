package operations;
import beans.Categoria;
import beans.Producto;
import beans.Usuario;
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
    
    public static void listUsuarios(){
        DBConnection con=new DBConnection();
        
        String sql="SELECT * FROM usuario";
        
        try{
            Statement st=con.getConnection().createStatement();
            ResultSet rs=st.executeQuery(sql);
            
            while(rs.next()){
                Usuario user=new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("contrasena"),
                        rs.getDouble("saldo")
                );
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{con.disconnect();}
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
}
