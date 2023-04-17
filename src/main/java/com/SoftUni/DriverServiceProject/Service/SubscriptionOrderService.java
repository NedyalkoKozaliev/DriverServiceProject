package com.SoftUni.DriverServiceProject.Service;

import com.SoftUni.DriverServiceProject.Models.Entity.SubscriptionOrder;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.SubscriptionOrderServiceModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.SubscriptionOrderViewModel;

import java.util.List;

public interface SubscriptionOrderService {
    SubscriptionOrderViewModel createSubscriptionOrder(SubscriptionOrderServiceModel subscriptionOrderServiceModel);

    List<SubscriptionOrder> findNotAssigned();

    SubscriptionOrder findSubscriptionOrderById(Long subscriptionId);

}
