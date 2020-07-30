package dao;

import dao.custiom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getInstance(){
        return (daoFactory==null)?(daoFactory=new DAOFactory()):daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOType daoType){
        switch (daoType){
            case CUSTOMER:
                return (T) new CustomerDAOimpl();
            case ITEM:
                return (T) new ItemDAOimpl();
            case ORDER:
                return (T) new OrderDAOimpl();
            case ORDER_DETAIL:
                return (T) new OrderDetailDAOimpl();
            case QUERY:
                return (T) new QueryDAOimpl();
            default:
                return null;
        }
    }

//    public CustomerDAO getCustomerDao(){
//        return new CustomerDAOimpl();
//    }
//
//    public ItemDAO getItemDao(){
//        return new ItemDAOimpl();
//    }
//
//    public OrderDAO getOrderDao(){
//        return new OrderDAOimpl();
//    }
//
//    public OrderDetailDAO getOrderDetailDao(){
//        return new OrderDetailDAOimpl();
//    }
}
