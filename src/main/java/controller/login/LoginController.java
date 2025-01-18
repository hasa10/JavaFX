package view;

import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController{
    private static LoginController loginController;

    private LoginController(){

    }
    public static LoginController getInstance(){
        if (loginController==null){
            loginController= new LoginController();
        }
        return loginController;
    }

    public boolean valiDateLogin(String email, String password){
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
            stm.setString(1,email);
            stm.setString(2,password);
            ResultSet res = stm.executeQuery();
            System.out.println(res);
            if (res.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
