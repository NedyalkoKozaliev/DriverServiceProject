package com.SoftUni.DriverServiceProject.Service;

import com.SoftUni.DriverServiceProject.Models.ServiceModels.OrderServiceModel;

public interface OrderService {

    void addOrder(OrderServiceModel orderServiceModel);

    Float takeDistance();

   // https://teamtreehouse.com/community/how-to-use-the-google-map-matrix-api-for-calculating-the-distance-between-two-points-in-java-a-big-treat-is-promise

    //https://www.youtube.com/watch?v=dFhKVJcmADo


    Float takeDuration();


}
