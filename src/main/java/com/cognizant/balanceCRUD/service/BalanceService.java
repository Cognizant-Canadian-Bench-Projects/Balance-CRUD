package com.cognizant.balanceCRUD.service;

import com.cognizant.balanceCRUD.dao.BalanceRepository;
import com.cognizant.balanceCRUD.models.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.EntityNotFoundException;

@Service
public class BalanceService {

    @Autowired
    BalanceRepository balanceRepository;

    public Balance findByProductId(int productId){
        Balance balance = balanceRepository.findByProductId(productId);
        if(balance == null){
            throw new EntityNotFoundException("Product Id = " + productId + " not found");
        }
        return balance;
    }

    public Balance findByProductIdAndLocationId(int productId, int locationId){
        Balance balance = balanceRepository.findByProductIdAndLocationId(productId, locationId);
        if(balance == null){
            throw new EntityNotFoundException("Invalid product id or location id");
        }
        return balance;
    }
}
