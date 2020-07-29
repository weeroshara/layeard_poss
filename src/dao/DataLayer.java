package dao;

import db.DBConnection;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import util.CustomerTM;
import util.ItemTM;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataLayer {

    public static List<CustomerTM> getAllCustomers() {
        try {
            Statement stm = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Customer");
            ArrayList<CustomerTM> customer = new ArrayList<>();
            while (rst.next()) {
//                String id = rst.getString(1);
//                String name = rst.getString(2);
//                String address = rst.getString(3);
                customer.add(new CustomerTM(rst.getString(1), rst.getString(2), rst.getString(3)));
            }
            return customer;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static boolean saveCustomer(CustomerTM customers){
        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO Customer VALUES (?,?,?)");
            pstm.setObject(1, customers.getId());
            pstm.setObject(2, customers.getName());
            pstm.setObject(3, customers.getAddress());
            return pstm.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }

    public static boolean updateCustomer(CustomerTM customers){
        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
            pstm.setObject(1, customers.getName());
            pstm.setObject(2, customers.getAddress());
            pstm.setObject(3, customers.getId());
            return pstm.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }


    }

    public static boolean DeletCustomer(String customerId){
        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM Customer WHERE id=?");
            pstm.setObject(1, customerId);
            return pstm.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static String lastOrderId() {
        try {
            Statement stm = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rst = stm.executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1");
            if (rst.next()) {
                return rst.getString(1);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    ////////********************************* ITEMS

    public static List<ItemTM> lodeAllItem(){
        try {
            Statement stm = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Item");
            ArrayList<ItemTM> items=new ArrayList<>();
            items.clear();
            while (rst.next()) {
                items.add(new ItemTM(rst.getString(1),rst.getString(2),rst.getInt(3), BigDecimal.valueOf(rst.getDouble(4))));
            }
            return items;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean saveItem(ItemTM items){
        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO Item VALUES (?,?,?,?)");
            pstm.setObject(1, items.getCode());
            pstm.setObject(2, items.getDescription());
            pstm.setObject(3, items.getUnitPrice());
            pstm.setObject(4, items.getQtyOnHand());
            return pstm.executeUpdate()>0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateItem(ItemTM items){
        try {
//            ItemTM selectedItem = tblItems.getSelectionModel().getSelectedItem();
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
            pstm.setObject(1, items.getDescription());
            pstm.setObject(2, items.getUnitPrice());
            pstm.setObject(3, items.getQtyOnHand());
            pstm.setObject(4, items.getCode());
            if (pstm.executeUpdate() == 0) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the Item").show();
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deletItem(TableView.TableViewSelectionModel<ItemTM> selectionModel,ObservableList<ItemTM> items,String code){
        try {
            ItemTM selectedItem = selectionModel.getSelectedItem();
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM Item WHERE code=?");
            pstm.setObject(1, code);
            if (pstm.executeUpdate() == 0) {
                new Alert(Alert.AlertType.ERROR, "Failed to delete the item", ButtonType.OK).show();
                return false;
            } else {
                items.remove(selectedItem);
                selectionModel.clearSelection();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String loadItemId(){
        try {
            Statement stm = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rst = stm.executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1");
            if (rst.next()) {
                return rst.getString(1);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
