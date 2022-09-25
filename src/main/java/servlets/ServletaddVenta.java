package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.VentasController;

@WebServlet("/ServletaddVenta")
public class ServletaddVenta extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public ServletaddVenta() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        doPost(request, response);
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        VentasController ventas = new VentasController();
        
        int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
        int id_producto = Integer.parseInt(request.getParameter("id_producto"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        double valor = Double.parseDouble(request.getParameter("valor_total"));
        
        String fecha = request.getParameter("fecha");
        
        String result = ventas.addVenta(id_usuario, id_producto, cantidad , valor, fecha);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(result);
        out.flush();
        out.close();
    }

}
