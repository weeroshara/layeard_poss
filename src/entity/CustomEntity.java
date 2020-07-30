package entity;

import java.util.Date;

public class CustomEntity implements SupperEntity{
    String orderId;
    String customerName;
    Date orderDate;
    String customerId;

    public CustomEntity() {
    }

    /////********** first join query
    public CustomEntity(String orderId, String customerName, Date orderDate) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderDate = orderDate;
    }


    ////////************  Second join query
    public CustomEntity(String orderId, String customerName, String customerId) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "orderId='" + orderId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }
}
