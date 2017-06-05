package org.newtutorials.example.model;

import javax.persistence.*;

/**
 * Created by dani on 5/20/2017.
 */
@Entity
@Table(name = "ORDERS")
//todo !!!!!!!!!!!!!!!!!!!
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="CUSTOMER_ID")
    private Customer customer;

    @Column(name = "ORDER_NAME", length = 128)
    private String orderName;

    @Version
    private Long version;

    public Order() {
    }

    public Order(Customer customer, String orderName) {
        this.customer = customer;
        this.orderName = orderName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderName='" + orderName + '\'' +
                ", version=" + version +
                '}';
    }
}
