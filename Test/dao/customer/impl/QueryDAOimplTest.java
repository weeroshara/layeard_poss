package dao.customer.impl;

import dao.DAOFactory;
import dao.DAOType;
import dao.custiom.QueryDAO;
import entity.CustomEntity;

public class QueryDAOimplTest {
    public static void main(String[] args) {
        QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOType.QUERY);
        CustomEntity ce = queryDAO.getOrderDetil2("OD001");
        System.out.println("Customer Name : " + ce.getCustomerName());
        System.out.println("Order ID : " + ce.getOrderId());
        System.out.println("Order Date : " + ce.getOrderDate());



        CustomEntity cal = queryDAO.getOrderDetail3("OD002");
        System.out.println("Ordr Id "+cal.getOrderId());
        System.out.println("orderDate "+cal.getOrderDate());
        System.out.println("customer name "+cal.getCustomerName());
        //System.out.println("total ");
    }
}
