package com.cognizant.balanceCRUD.dao;

import com.cognizant.balanceCRUD.models.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Integer> {
    List<Balance> findByProductId(int productId);

    Balance findByProductIdAndLocationId(int productId, int locationId);
}
