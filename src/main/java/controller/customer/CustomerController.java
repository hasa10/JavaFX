package controller.customer;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController implements CustomerService{
    private static CustomerController customerController;
    CustomerController(){

    }
    public static CustomerController getInstance(){
        if (customerController==null){
            customerController=new CustomerController();
        }
        return customerController;
    }
    @Override
    public List<Customer> getAllCustomers(){
        List<Customer> customerList= new ArrayList<>();
        try {
            ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery("select * from customer");
            while (resultSet.next()){
                customerList.add(new Customer(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }

    @Override
    public boolean saveCustomer(Customer customer) {
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("insert into customer values(?,?,?,?)");
            stm.setObject(1,customer.getId());
            stm.setObject(2,customer.getName());
            stm.setObject(3,customer.getAddress());
            stm.setObject(4,customer.getSalary());
            return stm.executeUpdate() >0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("Update customer set name=?,address=?,salary=? where id=?");
            stm.setObject(1,customer.getName());
            stm.setObject(2,customer.getAddress());
            stm.setObject(3,customer.getSalary());
            stm.setObject(4,customer.getId());
            boolean update = stm.executeUpdate() > 0;
            return update;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean deleteCustomer(String id) {
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("delete from customer where id='" + id + "'");
            return stm.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer searchCustomer(String id) {
        try {
            ResultSet res = DBConnection.getInstance().getConnection().createStatement().executeQuery("select * from customer where id='" + id + "'");
            if (res.next()){
                return new Customer(res.getString(1),res.getString(2),res.getString(3),res.getDouble(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public ObservableList<String> GetCustomerid() {
        ObservableList<String> custIdlist = FXCollections.observableArrayList();
        getAllCustomers().forEach(customer -> {
            custIdlist.add(customer.getId());
        });
        return custIdlist;
    }
}
