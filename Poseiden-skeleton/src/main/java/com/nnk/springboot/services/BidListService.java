package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidListService {
    private static final Logger logger = LogManager.getLogger("BidListService");

    @Autowired
    BidListRepository bidListRepository;

    public BidList addBidList(BidList bidList){
        BidList bidListResult = null;
        try {
           bidListResult = bidListRepository.save(bidList);

        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
        return bidListResult;
    }

}
