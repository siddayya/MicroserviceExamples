/**
 * Microservice implementation of the catalog service
 */
package com.dev.ewt.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.ewt.catalog.repository.ItemRepository;

/**
 * @author Sid
 *
 */
@RestController
@RequestMapping("/v1/catalog")
public class CatalogController {

	private final ItemRepository itemRepository;

	@Autowired
	public CatalogController(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	
	/**
	 * get an item from teh repository using teh id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces ={MediaType.APPLICATION_JSON_VALUE }
	)
	public ResponseEntity<?>  Item(@PathVariable("id") long id) {
		ResponseEntity<?> results = null;
		try {
			results = new ResponseEntity<>(itemRepository.findOne(id), HttpStatus.OK);
			
		} catch (Exception e) {
			//logger.error(": [{}]", ItemNumber, e);
			//results = ExceptionsHelper.handleServiceExceptions(e);
			results = new ResponseEntity<>("uncought error", HttpStatus.NOT_FOUND);
		}
		
		return results;
	}

	/**
	 * get all the items from teh repository
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE}
	)
	public ResponseEntity<?>  ItemList() {
		ResponseEntity<?> results = null;
		try {
			results = new ResponseEntity<>(itemRepository.findAll(), HttpStatus.OK);
			
		} catch (Exception e) {
			//logger.error(": [{}]", ItemNumber, e);
			//results = ExceptionsHelper.handleServiceExceptions(e);
			results = new ResponseEntity<>("uncought error", HttpStatus.NOT_FOUND);
		}
		
		return results;
	}

 /**
  * Add a new Item into the repository
  * @param item
  * @return
  */
	@RequestMapping(value = "/addItem", method = RequestMethod.POST, consumes ={MediaType.APPLICATION_JSON_VALUE}
	)
	public ResponseEntity<?> post(@RequestBody com.dev.ewt.catalog.entity.Item item) {
		ResponseEntity<?> results = null;
		try {
			item = itemRepository.save(item);
			if(item!=null) {
				results = new ResponseEntity<>(item, HttpStatus.OK);
			}else {
				throw new Exception("Error Saving item ");
			}
		} catch (Exception e) {
			//logger.error(": [{}]", ItemNumber, e);
			//results = ExceptionsHelper.handleServiceExceptions(e);
			results = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return results;
	}
	
	/**
	 * Update an item of the repository
	 * @param id
	 * @param item
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes ={MediaType.APPLICATION_JSON_VALUE}
			)
	public ResponseEntity<?> put(@PathVariable("id") long id, com.dev.ewt.catalog.entity.Item item) {
		
		ResponseEntity<?> results = null;
		try {
			
			item.setId(id);
			itemRepository.save(item);
			
			results = new ResponseEntity<>(item, HttpStatus.OK);
			
		} catch (Exception e) {
			//logger.error(": [{}]", ItemNumber, e);
			//results = ExceptionsHelper.handleServiceExceptions(e);
			results = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return results;

	}

	/**
	 * Search an item from teh repository using teh name of an item
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/search/{name}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> search(@PathVariable("name") String name) {
		ResponseEntity<?> results = null;
		try {
			
			results = new ResponseEntity<>(itemRepository.findByNameContaining(name), HttpStatus.OK);
			
		} catch (Exception e) {
			//logger.error(": [{}]", ItemNumber, e);
			//results = ExceptionsHelper.handleServiceExceptions(e);
			results = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return results;
	}

	/**
	 * Delete an item from the repository
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		ResponseEntity<?> results = null;
		try {
			
			itemRepository.delete(id);
			com.dev.ewt.catalog.entity.Item item = itemRepository.findOne(id);
			if(item==null) {
			   results = new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
			}
		} catch (Exception e) {
			//logger.error(": [{}]", ItemNumber, e);
			//results = ExceptionsHelper.handleServiceExceptions(e);
			results = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return results;
	}

}
