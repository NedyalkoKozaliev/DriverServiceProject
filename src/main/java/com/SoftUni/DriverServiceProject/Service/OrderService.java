package com.SoftUni.DriverServiceProject.Service;

import com.SoftUni.DriverServiceProject.Models.Entity.Order;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.OrderServiceModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.OrderViewModel;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrderViewModel createOrder(OrderServiceModel orderServiceModel);
    OrderViewModel getOrderById(Long id);

    Order getOrder(Long id);

    Float takeDistance();

   // https://teamtreehouse.com/community/how-to-use-the-google-map-matrix-api-for-calculating-the-distance-between-two-points-in-java-a-big-treat-is-promise

    //https://www.youtube.com/watch?v=dFhKVJcmADo


    Float takeDuration();


    Order findOrderById(Long orderId);

    List<OrderViewModel> getAllOrders();

}
