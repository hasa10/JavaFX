package controller.item;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemController implements ItemService{
    private static ItemController itemController;
    ItemController(){

    }
    public static ItemController getInstance(){
        if (itemController==null){
            itemController=new ItemController();
        }
        return itemController;
    }
    @Override
    public List<Item> getAllItem() {
        List<Item> itemList=new ArrayList<>();
        try {
            ResultSet res = DBConnection.getInstance().getConnection().createStatement().executeQuery("select * from item");
            while (res.next()){
                itemList.add(new Item(
                        res.getString(1),
                        res.getString(2),
                        res.getDouble(3),
                        res.getInt(4)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemList;
    }

    @Override
    public boolean saveItem(Customer item) {
        return false;
    }

    @Override
    public boolean updateItem(Customer item) {
        return false;
    }

    @Override
    public boolean deleteItem(String id) {
        return false;
    }

    @Override
    public Item searchItem(String code) {
        try {
            ResultSet res = DBConnection.getInstance().getConnection().createStatement().executeQuery("select * from item where code='" + code + "'");
            if (res.next()){
                return new Item(res.getString(1),res.getString(2),res.getDouble(3),res.getInt(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public ObservableList<String> GetOrderId() {
        ObservableList<String> orderIdlist = FXCollections.observableArrayList();
        getAllItem().forEach(item -> {
            orderIdlist.add(item.getCode());
        });
        return orderIdlist;
    }
}
