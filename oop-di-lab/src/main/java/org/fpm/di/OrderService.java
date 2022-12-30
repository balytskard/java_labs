package org.fpm.di;

import org.fpm.di.annotations.SimpleComponent;
import org.fpm.di.annotations.Inject;

@SimpleComponent
public class OrderService {

    @Inject
    private OrderRepository orderRepository;

    public Order getOrderDetails(Integer orderId) {
        return orderRepository.getById(orderId);
    }
}
