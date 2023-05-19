package com.nnk.springboot.repository;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@RunWith(SpringRunner.class)
public class BidRepositoryTest {
    private static final Logger logger = LogManager.getLogger("BidListRepositoryTest");

    @Autowired
    private BidListRepository bidListRepository;

    private BidList bid;

    @Before
    public void setUp() {
        this.bid = new BidList("Account Test", "Type Test", 10d);
    }

    @Test
    public void bidListSaveTest() {

        // Save
        bid = bidListRepository.save(bid);
        Assert.assertNotNull(bid.getId());
        Assert.assertEquals(bid.getBidQuantity(), 10d, 10d);
    }

    @Test
    public void updateTest() {
        // Update
        bid = bidListRepository.save(bid);
        bid.setBidQuantity(20d);
        bid = bidListRepository.save(bid);
        Assert.assertEquals(bid.getBidQuantity(), 20d, 20d);
    }

    @Test
    public void findAllTest() {
        // Find
        bid = bidListRepository.save(bid);
        List<BidList> listResult = new ArrayList<>();
        Iterable<BidList> iterable = bidListRepository.findAll();
        iterable.forEach(listResult::add);
        Assert.assertTrue(listResult.size() > 0);
    }

    @Test
    public void deleteByIdTest() {
        // Delete
        bid = bidListRepository.save(bid);
        Integer id = bid.getId();
        bidListRepository.delete(bid);
        Optional<BidList> bidList = bidListRepository.findById(id);
        Assert.assertFalse(bidList.isPresent());
    }


}
