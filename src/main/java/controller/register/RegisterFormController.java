package controller.register;

import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.jasypt.util.text.BasicTextEncryptor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterFormController {

    @FXML
    private JFXTextField txtConformPassword;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws SQLException {
        if (txtPassword.getText().equals(txtConformPassword.getText())){
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("select * from users where email=" + "'"+txtEmail.getText().toString() + "'");
            String key="hasa12";
            BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
            basicTextEncryptor.setPassword(key);
            String encrypt = basicTextEncryptor.encrypt(txtPassword.getText());
            if (!resultSet.next()){
                PreparedStatement pst = connection.prepareStatement("INSERT INTO users (username, email, password) VALUES (?, ?, ?)");
                pst.setString(1,txtUserName.getText());
                pst.setString(2,txtEmail.getText());
                pst.setString(3,encrypt);
                pst.executeUpdate();
                new Alert(Alert.AlertType.INFORMATION,"Register Successful!").show();
                Stage stage=new Stage();
                try {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.show();
            }



        }else{
            System.out.println("hasa");
            new Alert(Alert.AlertType.ERROR,"check Password!");
        }
    }

}
