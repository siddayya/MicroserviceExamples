/**
 * 
 */
package com.dev.ewt.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.ewt.customer.entity.CustomerEntity;
import com.dev.ewt.customer.repository.CustomerRepository;

/**
 * @author Sid
 *
 */
@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

	private CustomerRepository customerRepository;

	@Autowired
	public CustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	/**
	 * get a customer using his id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces ={MediaType.APPLICATION_JSON_VALUE }
    )
	public ResponseEntity<?> customer(@PathVariable("id") long id) {
		ResponseEntity<?> results = null;
		try {
			results = new ResponseEntity<>(customerRepository.findOne(id), HttpStatus.OK);
			
		} catch (Exception e) {
			//logger.error(": [{}]", ItemNumber, e);
			//results = ExceptionsHelper.handleServiceExceptions(e);
			results = new ResponseEntity<>("uncought error", HttpStatus.NOT_FOUND);
		}
		return results;
   }

	/**
	 * List all teh customers
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces ={MediaType.APPLICATION_JSON_VALUE }
    )
	public ResponseEntity<?> customerList() {
		
		ResponseEntity<?> results = null;
		try {
			results = new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
			
		} catch (Exception e) {
			//logger.error(": [{}]", ItemNumber, e);
			//results = ExceptionsHelper.handleServiceExceptions(e);
			results = new ResponseEntity<>("uncought error", HttpStatus.NOT_FOUND);
		}
		return results;
	}

	/**
	 * 
	 * @param customer
	 * @param httpRequest
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes ={MediaType.APPLICATION_JSON_VALUE }
    )
	public ResponseEntity<?> post(@RequestBody CustomerEntity customer) {
		ResponseEntity<?> results = null;
		try {
			customer = customerRepository.save(customer);
			if(customer!=null) {
				results = new ResponseEntity<>(customer, HttpStatus.OK);
			}
		} catch (Exception e) {
			//logger.error(": [{}]", ItemNumber, e);
			//results = ExceptionsHelper.handleServiceExceptions(e);
			results = new ResponseEntity<>("uncought error", HttpStatus.NOT_FOUND);
		}
		return results;
	}
    
	
	/**
	 * Update an existing customer
	 * @param id
	 * @param customer
	 * @param httpRequest
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces ={MediaType.APPLICATION_JSON_VALUE }, consumes= {MediaType.APPLICATION_JSON_VALUE}
		    )
	public ResponseEntity<?> put(@PathVariable("id") long id, @RequestBody CustomerEntity customer) {
		ResponseEntity<?> results = null;
		try {
			customer.setId(id);
			customer = customerRepository.save(customer);
			if(customer!=null) {
				results = new ResponseEntity<>(customer, HttpStatus.OK);
			}
		} catch (Exception e) {
			//logger.error(": [{}]", ItemNumber, e);
			//results = ExceptionsHelper.handleServiceExceptions(e);
			results = new ResponseEntity<>("uncought error", HttpStatus.NOT_FOUND);
		}
		return results;
	}

	/**
	 * delete a customer
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		ResponseEntity<?> results = null;
		try {
			customerRepository.delete(id);
			if(customerRepository.findOne(id)==null) {
				results = new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
			}
		} catch (Exception e) {
			//logger.error(": [{}]", ItemNumber, e);
			//results = ExceptionsHelper.handleServiceExceptions(e);
			results = new ResponseEntity<>("uncought error", HttpStatus.NOT_FOUND);
		}
		return results;
	}
}
