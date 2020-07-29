package business;

import dao.DataLayer;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import util.CustomerTM;
import util.ItemTM;

import java.math.BigDecimal;
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

    /////////*******************

}
