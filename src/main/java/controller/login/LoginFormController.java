package controller.login;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import view.LoginController;

import java.awt.*;
import java.io.IOException;

public class LoginFormController {

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtpassword;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String email = txtEmail.getText();
        String password = txtpassword.getText();

        if (LoginController.getInstance().valiDateLogin(email, password)) {
            new Alert(Alert.AlertType.INFORMATION,"Login Successfull!").show();
            Stage stage=new Stage();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Dashborad.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Invalid username or password!").show();
        }
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        Stage stage=new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/registerForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }
}
