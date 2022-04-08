package com.sdoran.fetchrewardschallenge.controller;

import java.util.List;

import com.sdoran.fetchrewardschallenge.model.Balance;
import com.sdoran.fetchrewardschallenge.model.Points;
import com.sdoran.fetchrewardschallenge.model.SpentBalance;
import com.sdoran.fetchrewardschallenge.model.Transaction;
import com.sdoran.fetchrewardschallenge.service.PointsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/points")
public class PointsController {

    private final PointsService pointsService;

    public PointsController(final PointsService pointsService) {

        this.pointsService = pointsService;
    }

    /**
     * Retrieves a list of all balances
     *
     * @return list of all balances
     */
    @RequestMapping(value = "/pointsBalance", method = RequestMethod.GET)
    public ResponseEntity<List<Balance>> getAllBalances() {
        return pointsService.getAllBalances();
    }

    /**
     * Creates a new transaction
     *
     * @param transaction the transaction data
     * @return the created transaction with generated {@code payer_id}
     */
    @RequestMapping(value = "/createTransaction", method = RequestMethod.POST)
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        return pointsService.createTransaction(transaction);
    }

    /**
     * Spend points
     *
     * @param points the points to spend
     *
     * @return list of spent points
     */
    @RequestMapping(value = "/spendPoints", method = RequestMethod.POST)
    public ResponseEntity<List<SpentBalance>> spendPoints(@RequestBody Points points) {
        return pointsService.spendPoints(points.getPoints());
    }




}
