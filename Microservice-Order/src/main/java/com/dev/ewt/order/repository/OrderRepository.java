/**
 * 
 */
package com.dev.ewt.order.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dev.ewt.order.entity.OrderEntity;
/**
 * @author Sid
 *
 */
@RepositoryRestResource(collectionResourceRel = "order", path = "order")
public interface OrderRepository extends PagingAndSortingRepository<OrderEntity, Long> {

	
}
