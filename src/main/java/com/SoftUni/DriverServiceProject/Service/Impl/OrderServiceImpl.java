package com.SoftUni.DriverServiceProject.Service.Impl;

import com.SoftUni.DriverServiceProject.Models.Entity.Order;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.OrderServiceModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.OrderViewModel;
import com.SoftUni.DriverServiceProject.Repository.OrderRepository;
import com.SoftUni.DriverServiceProject.Service.OrderService;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
@Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public OrderViewModel createOrder(OrderServiceModel orderServiceModel) {

        Order order=modelMapper.map(orderServiceModel,Order.class);


        orderRepository.save(order);

        return modelMapper.map(order,OrderViewModel.class);
    }

    @Override
    public Optional<OrderViewModel> getOrderById(Long id) {
        return orderRepository.findById(id).map(order -> modelMapper.map(order,OrderViewModel.class));
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findOrderById(id);
    }

    @Override
    public Float takeDistance() {
        return null;
    }

    @Override
    public Float takeDuration() {
        return null;
    }

    @Override
    public Order findOrderById(Long orderId) {
        return orderRepository.findOrderById(orderId);
    }


    @Override
    public List<OrderViewModel> getAllOrders() {

    List<OrderViewModel> orders=orderRepository.findAll().
            stream().
            map(order->modelMapper.map(order,OrderViewModel.class)).collect(Collectors.toList());
            if (orders.isEmpty()){
                throw new NullPointerException("No pending order");
            }
          return orders;
    }



    private OrderViewModel mapAsOrder(Order order) {
       OrderViewModel orderViewModel = new OrderViewModel();

      orderViewModel.setId(order.getId());
       orderViewModel.setAddressTo(order.getAddressTo());
       orderViewModel.setAddressFrom(order.getAddressFrom());
       orderViewModel.setNumberOfPassengers(order.getNumberOfPassengers());



        return orderViewModel;
    }
}
