package controller;

import operations.OperationsBD;

public class ProductController {
    
    public String listProductos(){
        OperationsBD op =new OperationsBD();
        return op.listProductos();
    }
}
