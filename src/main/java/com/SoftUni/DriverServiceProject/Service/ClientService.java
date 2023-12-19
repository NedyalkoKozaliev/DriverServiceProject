package com.SoftUni.DriverServiceProject.Service;

import com.SoftUni.DriverServiceProject.Models.Entity.Order;
import com.SoftUni.DriverServiceProject.Models.Entity.User;
import com.SoftUni.DriverServiceProject.Models.ViewModel.SubscriptionOrderViewModel;

import java.util.List;

public interface ClientService {


    User findClientById(Long id);

    User findClientByEmail(String email);

    List<Order> findMyOrders(Long id);

    List<SubscriptionOrderViewModel> findMySubscriptions(Long id);

    Order findLastOrder(Long id);

}
