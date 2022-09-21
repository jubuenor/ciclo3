package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    
    Connection connection;
    
    static String bd="jdbc:mysql://localhost:3306/juneled_store";
    static String login="root";
    static String pass="root";
    
    public DBConnection(){
        try{
            System.out.println("Conectado:");
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection(bd,login,pass);
        }catch(Exception e){
            System.out.println("Error en la conexion:"+ e.getMessage());
        }
    }
    public Connection getConnection(){return connection;}
    public void disconnect(){connection=null;}
    
    
}
