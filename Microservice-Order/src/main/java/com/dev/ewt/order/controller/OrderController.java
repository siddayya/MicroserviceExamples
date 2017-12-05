/**
 * 
 */
package com.dev.ewt.order.controller;

import org.springframework.web.bind.annotation.RestController;

import com.dev.ewt.order.client.catalog.CatalogClient;
import com.dev.ewt.order.client.catalog.Item;
import com.dev.ewt.order.client.customer.Customer;
import com.dev.ewt.order.client.customer.CustomerClient;
import com.dev.ewt.order.entity.OrderEntity;
import com.dev.ewt.order.repository.OrderRepository;
import com.dev.ewt.order.service.OrderService;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Sid
 *
 */
@RestController
@RequestMapping("/v1/order")
public class OrderController {

	private OrderRepository orderRepository;

	private OrderService orderService;

	private CustomerClient customerClient;
	private CatalogClient catalogClient;

	@Autowired
	private OrderController(OrderService orderService,
			OrderRepository orderRepository, CustomerClient customerClient,
			CatalogClient catalogClient) {
		super();
		this.orderRepository = orderRepository;
		this.customerClient = customerClient;
		this.catalogClient = catalogClient;
		this.orderService = orderService;
	}

	@ModelAttribute("items")
	public Collection<Item> items() {
		return catalogClient.findAll();
	}

	@ModelAttribute("customers")
	public Collection<Customer> customers() {
		return customerClient.findAll();
	}

	/**
	 * list all the orders
	 * @return
	 */
	@RequestMapping(value="/list", method = RequestMethod.GET, produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> orderList() {
		ResponseEntity<?> results = null;
		try {

			results = new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);

		}catch(Exception e) {
			results = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return results;
	}

	/**
	 * Add an order
	 */
	@RequestMapping(value="/addLine", method = RequestMethod.POST, consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> addLine(@RequestBody OrderEntity order) {
		ResponseEntity<?> results = null;
		try {
			order.addLine(0, catalogClient.findAll().iterator().next().getItemId());

			results = new ResponseEntity<>(order, HttpStatus.OK);

		}catch(Exception e) {
			results = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return results;
	}

	/**
	 * retrieve the order using orderid
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET, produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> get(@PathVariable("id") long id) {
		ResponseEntity<?> results = null;

		try {

			results = new ResponseEntity<>( orderRepository.findOne(id), HttpStatus.OK);

		}catch(Exception e) {
			results = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return results;
	}

	/**
	 * create an order
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/create", method = RequestMethod.POST, consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> post(@RequestBody OrderEntity order) {

		ResponseEntity<?> results = null;

		try {
			order = orderService.order(order);
			if(order != null) {
				results = new ResponseEntity<>( order, HttpStatus.OK);   
			}

		}catch(Exception e) {
			results = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return results;
	}

	/**
	 * Delete an Order
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> post(@PathVariable("id") long id) {
		ResponseEntity<?> results = null;

		try {

			orderRepository.delete(id);

			if(orderRepository.findOne(id) != null) {
				results = new ResponseEntity<>("Successfull deleted" , HttpStatus.OK);   
			}

		}catch(Exception e) {
			results = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return results;
	}

}
