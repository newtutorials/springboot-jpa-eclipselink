package org.newtutorials.example.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dani on 5/20/2017.
 */
@Entity
//@NamedEntityGraph(name = "Customer.orders",
//        attributeNodes = @NamedAttributeNode("orders"))
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", length = 64)
    private String name;

    @OneToMany(cascade = CascadeType.ALL
            , mappedBy = "customer", fetch = FetchType.EAGER, orphanRemoval = true
    )
    private List<Order> orders;

    @Version
    private Long version;

    public Customer() {
    }

    public Customer( String name, List<Order> orders) {
        this.name = name;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", version=" + version +
                ", orders=" + orders +
                '}';
    }
}
