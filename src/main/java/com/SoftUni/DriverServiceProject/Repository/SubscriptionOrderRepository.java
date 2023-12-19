package com.SoftUni.DriverServiceProject.Repository;

import com.SoftUni.DriverServiceProject.Models.Entity.SubscriptionOrder;
import com.SoftUni.DriverServiceProject.Models.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionOrderRepository extends JpaRepository<SubscriptionOrder, Long> {

    SubscriptionOrder findSubscriptionOrderById(Long subscriptionId);

    List<SubscriptionOrder>findAllByClient(User user);

}