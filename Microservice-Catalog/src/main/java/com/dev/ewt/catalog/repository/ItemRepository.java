/**
 * 
 */
package com.dev.ewt.catalog.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dev.ewt.catalog.entity.Item;

/**
 * @author Sid
 *
 */
@RepositoryRestResource(collectionResourceRel = "catalog", path = "catalog")
public interface ItemRepository extends PagingAndSortingRepository<Item, Long>{

	List<Item> findByName(@Param("name") String name);

	List<Item> findByNameContaining(@Param("name") String name);

}
