/**
 * 
 */
package com.dev.ewt.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dev.ewt.order.client.catalog.CatalogClient;
import com.dev.ewt.order.client.customer.CustomerClient;
import com.dev.ewt.order.entity.OrderEntity;
import com.dev.ewt.order.repository.OrderRepository;

/**
 * @author Sid
 *
 */
@Component
public class OrderService {

	private OrderRepository orderRepository;
	private CustomerClient customerClient;
	private CatalogClient itemClient;

	@Autowired
	private OrderService(OrderRepository orderRepository,
			CustomerClient customerClient, CatalogClient itemClient) {
		super();
		this.orderRepository = orderRepository;
		this.customerClient = customerClient;
		this.itemClient = itemClient;
	}

	public OrderEntity order(OrderEntity order) {
		if (order.getNumberOfLines() == 0) {
			throw new IllegalArgumentException("No order lines!");
		}
		if (!customerClient.isValidCustomerId(order.getCustomerId())) {
			throw new IllegalArgumentException("Customer does not exist!");
		}
		return orderRepository.save(order);
	}

	public double getPrice(long orderId) {
		return orderRepository.findOne(orderId).totalPrice(itemClient);
	}
	
}
