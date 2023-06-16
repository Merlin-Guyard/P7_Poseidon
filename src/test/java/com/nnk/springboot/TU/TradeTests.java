package com.nnk.springboot.TU;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;
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
public class TradeTests {

    @Autowired
    private TradeService tradeService;

    @Autowired
    private TradeRepository tradeRepository;

    @BeforeEach
    public void clear() {
        tradeRepository.deleteAll();
    }

    @Test
    public void getAll() {

        // Act
        Trade trade = new Trade("Account Test", "Type Test", 10);
        tradeRepository.save(trade);

        // Perform
        List<Trade> trades = tradeService.getAll();

        //Assert
        assertNotNull(trades.get(0));
        assertEquals(trades.get(0).getAccount(),"Account Test");
    }

    @Test
    public void getById() {

        // Act
        Trade trade = new Trade("Account Test", "Type Test", 10);
        tradeRepository.save(trade);

        // Perform
        Trade trade1 = tradeService.getById(trade.getTradeId());

        //Assert
        assertEquals(trade1.getAccount(),"Account Test");
    }

    @Test
    public void update() {

        // Act
        Trade trade = new Trade("Account Test", "Type Test", 10);
        Trade tradeUpdated = new Trade("Account Test2", "Type Test", 10);
        tradeRepository.save(trade);

        // Perform
        tradeService.updateById(trade.getTradeId(), tradeUpdated);

        //Assert
        Trade trade1 = tradeService.getById(tradeUpdated.getTradeId());
        assertEquals(trade1.getAccount(),"Account Test2");
        assertEquals(trade1.getType(),"Type Test");
    }

    @Test
    public void delete() {

        // Act
        Trade trade = new Trade("Account Test", "Type Test", 10);
        tradeRepository.save(trade);

        // Perform
        tradeService.deleteById(trade.getTradeId());

        //Assert
        List<Trade> trades = tradeService.getAll();
        assertThat(trades.isEmpty()).isTrue();
    }
}
