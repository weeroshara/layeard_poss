package dao.custiom;

import dao.SuperDAO;
import entity.CustomEntity;

public interface QueryDAO extends SuperDAO {

    CustomEntity getOrdrerDetal(String id);

    CustomEntity getOrderDetil2(String id);
}
