package com.sdoran.fetchrewardschallenge.service;

import java.util.List;
import java.util.Map;

import com.sdoran.fetchrewardschallenge.model.Balance;
import com.sdoran.fetchrewardschallenge.model.SpentBalance;
import com.sdoran.fetchrewardschallenge.model.Transaction;
import org.springframework.http.ResponseEntity;

public interface PointsService {

    public ResponseEntity<Map<String, Integer>> getAllBalances();

    public ResponseEntity<List<Balance>> spendPoints(final SpentBalance spentBalance);

    public ResponseEntity<Transaction> createTransaction(final Transaction transaction);

}
