package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.List;
import java.util.Optional;

@Service
public class BidListService {

    @Autowired
    BidListRepository bidListRepository;

    //get all bids
    public List<BidList> getAll(){
        return bidListRepository.findAll();
    }

    //get bid by id
    public BidList getById(int id){
        Optional<BidList> optionalBidList= bidListRepository.findById(id);
        if (optionalBidList.isEmpty()){
            Logger.error("No bidlist found by id",id);
            throw new RuntimeException();
        }
        return optionalBidList.get();
    }

    //save a new bid
    public void saveBid(BidList bid) {
        bidListRepository.save(bid);
    }

    //update a bid
    public void updateById(Integer id, BidList bidList) {
        bidListRepository.deleteById(id);
        bidListRepository.save(bidList);
    }

    //delete a bid
    public void deleteById(Integer id) {
        bidListRepository.deleteById(id);
    }
}
