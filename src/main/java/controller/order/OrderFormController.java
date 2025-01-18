package controller.order;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.customer.CustomerController;
import controller.item.ItemController;
import db.DBConnection;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.Customer;
import model.Item;

import java.awt.*;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.SimpleFormatter;

import static java.lang.Integer.*;

public class OrderFormController implements Initializable {

    public TableView tblOrders;
    public TableColumn colId;
    public TableColumn colDescription;
    public TableColumn colQTyOnHand;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;


    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXComboBox ComboBoxCustomerID;

    @FXML
    private JFXComboBox comboBoxItemCode;

    @FXML
    private JFXTextField txtDesc;

    @FXML
    private JFXComboBox txtItemCode;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtStock;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    void btnOrderOnAction(ActionEvent event) {

    }
    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime cTime = LocalTime.now();
            lblTime.setText(
                    cTime.getHour() + ":" + cTime.getMinute() + ":" + cTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();

    }



        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            colId.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
            colDescription.setCellValueFactory(new PropertyValueFactory<>("desc"));
            colQTyOnHand.setCellValueFactory(new PropertyValueFactory<>("qty"));
            colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
            colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

            loadDateAndTime();
            getCustomerId();
            getOrderId();

            ComboBoxCustomerID.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
                if (newValue!=null){
                    setCustomerDetaToText((String) newValue);
                    System.out.println(newValue);
                }
            }));

            comboBoxItemCode.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
                if (newValue!=null){
                    setOrderrDetaToText((String) newValue);
                    System.out.println(newValue);
                }
            }));

        }



    private void getCustomerId(){
        ObservableList<String> custid = CustomerController.getInstance().GetCustomerid();
        ComboBoxCustomerID.setItems(custid);
    }
    private void getOrderId(){
        ObservableList<String> orderid = ItemController.getInstance().GetOrderId();
        comboBoxItemCode.setItems(orderid);
    }

    private void setCustomerDetaToText(String id){
        Customer customer = CustomerController.getInstance().searchCustomer(id);
        txtName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
        txtSalary.setText(customer.getSalary().toString());
    }

    private void setOrderrDetaToText(String code){
        Item item = ItemController.getInstance().searchItem(code);
        txtDesc.setText(item.getDescription());
        txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
        txtStock.setText(String.valueOf(item.getQty()));
    }

}
