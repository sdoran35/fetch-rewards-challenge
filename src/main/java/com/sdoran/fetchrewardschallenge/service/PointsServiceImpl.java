package com.sdoran.fetchrewardschallenge.service;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sdoran.fetchrewardschallenge.model.Balance;
import com.sdoran.fetchrewardschallenge.model.SpentBalance;
import com.sdoran.fetchrewardschallenge.model.Transaction;
import org.springframework.web.bind.annotation.RequestBody;

@Service(value = "PointsServiceImpl")
public class PointsServiceImpl implements PointsService {

    List<Transaction> transactionList = new ArrayList<>();
    Map<String, Integer> balanceMap = new HashMap<>();

    /**
     *
     * @param transaction the transaction information to use
     * @return processed/failed to process
     */
    public ResponseEntity<Transaction> createTransaction(final Transaction transaction) {

        transactionList.add(transaction);

        if(balanceMap.containsKey(transaction.getPayerName()) && (transaction.getBalance() + balanceMap.get(transaction.getPayerName())) >= 0 ) {
            balanceMap.replace(transaction.getPayerName(), transaction.getBalance() + balanceMap.get(transaction.getPayerName()));
        }
        else {
            balanceMap.put(transaction.getPayerName(),transaction.getBalance());

        }

        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    /**
     *
     * @param spentBalance the points to spend
     * @return list of spent balance
     */
    public ResponseEntity<List<Balance>> spendPoints(@RequestBody SpentBalance spentBalance) {

        Map<String, Integer> tempMap = new HashMap<>();
        List<Balance> balanceList = new ArrayList<>();

        int amountToSpend = spentBalance.getBalance();

        transactionList.sort(Transaction::compareTo);

        for(Transaction transaction : transactionList) {
            int change;

            if(amountToSpend > 0) {
                if(amountToSpend <= transaction.getBalance()) {

                    change = amountToSpend;
                    amountToSpend = 0;
                } else {
                    amountToSpend -= transaction.getBalance();
                    change = transaction.getBalance();
                }

                balanceMap.replace(transaction.getPayerName(), balanceMap.get(transaction.getPayerName()) - change);

                if(!tempMap.containsKey(transaction.getPayerName())) {
                    tempMap.put(transaction.getPayerName(), change * -1);
                } else {
                    tempMap.replace(transaction.getPayerName(), tempMap.get(transaction.getPayerName()) + (change * -1));
                }


            }
        }

        for(Map.Entry<String, Integer> payer : tempMap.entrySet()) {
            Balance balance = new Balance(payer.getKey(), payer.getValue());
            balanceList.add(balance);
        }

        return new ResponseEntity<>(balanceList, HttpStatus.CREATED);
    }

    /**
     *
     * @return the list of all balances
     */
    public ResponseEntity<Map<String, Integer>> getAllBalances() {
        return new ResponseEntity<>(balanceMap, HttpStatus.OK);
    }

}
