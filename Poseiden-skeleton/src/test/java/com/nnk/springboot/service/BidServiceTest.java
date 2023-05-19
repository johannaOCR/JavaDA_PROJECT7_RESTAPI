package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
public class BidServiceTest {

    private static final Logger logger = LogManager.getLogger("BidListServiceTest");

    @Mock
    private BidListRepository bidListRepository;

    @InjectMocks
    private BidListService bidListService;

    @Test
    public void bidListSaveTest() {
        // GIVEN
        BidList bid = new BidList("Account Test", "Type Test", 10d);

        // WHEN
        when(bidListRepository.save(bid)).thenReturn(bid);
        bid = bidListService.addBidList(bid);

        // THEN
        Assert.assertNotNull(bid);
        Assert.assertEquals(bid.getBidQuantity(), 10d, 10d);
    }

    @Test
    public void bidListUpdateTest() {
        // GIVEN
        BidList bid = new BidList("Account Test", "Type Test", 10d);
        Optional<BidList> bidListOptional = java.util.Optional.of(new BidList("Account Test", "Type Test", 10d));

        // WHEN
        when(bidListRepository.findById(anyInt())).thenReturn(bidListOptional);
        bid.setAccount("changement account");
        boolean response = bidListService.updateBidList(1, bid);

        // THEN
        Assert.assertTrue(response);
        verify(bidListRepository, times(1)).save(any());
    }

    @Test
    public void bidListFindAllTest() {
        // WHEN
        bidListService.getAllBidList();
        // THEN
        verify(bidListRepository, times(1)).findAll();
    }

    @Test
    public void bidListFindByIdTest() {
        // GIVEN
        BidList bid = new BidList("Account Test", "Type Test", 10d);
        bid.setId(1);
        Optional<BidList> bidListOptional = java.util.Optional.of(new BidList("Account Test", "Type Test", 10d));

        // WHEN
        when(bidListRepository.findById(anyInt())).thenReturn(bidListOptional);
        BidList result = bidListService.getBidListById(1);
        // THEN
        Assert.assertNotNull(result);
    }

    @Test
    public void bidListDeleteTest() {
        // GIVEN
        BidList bid = new BidList("Account Test", "Type Test", 10d);

        // WHEN
        when(bidListRepository.findById(anyInt())).thenReturn(java.util.Optional.of(bid));
        boolean response = bidListService.deleteBidList(1);

        // THEN
        Assert.assertTrue(response);
        verify(bidListRepository, times(1)).deleteById(1);
    }
}
