package controller;

import beans.Usuario;
import com.google.gson.Gson;
import connector.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserController implements IUserController {

    @Override
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
            e.getMessage();
        } finally {
            con.disconnect();
        }
        return "false";
    }

}
