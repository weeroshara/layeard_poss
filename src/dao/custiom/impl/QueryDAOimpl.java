package dao.custiom.impl;

import dao.custiom.QueryDAO;
import db.DBConnection;
import entity.CustomEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDAOimpl implements QueryDAO {

    @Override
    public CustomEntity getOrdrerDetal(String id) {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select c.name,c.id,o.id from Customer c inner join `Order` o on c.id=o.customerId where o.id=?");
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return new CustomEntity(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
            }
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public CustomEntity getOrderDetil2(String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT  o.id, c.name, o.date FROM `Order` o\n" +
                    "INNER JOIN Customer c on o.customerId = c.id\n" +
                    "WHERE o.id=?");
            pstm.setObject(1,id);
            ResultSet rst = pstm.executeQuery();
            if (rst.next()){
                return new CustomEntity(rst.getString(1),
                        rst.getString(2),
                        rst.getString(3));
            }
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }


    ///// all join query in there
}
