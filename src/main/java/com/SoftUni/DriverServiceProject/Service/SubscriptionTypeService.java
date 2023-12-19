package com.SoftUni.DriverServiceProject.Service;

import com.SoftUni.DriverServiceProject.Models.Entity.Subscription;
import com.SoftUni.DriverServiceProject.Models.Enums.SubscriptionEnumName;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.SubscriptionTypeServiceModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.SubscriptionTypeViewModel;

public interface SubscriptionTypeService {


    SubscriptionTypeViewModel changeType(SubscriptionTypeServiceModel subscriptionTypeServiceModel);

    Subscription getSubscriptionByName(SubscriptionEnumName subscription);

}
