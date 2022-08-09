package com.cognizant.balanceCRUD.service;

import com.cognizant.balanceCRUD.dao.BalanceRepository;
import com.cognizant.balanceCRUD.models.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BalanceService {

    @Autowired
    BalanceRepository balanceRepository;

    public List<Balance> getAllBalance() {
        return balanceRepository.findAll();
    }

    public List<Balance> findByProductId(String productId) {
        try {
            int intProductId = Integer.parseInt(productId);
            List<Balance> balanceList = balanceRepository.findByProductId(intProductId);
            if (balanceList.isEmpty()) {
                throw new EntityNotFoundException("Product Id = " + productId + " not found");
            }
            return balanceList;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Product id is illegal");
        }
    }

    public Balance findByProductIdAndLocationId(String productId, String locationId) {
        try {
            int intProductId = Integer.parseInt(productId);
            int intLocationId = Integer.parseInt(locationId);
            Balance balance = balanceRepository.findByProductIdAndLocationId(intProductId, intLocationId);
            if (balance == null) {
                throw new EntityNotFoundException("Invalid product id or location id");
            }
            return balance;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Product id or location id is illegal");
        }

    }
}
