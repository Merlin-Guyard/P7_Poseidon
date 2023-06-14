package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.List;
import java.util.Optional;

@Service
public class RuleNameService {

    @Autowired
    RuleNameRepository ruleNameRepository;

    public List<RuleName> getAll(){
        return ruleNameRepository.findAll();
    }

    public RuleName getById(int id){
        Optional<RuleName> optionalRuleName= ruleNameRepository.findById(id);
        if (optionalRuleName.isEmpty()){
            Logger.error("No RuleName found by id",id);
            throw new RuntimeException();
        }
        return optionalRuleName.get();
    }


    public void saveRuleName(RuleName bid) {
        ruleNameRepository.save(bid);
    }

    public void updateById(Integer id, RuleName ruleName) {
        ruleNameRepository.deleteById(id);ruleName.setId(id);
        ruleNameRepository.save(ruleName);
    }

    public void deleteById(Integer id) {
        ruleNameRepository.deleteById(id);
    }
}
