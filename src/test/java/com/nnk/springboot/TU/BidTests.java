package com.nnk.springboot.TU;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@ActiveProfiles("test")
public class BidTests {

	@Autowired
	private BidListService bidListService;

	@Autowired
	private BidListRepository bidListRepository;

	@BeforeEach
	public void clear() {
		bidListRepository.deleteAll();
	}

	@Test
	public void getAll() {

		// Act
		BidList bid = new BidList("Account Test", "Type Test", 10d);
		bidListRepository.save(bid);

		// Perform
		List<BidList> bidLists = bidListService.getAll();

		//Assert
		assertNotNull(bidLists.get(0));
		assertEquals(bidLists.get(0).getAccount(),"Account Test");
	}

	@Test
	public void getById() {

		// Act
		BidList bid = new BidList("Account Test", "Type Test", 10d);
		bidListRepository.save(bid);

		// Perform
		BidList bidLists = bidListService.getById(bid.getBidListId());

		//Assert
		assertEquals(bidLists.getAccount(),"Account Test");
	}

	@Test
	public void update() {

		// Act
		BidList bid = new BidList("Account Test", "Type Test", 10d);
		BidList bidUpdated = new BidList("Account Test2", "Type Test", 10d);
		bidListRepository.save(bid);

		// Perform
		bidListService.updateById(bid.getBidListId(), bidUpdated);

		//Assert
		BidList bidLists = bidListService.getById(bidUpdated.getBidListId());
		assertEquals(bidLists.getAccount(),"Account Test2");
		assertEquals(bidLists.getType(),"Type Test");
	}

	@Test
	public void delete() {

		// Act
		BidList bid = new BidList("Account Test", "Type Test", 10d);
		bidListRepository.save(bid);

		// Perform
		bidListService.deleteById(bid.getBidListId());

		//Assert
		List<BidList> bidLists = bidListService.getAll();
		assertThat(bidLists.isEmpty()).isTrue();
	}
}
