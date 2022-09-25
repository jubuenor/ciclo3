package controller;

import operations.OperationsBD;

public class VentasController {
    
    public String removeVenta(int id_venta){
        OperationsBD op = new OperationsBD();
        return op.removeVenta(id_venta);
    }
    
    public String addVenta(int id_usuario, int id_producto,int cantidad,double valor_total,String fecha){
        OperationsBD op = new OperationsBD();
        return op.addVenta(id_usuario,id_producto,cantidad,valor_total,fecha);
    }
    
    public String getVentas(int id_usuario){
        OperationsBD op = new OperationsBD();
        return op.getVentas(id_usuario);
    }
    
    
}
