package controller;

import operations.OperationsBD;

public class UserController {
    
    public String update(int id_usuario,String nombre,String apellido, String email,String contrasena,double saldo){
        OperationsBD op = new OperationsBD();
        return op.update(id_usuario,nombre,apellido,email,contrasena,saldo);
    }
    
    public String getUser(String email){
        OperationsBD op = new OperationsBD();
        return op.getUser(email);
    }
    
    public String login(String email, String password) {
        OperationsBD op = new OperationsBD();
        return op.login(email, password);
    }
    
    public String register(String nombre,String apellido, String email,String contrasena,double saldo){
        OperationsBD op = new OperationsBD();
        return op.register(nombre,apellido,email,contrasena,saldo);
    }

}
