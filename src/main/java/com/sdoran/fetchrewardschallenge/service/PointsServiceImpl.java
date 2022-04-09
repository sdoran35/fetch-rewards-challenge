package com.sdoran.fetchrewardschallenge.service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sdoran.fetchrewardschallenge.model.Balance;
import com.sdoran.fetchrewardschallenge.model.SpentBalance;
import com.sdoran.fetchrewardschallenge.model.Transaction;

@Service(value = "PointsServiceImpl")
public class PointsServiceImpl implements PointsService {

    //Setup some global variables
    Map<String, List<Integer>> payerTransactionMap = new HashMap<>();
    Map<Integer, Transaction> transactionMap = new LinkedHashMap<>();
    static int payerBalance;
    static int payerId = 0;

    /**
     * Create a new transaction
     *
     * @param transaction the transaction data to use
     * @return the result
     */
    public ResponseEntity<String> createTransaction(final Transaction transaction) {
        if(payerTransactionMap.containsKey(transaction.getPayer())) {

            List<Integer> userIdList= payerTransactionMap.get(transaction.getPayer());
            if(transaction.getPoints()<0) {

                int amount = -1*transaction.getPoints();

                int balance  =  getSingleBalance(transaction.getPayer());

                if(balance<amount) return new ResponseEntity<String>("Transaction Unsucessful", HttpStatus.BAD_REQUEST);


                List<Integer> idToRemove= new ArrayList<>();

                for(int i : userIdList) {
                    Transaction  trans  = transactionMap.get(i);
                    if(trans.getPoints()>=amount) {
                        trans.setPoints(trans.getPoints()-amount);
                        transactionMap.put(i, trans);
                        break;
                    }
                    else {
                        amount-=trans.getPoints();
                        trans.setPoints(0);
                        transactionMap.put(i, trans);
                    }

                    if(trans.getPoints()==0) {
                        transactionMap.remove(i);
                        idToRemove.add(i);
                    }
                }

                idToRemove.forEach(userIdList::remove);

                payerTransactionMap.put(transaction.getPayer(), userIdList);

                payerBalance+=transaction.getPoints();
            }
            else {
                userIdList.add(++payerId);
                payerTransactionMap.put(transaction.getPayer(), userIdList);
                transactionMap.put(payerId, transaction);
                payerBalance+=transaction.getPoints();

            }
        }
        else {
            if(transaction.getPoints()<=0) return new ResponseEntity<String>("Transaction Unsucessful", HttpStatus.BAD_REQUEST);

            List<Integer> userIdList= new ArrayList<>();
            userIdList.add(++payerId);
            payerTransactionMap.put(transaction.getPayer(), userIdList);
            transactionMap.put(payerId, transaction);
            payerBalance+=transaction.getPoints();
        }


        return new ResponseEntity<String>("Transaction Sucessful", HttpStatus.OK);
    }

    public ResponseEntity<List<SpentBalance>> spendPoints(int points) {

        List<SpentBalance> deductedBalanceList = new ArrayList<SpentBalance>();
        if(points>payerBalance) return new ResponseEntity<List<SpentBalance>>(deductedBalanceList, HttpStatus.BAD_REQUEST);

        List<Integer> removeIds = new ArrayList<>();
        for(int i : transactionMap.keySet()) {

            if(transactionMap.get(i).getPoints()>=points) {

                deductedBalanceList.add(new SpentBalance(transactionMap.get(i).getPayer(), -1*points, LocalDateTime.now()));

                System.out.println(transactionMap.get(i).getPayer() + ", -" + points+ ", "+LocalDateTime.now());

                transactionMap.get(i).setPoints(transactionMap.get(i).getPoints()-points);
                if(transactionMap.get(i).getPoints()==0) {
                    payerTransactionMap.get(transactionMap.get(i).getPayer()).remove(i);
                    removeIds.add(i);
                }
                break;
            }
            else {
                if(transactionMap.get(i).getPoints()>0) {
                    deductedBalanceList.add(new SpentBalance(transactionMap.get(i).getPayer(), -1*transactionMap.get(i).getPoints(), LocalDateTime.now()));
                    System.out.println(transactionMap.get(i).getPayer() + ", -" + transactionMap.get(i).getPoints()+ ", "+LocalDateTime.now());
                    points-=transactionMap.get(i).getPoints();
                    transactionMap.get(i).setPoints(0);
                    payerTransactionMap.get(transactionMap.get(i).getPayer()).remove(i);
                    removeIds.add(i);
                }
            }

        }

        for(int i : removeIds) {
            transactionMap.remove(i);
        }
        payerBalance-=points;

        return new ResponseEntity<List<SpentBalance>>(deductedBalanceList, HttpStatus.OK);
    }

//    /**
//     * Return a list of all point balances
//     *
//     * @return the list of point balances
//     */
//    public ResponseEntity<List<Balance>> getAllBalances() {
//        return new ResponseEntity<>(payerTransactionMap.keySet().stream()
//                .map(payee -> new Balance(payee, getSingleBalance(payee)))
//            .collect(Collectors.toList()), HttpStatus.OK);
//}
//
//    /**
//     * Helper function to get points balance
//     *
//     * @param payer payer
//     * @return the payer point balance
//     */
//    private Integer getSingleBalance(final String payer) {
//        return payerTransactionMap.get(payer).stream().mapToInt(id -> id).map(i -> transactionMap.get(i).getPoints()).sum();
//    }


    public ResponseEntity<List<Balance>> getAllBalances() {

        List<Balance> allUsersbalance = new ArrayList<Balance>();
        for(String payee : payerTransactionMap.keySet()) {
            int amount=0;
            List<Integer> ids = payerTransactionMap.get(payee);

            for(int id : ids) {
                amount+= transactionMap.get(id).getPoints();
            }

            allUsersbalance.add(new Balance(payee, amount));

            System.out.println(payee +" : " + amount);
        }

        return new ResponseEntity<>(allUsersbalance,HttpStatus.OK);
    }



    private Integer getSingleBalance(String user) {
        List<Integer> ids = payerTransactionMap.get(user);

        int amount=0;
        for(int id : ids) {
            amount+= transactionMap.get(id).getPoints();
        }

        return amount;
    }

}