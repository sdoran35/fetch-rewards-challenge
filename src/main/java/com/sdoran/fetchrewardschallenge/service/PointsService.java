package com.sdoran.fetchrewardschallenge.service;

import com.sdoran.fetchrewardschallenge.model.Balance;
import com.sdoran.fetchrewardschallenge.model.SpentBalance;
import com.sdoran.fetchrewardschallenge.model.Transaction;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PointsService {

    public ResponseEntity<List<Balance>> getAllBalances();

    public ResponseEntity<List<SpentBalance>> spendPoints(final Integer points);

    public ResponseEntity<String> createTransaction(final Transaction transaction);

}
