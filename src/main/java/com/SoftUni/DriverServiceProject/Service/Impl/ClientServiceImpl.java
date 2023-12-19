package com.SoftUni.DriverServiceProject.Service.Impl;

import com.SoftUni.DriverServiceProject.Models.Entity.Order;
import com.SoftUni.DriverServiceProject.Models.Entity.User;
import com.SoftUni.DriverServiceProject.Models.ViewModel.SubscriptionOrderViewModel;
import com.SoftUni.DriverServiceProject.Repository.OrderRepository;
import com.SoftUni.DriverServiceProject.Repository.SubscriptionOrderRepository;
import com.SoftUni.DriverServiceProject.Repository.UserRepository;
import com.SoftUni.DriverServiceProject.Service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final SubscriptionOrderRepository subscriptionOrderRepository;
    private final ModelMapper modelMapper;


    public ClientServiceImpl(UserRepository userRepository, OrderRepository orderRepository, SubscriptionOrderRepository subscriptionOrderRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.subscriptionOrderRepository = subscriptionOrderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User findClientById(Long id) {
       return userRepository.findUserById(id).orElseThrow(null);

    }

    @Override
    public User findClientByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(null);
    }

    @Override
    public List<Order> findMyOrders(Long id) {
        return orderRepository.findByClient(userRepository.findUserById(id).orElse(null));
    }

    @Override
    public List<SubscriptionOrderViewModel> findMySubscriptions(Long id) {
        return subscriptionOrderRepository.findAllByClient(userRepository.findUserById(id).orElse(null)).stream().
                map(subscriptionOrder -> modelMapper.map(subscriptionOrder,SubscriptionOrderViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public Order findLastOrder(Long id) {
        return orderRepository.findByClient(userRepository.findUserById(id).orElse(null)).stream().sorted().findFirst().get();
    }
}
