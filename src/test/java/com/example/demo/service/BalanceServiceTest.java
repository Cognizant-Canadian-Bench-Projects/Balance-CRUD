package com.example.demo.service;

import com.cognizant.balanceCRUD.dao.BalanceRepository;
import com.cognizant.balanceCRUD.models.Balance;
import com.cognizant.balanceCRUD.service.BalanceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BalanceServiceTest {

    @Mock
    BalanceRepository balanceRepository;

    @InjectMocks
    BalanceService balanceService;

    Balance balance1;
    Balance balance2;
    Balance balance3;
    Balance balance4;

    @BeforeEach
    void init(){
        balance1 = new Balance(1,2,3,123);
        balance2 = new Balance(2,3,4,234);
        balance3 = new Balance(3,4,5,345);
        balance4 = new Balance(4,5,6,456);
    }

    @Test
    void findByProductId_Positive(){
        List<Balance> balanceList = new ArrayList<>();
        balanceList.add(balance1);
        balanceList.add(balance2);
        when(balanceRepository.findByProductId(2)).thenReturn(balanceList);
        List<Balance> actual=balanceService.findByProductId("2");
        assertThat(actual).isEqualTo(balanceList);
    }

    @Test
    void findByProductId_Negative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            balanceService.findByProductId(" ");
        });
    }

    @Test
    void findByProductIdAndLocationId_Positive(){
        when(balanceRepository.findByProductIdAndLocationId(2,3)).thenReturn(balance1);
        Balance actual=balanceService.findByProductIdAndLocationId("2","3");
        assertThat(actual).isEqualTo(balance1);
    }

    @Test
    void findByProductIdAndLocationId_Negative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            balanceService.findByProductId(" ");
        });
    }

    @Test
    void findByProductIdAndLocationIdNotFound_Negative(){
        when(balanceRepository.findByProductIdAndLocationId(2,3)).thenReturn(null);
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            Balance actual=balanceService.findByProductIdAndLocationId("2","3");
        });
        }

    @Test
    void findByProductIdAndLocationIdNumberFormat_Negative(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Balance actual=balanceService.findByProductIdAndLocationId("a","b");
        });
    }

    @Test
    void findByProductIdNotFound_Negative(){
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            balanceService.findByProductId("2");
        });
    }
    }
