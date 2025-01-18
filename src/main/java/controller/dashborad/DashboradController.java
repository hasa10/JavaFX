package controller.dashborad;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class DashboradController {
    public AnchorPane dashboardAnchorPane;

    public void btnCustomerFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/Customer.fxml");

        assert resource !=null;
        Parent load = FXMLLoader.load(resource);
        this.dashboardAnchorPane.getChildren().clear();
        this.dashboardAnchorPane.getChildren().add(load);


    }

    public void btnOrderOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/orderForm.fxml");

        assert resource !=null;
        Parent load = FXMLLoader.load(resource);
        this.dashboardAnchorPane.getChildren().clear();
        this.dashboardAnchorPane.getChildren().add(load);
    }
}
