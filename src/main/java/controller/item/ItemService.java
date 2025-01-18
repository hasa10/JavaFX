package controller.item;

import model.Customer;
import model.Item;

import java.util.List;

public interface ItemService {
    List<Item> getAllItem();

    boolean saveItem(Customer item);

    boolean updateItem(Customer item);

    boolean deleteItem(String id);

    Item searchItem(String id);
}
