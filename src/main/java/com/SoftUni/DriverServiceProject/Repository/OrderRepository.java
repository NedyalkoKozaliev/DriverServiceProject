package com.SoftUni.DriverServiceProject.Repository;

import com.SoftUni.DriverServiceProject.Models.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.id = ?1")
    Order findOrderById(Long id);
}