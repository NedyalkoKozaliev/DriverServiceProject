package com.SoftUni.DriverServiceProject.Service;

import com.SoftUni.DriverServiceProject.Models.ServiceModels.SubscriptionOrderServiceModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.SubscriptionOrderViewModel;

public interface SubscriptionOrderService {
    SubscriptionOrderViewModel createSubscriptionOrder(SubscriptionOrderServiceModel subscriptionOrderServiceModel);

}
