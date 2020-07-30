package dao.custiom;

import dao.CrudDAO;
import entity.Order;

public interface OrderDAO extends CrudDAO<Order,String> {

    public String getLastOrderId();
}
