package dao.custiom.impl;

import dao.custiom.CustomerDAO;
import entity.Customer;

import java.util.List;

public class CustomerDAOimpl2 implements CustomerDAO {
    @Override
    public String getLastCustomerId() {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Customer find(String pk) {
        return null;
    }

    @Override
    public boolean save(Customer entity) {
        return false;
    }

    @Override
    public boolean update(Customer entity) {
        return false;
    }

    @Override
    public boolean delete(String pk) {
        return false;
    }
}
