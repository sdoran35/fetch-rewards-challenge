# FetchRewardsChallenge

Fetch Rewards BackEnd Interview Assessment

<br/>

**Running the Application (Local):**

- Clone the project in a local repository
- Import the project in your IDE of Choice (Eclipse or IntelliJ or VSCode)
    - You will import as a maven project
- Run the created `FetchRewardsChallengeApplication` run config
-

**Running the Application (Heroku Application):**

- This project is also deployed to a heroku application. This allows for running the application without needing to
  clone and build the project.
- The below steps for using the endpoints is the same, just with a different URL

**API Routes:**

* **Base URL: /api/v1/points/**
* POST /processTransaction : Completes and processes a transaction. This will also save the transaction information to
  the `Transaction List`
* POST /spendPoints : Process spend points
* GET /pointsBalance : Provides a Map of all payers and points

<br/>

## **Using the API with `POSTMAN` or `INSOMNIA`**

---

## 1. Add Points to the User

<br/>

### Endpoint: `/processTransaction`

- This is a `POST` request. <br/>
  We can use any of the following Request Body:
  <br/><br/>

 ```JSON
   {
  "payer": "DANNON",
  "points": 1000,
  "timestamp": "2020-11-02T14:00:00Z"
}
 ```

  <br/>

- Give all the details of the transaction that you want to process, using the above format.
- The name of the payer is to be given as a `STRING`
- The points of the payer are given as `INTEGER`.
- If the transaction is successful them the result will be returned as "Transaction Processed" with HTTP Status Code
  of `201 - Created` and the created records
- If the format is incorrect, it will return "Transaction Failed to Process" with HTTP Status Code of `400 - Bad Request`
- We can have transactions with negative points for a particular user which will deduct the points given by the same
  user.
  <br/>
  <br/>

---

## 2. View Point Balance

<br/>

### Endpoint: `/pointsBalance`

- This is a `GET` request. <br/>
- This API call gives all the POINTS of the user along with the payer name.
- Then output will be in a JSON format

<br/>

```JSON
    [
  {
    "payerName": "payer_name",
    "balance": "payer_balance"
  }
]
```

<br/>

- Give all the details of all payers and their respective balance
- The name of the payer is returned as a `STRING`
- The points of the payer is returned as `INTEGER`.
- If the lookup is successful them the result will be returned as "Points Lookup Processed" with HTTP Status Code
  of `201 - Created`
- If the lookup fails, it will return "Points Lookup Failed to Process with HTTP Status Code
  of `400 - Bad Request`
  <br/>
  <br/>

---

## 3. Spend User Points

<br/>

### Endpoint: `/spendPoints`

- This is a `POST` request. <br/>
  We can use the following Request Body:
  <br/><br/>

 ```JSON
{
  "points": "points_to_spend"
}
 ```

  <br/>

- We deduct the points from each transaction in a chronological order.
- The output will be in the following format.

 ```JSON
   [
  {
    "payer_name": "DANNON",
    "payer_balance": "-100",
    "time": "2020-10-31T10:00:00Z"
  }
]
 ```

  <br/>
  <br/>