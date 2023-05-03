package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BidListService {
    private static final Logger logger = LogManager.getLogger("BidListService");

    @Autowired
    private BidListRepository bidListRepository;

    public List<BidList> getAllBidList() {
        logger.info("Find all Bidlist processing");
        List<BidList> bidLists = new ArrayList<>();
        bidListRepository.findAll().forEach(bidLists::add);
        return bidLists;
    }

    public BidList addBidList(BidList bid){
        logger.info("Adding bidList");
        BidList bidList = bidListRepository.save(bid);
        return bidList;
    }

    public boolean updateBidList(int id,BidList bidList){
        BidList bidListFind = getBidListById(id);
        bidList.setId(id);

        if(bidListFind!=bidList){
            logger.info("Updating bidList");
            bidListRepository.save(bidList);
            return true;
        }
        logger.info("Updating failed");
        return false;
    }

    public boolean deleteBidList(Integer id){
        if(getBidListById(id)!=null){
            bidListRepository.deleteById(id);
            logger.info("Deleting bidList");
            return true;
        }
        logger.info("Deleting failed");
        return false;
    }

    public BidList getBidListById(Integer id) {
        if(bidListRepository.findById(id).isPresent()){
            logger.info("find by id processing");
            return bidListRepository.findById(id).get();
        }
        return null;
    }
}



