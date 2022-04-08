package com.sdoran.fetchrewardschallenge.service;

import com.sdoran.fetchrewardschallenge.model.Balance;
import com.sdoran.fetchrewardschallenge.model.SpentBalance;
import com.sdoran.fetchrewardschallenge.model.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service(value = "PointsServiceImpl")
public class PointsServiceImpl implements PointsService {

    Map<String, List<Integer>> userTransactionMap = new HashMap<>();
    Map<Integer, Transaction> transactionMap = new LinkedHashMap<>();
    private static Integer availableBalance = 0;
    private static Integer id = 0;

    /**
     *
     * @param transaction the transaction information to use
     * @return processed/failed to process
     */
    public ResponseEntity<String> createTransaction(final Transaction transaction) {

        if(userTransactionMap.containsKey(transaction.getPayerName())) {
            List<Integer> userIdList = userTransactionMap.get(transaction.getPayerName());

            if(transaction.getPoints() < 0) {
                int amount = -1 * transaction.getPoints();
                int balance =  getBalance(transaction.getPayerName());

                if (balance < amount) {
                    return new ResponseEntity<String>("Transaction failed to Process", HttpStatus.BAD_REQUEST);
                }


                for(int userId : userIdList) {
                    Transaction newTransaction = transactionMap.get(userId);

                    if(newTransaction.getPoints() >= amount) {
                        newTransaction.setPoints(newTransaction.getPoints() - amount);
                        transactionMap.put(userId, newTransaction);
                        break;
                    } else {
                        amount -= newTransaction.getPoints();
                        newTransaction.setPoints(0);
                        transactionMap.put(userId, newTransaction);
                    }

                    if(newTransaction.getPoints() == 0) {
                        transactionMap.remove(userId);
                        userIdList.remove(userId);
                    }
                }

                userTransactionMap.put(transaction.getPayerName(), userIdList);

                availableBalance += transaction.getPoints();
            } else {
                userIdList.add(id++);
                userTransactionMap.put(transaction.getPayerName(), userIdList);
                transactionMap.put(id, transaction);
                availableBalance += transaction.getPoints();
            }
        } else {
             if(transaction.getPoints() <= 0) {
                 return new ResponseEntity<String>("Transaction Failed to Process", HttpStatus.BAD_REQUEST);
             }

             List<Integer> userIdList = new ArrayList<>();

             userIdList.add(id++);
            userTransactionMap.put(transaction.getPayerName(), userIdList);
            transactionMap.put(id, transaction);
            availableBalance += transaction.getPoints();
        }

        return new ResponseEntity<String>("Transaction Processed", HttpStatus.CREATED);
    }

    /**
     *
     * @param points the points to spend
     * @return list of spent balance
     */
    public ResponseEntity<List<SpentBalance>> spendPoints(Integer points) {

        List<SpentBalance> spentBalanceList = new ArrayList<SpentBalance>();
        if(points > availableBalance) {
            return new ResponseEntity<List<SpentBalance>>(spentBalanceList, HttpStatus.BAD_REQUEST);
        }

        List<Integer> removeIds = new ArrayList<>();
        for(int i : transactionMap.keySet()) {

            if(transactionMap.get(i).getPoints() >= points) {

                spentBalanceList.add(new SpentBalance(transactionMap.get(i).getPayerName(), -1 * points, LocalDateTime.now()));

                System.out.println(transactionMap.get(i).getPayerName() + ", -" + points + ", " + LocalDateTime.now());

                transactionMap.get(i).setPoints(transactionMap.get(i).getPoints() - points);
                if(transactionMap.get(i).getPoints() == 0) {
                    userTransactionMap.get(transactionMap.get(i).getPayerName()).remove(i);
                    removeIds.add(i);
                }
                break;
            }
            else {
                if(transactionMap.get(i).getPoints()>0) {
                    spentBalanceList.add(new SpentBalance(transactionMap.get(i).getPayerName(), -1 * transactionMap.get(i).getPoints(), LocalDateTime.now()));
                    System.out.println(transactionMap.get(i).getPayerName() + ", -" + transactionMap.get(i).getPoints() + ", " + LocalDateTime.now());
                    points -= transactionMap.get(i).getPoints();
                    transactionMap.get(i).setPoints(0);
                    userTransactionMap.get(transactionMap.get(i).getPayerName()).remove(i);
                    removeIds.add(i);
                }
            }

        }

        for(int i : removeIds) {
            transactionMap.remove(i);
        }
        availableBalance -= points;

        return new ResponseEntity<List<SpentBalance>>(spentBalanceList, HttpStatus.OK);
    }

    /**
     *
     * @return the list of all balances
     */
    public ResponseEntity<List<Balance>> getAllBalances() {

        List<Balance> balanceList = new ArrayList<Balance>();
        for(String payee : userTransactionMap.keySet()) {
            int amount = 0;
            List<Integer> ids = userTransactionMap.get(payee);

            for(int id : ids) {
                amount+= transactionMap.get(id).getPoints();
            }

            balanceList.add(new Balance(payee, amount));

            System.out.println(payee + " : " + amount);
        }

        return new ResponseEntity<List<Balance>>(balanceList,HttpStatus.OK);
    }

    /**
     *
     * @param user user to lookup
     * @return
     */
    private Integer getBalance(final String user) {
        List<Integer> idList = userTransactionMap.get(user);

        int amount  = 0;

        for (int id : idList) {
            amount += transactionMap.get(id).getPoints();
        }

        return amount;
    }
}
