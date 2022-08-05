package com.cognizant.balanceCRUD.controller;

import com.cognizant.balanceCRUD.models.Balance;
import com.cognizant.balanceCRUD.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*", exposedHeaders = "*",allowedHeaders = "*")
public class BalanceController {

    @Autowired
    BalanceService balanceService;

    @GetMapping("/balance")
    @Cacheable(value="balance", key="#productId")
    public ResponseEntity<?> getBalance(@RequestParam String productId, @RequestParam(required = false) String locationId){

        try{
            if( locationId == null || locationId.equals("")){
                List<Balance> balanceList = balanceService.findByProductId(productId);
                return ResponseEntity.ok(balanceList);
            }
            else {
               Balance  balance = balanceService.findByProductIdAndLocationId(productId, locationId);
                return ResponseEntity.ok(balance);
            }

        }catch(EntityNotFoundException e){
            return  ResponseEntity.status(404).body(e.getMessage());
        }
        catch(IllegalArgumentException e){
            return  ResponseEntity.status(400).body(e.getMessage());
        }
    }

//    @GetMapping("/balance")
//    public ResponseEntity<?> getAllBalance(){
//        return ResponseEntity.ok(balanceService.getAllBalance());
//    }
}
