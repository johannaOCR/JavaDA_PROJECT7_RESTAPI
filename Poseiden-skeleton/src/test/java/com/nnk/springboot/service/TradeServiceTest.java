package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class TradeServiceTest {
    @Mock
    TradeRepository tradeRepository;
    @InjectMocks
    private TradeService tradeService;

    @Test
    public void addTradeTest() {
        //GIVEN
        Trade trade = new Trade("Trade Account", "Type");
        //WHEN
        tradeService.addTrade(trade);
        //THEN
        verify(tradeRepository, times(1)).save(trade);
    }

    @Test
    public void updateTradeTest() {
        //GIVEN
        Trade trade = new Trade("Trade Account", "Type");
        tradeService.addTrade(trade);
        trade.setAccount("Trade Account Update");
        //WHEN
        tradeService.updateTrade(trade);
        //THEN
        verify(tradeRepository, times(2)).save(trade);
    }

    @Test
    public void deleteByIdTradeTest() {
        //GIVEN
        Trade trade = new Trade("Trade Account", "Type");
        trade.setId(1);
        Integer id = trade.getId();
        //WHEN
        tradeService.deleteTrade(id);
        //THEN
        verify(tradeRepository, times(1)).deleteById(id);
    }

    @Test
    public void getByIdTradeTest() {
        //GIVEN
        Trade trade = new Trade("Trade Account", "Type");
        trade.setId(1);
        Integer id = trade.getId();
        //WHEN
        when(tradeRepository.findById(id)).thenReturn(java.util.Optional.of(trade));
        Trade result = tradeService.getTradeById(id).get();
        //THEN
        verify(tradeRepository, times(1)).findById(id);
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getAccount()).isEqualTo(trade.getAccount());
        assertThat(result.getType()).isEqualTo(trade.getType());
    }

    @Test
    public void getAllTradeTest() {
        //GIVEN
        Trade trade = new Trade("Trade Account", "Type");
        trade.setId(1);
        Integer id = trade.getId();
        //WHEN
        tradeService.getAllTrade();
        //THEN
        verify(tradeRepository, times(1)).findAll();
    }
}
