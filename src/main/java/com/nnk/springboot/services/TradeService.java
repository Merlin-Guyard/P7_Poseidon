package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

    @Autowired
    TradeRepository tradeRepository;


    //get all trades
    public List<Trade> getAll(){
        return tradeRepository.findAll();
    }

    //get trades by id
    public Trade getById(int id){
        Optional<Trade> optionalTrade= tradeRepository.findById(id);
        if (optionalTrade.isEmpty()){
            Logger.error("No trade found by id",id);
            throw new RuntimeException();
        }
        return optionalTrade.get();
    }

    //save a new trade
    public void saveTrade(Trade trade) {
        tradeRepository.save(trade);
    }

    //update a trade
    public void updateById(Integer id, Trade trade) {
        tradeRepository.deleteById(id);
        tradeRepository.save(trade);
    }

    //delete a trade
    public void deleteById(Integer id) {
        tradeRepository.deleteById(id);
    }
}
