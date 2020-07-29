package business;

import dao.DataLayer;
import dao.ItemDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.impl.ItemDAOimpl;
import dao.impl.OrderDAOimpl;
import dao.impl.OrderDetailDAOimpl;
import db.DBConnection;
import entity.Item;
import entity.Order;
import entity.OrderDetail;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import util.CustomerTM;
import util.ItemTM;
import util.OrderDetailTM;
import util.OrderTM;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Business {

    public static List<CustomerTM> getCustomers(){
        return DataLayer.getAllCustomers();
    }

    public static boolean saveCustomer(String id, String name, String address){
        return DataLayer.saveCustomer(new CustomerTM(id,name,address));
    }

    public static boolean updateCustomer(String id,String name,String address){
        return DataLayer.updateCustomer(new CustomerTM(id,name,address));
    }

    public static boolean daletCustomer(String id){
        return DataLayer.DeletCustomer(id);
    }

    public static String newId(){
        String lastId = DataLayer.lastOrderId();

        if (lastId==null){
            return "C001";
        }else {
            int maxId = Integer.parseInt(lastId.replace("C", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "C00" + maxId;
            } else if (maxId < 100) {
                id = "C0" + maxId;
            } else {
                id = "C" + maxId;
            }
            return id;
        }
    }


    /////////************************* ITEM
    public static List<ItemTM> getItems(){
        return DataLayer.lodeAllItem();
    }

    public static boolean saveItems(String id, String descrition,int quntity,BigDecimal unitePrice){
        return DataLayer.saveItem(new ItemTM(id,descrition,quntity,unitePrice));
    }

    public static boolean updateItem(String id,String descreption, int quntity, BigDecimal unitPrice){
        return DataLayer.updateItem(new ItemTM(id,descreption,quntity,unitPrice));
    }

    public static boolean deletItem(TableView.TableViewSelectionModel<ItemTM> selectionModel, ObservableList<ItemTM> items, String code){
        return DataLayer.deletItem(selectionModel,items,code);
    }

    public static String itemIdGenerate(){

        String s = DataLayer.loadItemId();

        if (s==null){
            return "I001";
        }
        else {
            int maxCode = Integer.parseInt(s.replace("I", ""));
            maxCode =maxCode + 1;
            String code = "";
            if (maxCode < 10) {
                code = "I00" + maxCode;
            } else if (maxCode < 100) {
                code = "I0" + maxCode;
            } else {
                code = "I" + maxCode;
            }
            return code;
        }

    }

    /////////******************* Place Order

    public static boolean placeOrder(OrderTM order, List<OrderDetailTM> orderDetails) {
        OrderDAO orderDAO=new OrderDAOimpl();
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            boolean result = orderDAO.saveOrder(new Order(order.getOrderId(),
                    Date.valueOf(order.getOrderDate()),
                    order.getCustomerId()));
            if (!result) {
                connection.rollback();
                return false;
            }
            for (OrderDetailTM orderDetail : orderDetails) {
                OrderDetailDAO orderDetail1=new OrderDetailDAOimpl();
                ItemDAO itemDAO=new ItemDAOimpl();

                result = orderDetail1.saveOrderDetail(new OrderDetail(
                        order.getOrderId(), orderDetail.getCode(),
                        orderDetail.getQty(), BigDecimal.valueOf(orderDetail.getUnitPrice())
                ));
                if (!result){
                    connection.rollback();
                    return false;
                }
                Item item = itemDAO.findItem(orderDetail.getCode());
                item.setQtyOnHand(item.getQtyOnHand() - orderDetail.getQty());
                result = itemDAO.updateItem(item);
                if (!result){
                    connection.rollback();
                    return false;
                }
            }
            connection.commit();
            return true;
        } catch (Throwable throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


}
