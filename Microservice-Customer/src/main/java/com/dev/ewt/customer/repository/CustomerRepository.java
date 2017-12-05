/**
 * 
 */
package com.dev.ewt.customer.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dev.ewt.customer.entity.CustomerEntity;

/**
 * @author Sid
 *
 */
@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
public interface CustomerRepository  extends PagingAndSortingRepository<CustomerEntity, Long> {

	List<CustomerEntity> findByName(@Param("name") String name);
}
