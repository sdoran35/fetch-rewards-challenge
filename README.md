# FetchRewardsChallenge

Fetch Rewards BackEnd Interview Assessment

<br/>

### Hosted Access

The App is hosted at https://warm-mountain-06740.herokuapp.com
- This project is also deployed to a heroku application. This allows for running the application without needing to
  clone and build the project.
- The below steps for using the endpoints is the same, just with a different URL
- Heroku URL: The App is hosted at https://warm-mountain-06740.herokuapp.com

**Running the Application (Local):**

### Setup

#### 1. Verify Java
Open your terminal/command prompt and enter the following command
```
java -version
```
If your java version is 1.8 or higher, then continue below. Otherwise, update it to the most recent JDK.

Verify your version again.
```
java -version
```
#### 2. Clone the project from Git.
If you do not have git installed, [click here.](https://git-scm.com/downloads)

If you're unsure, open your terminal and type in
```
git --version
```

Once git is installed, clone the project from git

In your terminal, run the following command
```
$ git clone https://github.com/sdoran35/fetch-rewards-challenge
```
This will clone the repo to your local machine

#### 4. Build Project using Maven
This step not needed if using the Heroku hosted version

If you do not have maven installed, you can install it [here.](https://maven.apache.org/download.cgi)

If you're unsure, enter this command in your terminal
```
mvn -version
```

Once maven is installed, build the project. (Make sure you are in the cloned folder)
```
mvn clean install -U
```

### Run the application
This step not needed if using the heroku hosted version.

After maven has finished building and was successful, you'll now have a target folder in your project folder.
```
cd target
```
Run the following command to start the application
```
java -jar FetchRewardsChallenge-0.0.1-SNAPSHOT.jar
```


**API Routes:**

* **Base URL: /api/v1/points/**
* POST /processTransaction : Completes and processes a transaction. This will also save the transaction information to
  the `Transaction List`
* POST /spendPoints : Process spend points
* GET /pointsBalance : Provides a Map of all payers and points

<br/>

## **Using the API with `POSTMAN` or `INSOMNIA`**

Add [Postman](https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=en) extension for Chrome or any other REST Client

Launch Postman, or other REST Client
---

## 1. Add Points to the User

<br/>

### Endpoint: `/api/v1/points/processTransaction`

- This is a `POST` request. <br/>
  We can use any of the following Request Body:
  <br/><br/>

 ```JSON
   {
  "payerName": "DANNON",
  "balance": 1000,
  "timeStamp": "2020-11-02T14:00:00Z"
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

### Endpoint: `/api/v1/points/pointsBalance`

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

### Endpoint: `/api/v1/points/spendPoints`

- This is a `POST` request. <br/>
  We can use the following Request Body:
  <br/><br/>

 ```JSON
{
  "balance": "points_to_spend"
}
 ```

  <br/>

- We deduct the points from each transaction in a chronological order.
- The output will be in the following format.

 ```JSON
   [
  {
    "payerName": "DANNON",
    "payerBalance": "-100",
    "timeStamp": "2020-10-31T10:00:00Z"
  }
]
 ```

  <br/>
  <br/>