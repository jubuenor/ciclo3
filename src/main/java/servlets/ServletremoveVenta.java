package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.VentasController;

@WebServlet("/ServletremoveVenta")
public class ServletremoveVenta extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public ServletremoveVenta() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        doPost(request, response);
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        VentasController venta = new VentasController();
        
        int id_venta = Integer.parseInt(request.getParameter("id_venta"));
        
        String result = venta.removeVenta(id_venta);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(result);
        out.flush();
        out.close();
    }

}
