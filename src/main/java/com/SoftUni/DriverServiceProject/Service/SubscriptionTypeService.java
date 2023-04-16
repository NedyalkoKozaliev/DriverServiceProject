package com.SoftUni.DriverServiceProject.Service;

import com.SoftUni.DriverServiceProject.Models.ServiceModels.SubscriptionTypeServiceModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.SubscriptionTypeViewModel;

public interface SubscriptionTypeService {


    SubscriptionTypeViewModel createNewType(SubscriptionTypeServiceModel subscriptionTypeServiceModel);

}
