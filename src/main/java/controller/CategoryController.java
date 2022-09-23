package controller;

import operations.OperationsBD;


public class CategoryController {
    
    public String listCategorias(){
        OperationsBD op= new OperationsBD();
        return op.listCategorias();
    }
    
}
