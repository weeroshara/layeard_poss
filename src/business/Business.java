package business;

import dao.DataLayer;
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
            int maxId = Integer.parseInt(lastId.replace("C00", ""));
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

}
