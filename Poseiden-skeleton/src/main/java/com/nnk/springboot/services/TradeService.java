package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeService {
    private static final Logger logger = LogManager.getLogger("TradeService");
    @Autowired
    private TradeRepository tradeRepository;

    public List<Trade> getAllTrade(){
        return tradeRepository.findAll();
    }

    public void addTrade(Trade trade){
        logger.info("Adding trade");
        tradeRepository.save(trade);
    }

    public void updateTrade(Trade trade){
        logger.info("Updating trade");
        tradeRepository.save(trade);
    }

    public void deleteTrade(Integer id){
        logger.info("Deleting trade");
        tradeRepository.deleteById(id);
    }

    public Optional<Trade> getTradeById(Integer id) {
        return tradeRepository.findById(id);
    }
}
