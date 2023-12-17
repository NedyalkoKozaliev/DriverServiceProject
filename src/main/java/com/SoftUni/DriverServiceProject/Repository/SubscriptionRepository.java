package com.SoftUni.DriverServiceProject.Repository;

import com.SoftUni.DriverServiceProject.Models.Entity.Subscription;
import com.SoftUni.DriverServiceProject.Models.Enums.SubscriptionEnumName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    //Subscription findSubscriptionByName(SubscriptionEnumName subscription);

    Optional<Subscription>findByName(SubscriptionEnumName subscriptionEnumName);
}