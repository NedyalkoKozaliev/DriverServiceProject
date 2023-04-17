package com.SoftUni.DriverServiceProject.Service.Impl;

import com.SoftUni.DriverServiceProject.Models.Entity.SubscriptionOrder;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.SubscriptionOrderServiceModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.SubscriptionOrderViewModel;
import com.SoftUni.DriverServiceProject.Repository.SubscriptionOrderRepository;
import com.SoftUni.DriverServiceProject.Service.ClientService;
import com.SoftUni.DriverServiceProject.Service.SubscriptionOrderService;
import com.SoftUni.DriverServiceProject.Service.SubscriptionTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionOrderServiceImpl implements SubscriptionOrderService {
    private final ModelMapper modelMapper;
    private final SubscriptionOrderRepository subscriptionOrderRepository;
    private final ClientService clientService;
    private final SubscriptionTypeService subscriptionTypeService;

    @Autowired
    public SubscriptionOrderServiceImpl(ModelMapper modelMapper, SubscriptionOrderRepository subscriptionOrderRepository, ClientService clientService, SubscriptionTypeService subscriptionTypeService) {
        this.modelMapper = modelMapper;
        this.subscriptionOrderRepository = subscriptionOrderRepository;
        this.clientService = clientService;
        this.subscriptionTypeService = subscriptionTypeService;
    }

    @Override
    public SubscriptionOrderViewModel createSubscriptionOrder(SubscriptionOrderServiceModel subscriptionOrderServiceModel) {

        SubscriptionOrder subscriptionOrder=modelMapper.map(subscriptionOrderServiceModel,SubscriptionOrder.class);
        subscriptionOrder.setClient(clientService.findClientById(subscriptionOrderServiceModel.getClientId()));
        subscriptionOrder.setSubscription(subscriptionTypeService.getSubscriptionByName(subscriptionOrderServiceModel.getSubscription()));
        subscriptionOrderRepository.save(subscriptionOrder);


        return modelMapper.map(subscriptionOrder, SubscriptionOrderViewModel.class);
    }
}
