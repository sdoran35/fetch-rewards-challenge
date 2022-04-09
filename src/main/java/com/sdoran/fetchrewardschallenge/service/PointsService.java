package com.sdoran.fetchrewardschallenge.service;

import java.util.List;
import java.util.Map;

import com.sdoran.fetchrewardschallenge.model.Balance;
import com.sdoran.fetchrewardschallenge.model.Points;
import com.sdoran.fetchrewardschallenge.model.SpentBalance;
import com.sdoran.fetchrewardschallenge.model.Transaction;
import org.springframework.http.ResponseEntity;

public interface PointsService {

    public ResponseEntity<List<Balance>> getAllBalances();

    public ResponseEntity<List<SpentBalance>> spendPoints(final int spentBalance);

    public ResponseEntity<String> createTransaction(final Transaction transaction);

}
