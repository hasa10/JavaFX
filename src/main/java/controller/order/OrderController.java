package controller.order;

import db.DBConnection;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderController {
    public boolean placeOrder(Order order) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("Insert into orders VALUES(?,?,?");
        pstm.setString(1,order.getId());
        pstm.setObject(2,order.getDate());
        pstm.setString(3,order.getCustomerId());

        return pstm.executeUpdate() > 0;

    }
}
