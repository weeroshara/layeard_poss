package dao.custiom;

import dao.CrudDAO;
import entity.Customer;

public interface CustomerDAO extends CrudDAO<Customer,String> {


    public String getLastCustomerId();
}
