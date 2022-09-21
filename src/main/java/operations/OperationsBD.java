package operations;
import beans.Producto;
import beans.Usuario;
import connector.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controller.UserController;

public class OperationsBD {
    /*public static void main(String[] args) {
        listUsuarios();
        UserController user = new UserController();
        String res=user.login("admin", "admin");
        System.out.println(res);
    }*/
    
    public static void listProductos(){
        DBConnection con=new DBConnection();
        
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
                System.out.println(pr.toString());
            }
        }catch(SQLException e){
            e.getMessage();
        }finally{con.disconnect();}
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
                System.out.println(user.toString());
            }
        }catch(SQLException e){
            System.out.println(e);
            e.getMessage();
        }finally{con.disconnect();}
    }
    
}
