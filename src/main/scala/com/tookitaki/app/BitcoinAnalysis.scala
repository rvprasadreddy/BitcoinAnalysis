package com.tookitaki.app

import org.scalatra._

class BitcoinAnalysis extends ScalatraServlet {

   
  /**
  * The URL
  * http://localhost:8080/rundialyjob
  * fetch: pull rest api response and append delta to respository
  */
  post("/rundialyjob") {  
    
    CoinbaseClient.dailyRun()
  }
  
  /**
  * The URL
  * http://localhost:8080/fetchdata?sdate=12/23/2018&edata=12/25/2018
  * usage: fetches data slice for given dates
  */
     
  get("/fetchdata") {      
    val startDate = params.get("sdate")
    val endDate = params.get("edata")
	  val results = FetechData.getQueryResult(startDate.toString(),endDate.toString())
  }
  
  
  /**
  * The URL
  * http://localhost:8080/mvgavg
  * usage: compute moving average (moving average window size assummed as 3 days)
  */
  get("/mvgavg") {  
    
    val mvgAvg = MovingAvg.getAvgProce 
  }
  
  /**
  * The URL
  * http://localhost:8080/forecast
  * usage: forecast price  for next 15 days
  */
  get("/forecast") {      
    val forecastPrices = Forecast.getCoinPrices
  }
  
  
}
