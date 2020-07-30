package dao.custiom;

import dao.CrudDAO;
import entity.Item;

public interface ItemDAO extends CrudDAO<Item,String> {

    public String getLastItemCode();
}
