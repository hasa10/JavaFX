package controller.customer;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerFromController implements Initializable {

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colSalary;

    @FXML
    private TableView tblCustomer;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Customer customer = new Customer(txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtSalary.getText()));
        boolean isAdded = CustomerController.getInstance().saveCustomer(customer);
        if (isAdded){
            new Alert(Alert.AlertType.INFORMATION,"Customer Added Successfull").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Customer Not Added ").show();
        }
        loadTableCustomer();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (CustomerController.getInstance().deleteCustomer(txtId.getText())){
            new Alert(Alert.AlertType.INFORMATION,"Customer Deleted Successull!").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Customer Not Deleted").show();
        }
        loadTableCustomer();

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTableCustomer();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        Customer customer = CustomerController.getInstance().searchCustomer(txtId.getText());
        if (customer!=null){
            txtName.setText(customer.getName());
            txtAddress.setText(customer.getAddress());
            txtSalary.setText(customer.getSalary().toString());
            new Alert(Alert.AlertType.INFORMATION,"Customer Found!").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Customer Not Found!").show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean isupdated = CustomerController.getInstance().updateCustomer(new Customer(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtSalary.getText())
        ));
        if (isupdated){
            new Alert(Alert.AlertType.INFORMATION,"Customer Update Successfull!").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Customer Update Not Successll!").show();
        }
        loadTableCustomer();
    }

    void loadTableCustomer(){
        ObservableList<Customer> objectObservableList = FXCollections.observableArrayList();
        objectObservableList.addAll(CustomerController.getInstance().getAllCustomers());
        tblCustomer.setItems(objectObservableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        loadTableCustomer();

    }

    public void btnItemOnAction(ActionEvent actionEvent){
        Stage stage =new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../view/Item.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }
}
