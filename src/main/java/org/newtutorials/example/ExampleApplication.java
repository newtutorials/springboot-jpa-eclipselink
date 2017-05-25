package org.newtutorials.example;

import org.newtutorials.example.model.Customer;
import org.newtutorials.example.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * Created by dani on 25/05/2017.
 */
@SpringBootApplication
@EnableTransactionManagement
public class ExampleApplication extends JpaBaseConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ExampleApplication.class);

    protected ExampleApplication(DataSource dataSource, JpaProperties properties, ObjectProvider<JtaTransactionManager> jtaTransactionManager, ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
        super(dataSource, properties, jtaTransactionManager, transactionManagerCustomizers);
    }

    @Override
    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        return new EclipseLinkJpaVendorAdapter();
    }

    @Override
    protected Map<String, Object> getVendorProperties() {
        return Collections.singletonMap("eclipselink.weaving", "false");
    }

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    @Bean
    public CommandLineRunner exampleRunner(CustomerService customerService) {
        return (args) -> {
            Customer customer = new Customer("Customer 1", new ArrayList<>());
            customer.getOrders().add(new Order(customer, "order 1"));
            customer.getOrders().add(new Order(customer, "order 2"));
            customerService.save(customer);
            customerService.findAll().forEach(c -> {
                logger.info(c.toString());
            });
        };
    }
}
