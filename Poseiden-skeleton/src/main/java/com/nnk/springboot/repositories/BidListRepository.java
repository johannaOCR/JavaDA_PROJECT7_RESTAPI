package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@DynamicUpdate
public interface BidListRepository extends CrudRepository<BidList, Integer> {

}
