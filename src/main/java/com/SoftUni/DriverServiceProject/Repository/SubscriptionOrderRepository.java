package com.SoftUni.DriverServiceProject.Repository;

import com.SoftUni.DriverServiceProject.Models.Entity.SubscriptionOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionOrderRepository extends JpaRepository<SubscriptionOrder, Long> {
}