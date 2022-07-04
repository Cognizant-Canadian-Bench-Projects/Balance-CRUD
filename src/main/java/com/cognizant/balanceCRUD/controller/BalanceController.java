package com.cognizant.balanceCRUD.controller;

import com.cognizant.balanceCRUD.models.Balance;
import com.cognizant.balanceCRUD.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@CrossOrigin(originPatterns = "*", exposedHeaders = "*",allowedHeaders = "*")
public class BalanceController {

    @Autowired
    BalanceService balanceService;

    @GetMapping("/balance")
    public ResponseEntity<?> getBalance(@RequestParam String productId, @RequestParam String locationId){

        Balance balance = new Balance();
        try{
            if(locationId==""){
                balance = balanceService.findByProductId(productId);
            }
            else {
                balance = balanceService.findByProductIdAndLocationId(productId, locationId);
            }
            return ResponseEntity.ok(balance);
        }catch(EntityNotFoundException | IllegalArgumentException e){
            return  ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
