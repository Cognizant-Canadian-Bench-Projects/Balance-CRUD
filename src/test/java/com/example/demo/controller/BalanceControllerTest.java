package com.example.demo.controller;

import com.cognizant.balanceCRUD.controller.BalanceController;
import com.cognizant.balanceCRUD.models.Balance;
import com.cognizant.balanceCRUD.service.BalanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BalanceControllerTest {

    @Mock
    private BalanceService balanceService;

    @InjectMocks
    private BalanceController balanceController;

    @BeforeEach
    void setUp(){

    }

    @Test
    void findByProductId_PositiveTest(){
        List<Balance> balanceList = new ArrayList<>();
        when(balanceService.findByProductId("2")).thenReturn(balanceList);
        ResponseEntity actual = balanceController.getBalance("2","");
        assertThat(actual.getStatusCodeValue()).isEqualTo(200);
        assertThat(actual.getBody()).isEqualTo(balanceList);
    }

    @Test
    void findByProductIdAndLocationId_PositiveTest(){
        Balance balance1 = new Balance(1,2,3,200);
        when(balanceService.findByProductIdAndLocationId("2","3")).thenReturn(balance1);
        ResponseEntity actual = balanceController.getBalance("2","3");
        assertThat(actual.getStatusCodeValue()).isEqualTo(200);
        assertThat(actual.getBody()).isEqualTo(balance1);
    }
    @Test
    void findByProductId_NegativeTest_404(){
        when(balanceService.findByProductId("2")).thenThrow(EntityNotFoundException.class);
        ResponseEntity actual = balanceController.getBalance("2","");
        assertThat(actual.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    void findByLocationId_NegativeTest_404(){
        when(balanceService.findByProductIdAndLocationId("1","2")).thenThrow(EntityNotFoundException.class);
        ResponseEntity actual = balanceController.getBalance("1","2");
        assertThat(actual.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    void findByProductId_NegativeTest(){
        when(balanceService.findByProductId("a")).thenThrow(IllegalArgumentException.class);
        ResponseEntity actual = balanceController.getBalance("a","");
        assertThat(actual.getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    void findByLocationId_NegativeTest(){
        when(balanceService.findByProductIdAndLocationId("1","a")).thenThrow(IllegalArgumentException.class);
        ResponseEntity actual = balanceController.getBalance("1","a");
        assertThat(actual.getStatusCodeValue()).isEqualTo(400);
    }
}
