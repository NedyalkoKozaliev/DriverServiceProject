package com.SoftUni.DriverServiceProject.Service;

import com.SoftUni.DriverServiceProject.Models.Entity.Order;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.OrderServiceModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.OrderViewModel;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrderViewModel createOrder(OrderServiceModel orderServiceModel) ;
    OrderViewModel getOrderById(Long id);

    Order getOrder(Long id);

    Float takeDistance();


    Float takeDuration();


    Order findOrderById(Long orderId);

    List<OrderViewModel> getAllOrders();

}
