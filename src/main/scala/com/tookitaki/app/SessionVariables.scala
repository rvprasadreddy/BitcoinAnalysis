package com.tookitaki.app
import org.apache.spark.sql.SparkSession

// SessionVariables object holds common methods and variables

object SessionVariables {

  // hdfs path for coin details
  val hdfsPath = "hdfs://input/coindetails.txt"

  //rest api url
  val url =
    "https://www.coinbase.com/api/v2/prices/BTC-USD/historic?period=year"
  
  case class Record(date: String, price:Double)

  var sparkSession : SparkSession = null

  // build Spark Session
  def getSparkSession: SparkSession = {
    if (sparkSession == null) {
      sparkSession = SparkSession
												        .builder()
												        .appName("BitCoinAnalysis")
												        .enableHiveSupport()
												        .getOrCreate()
    }
    return sparkSession
  }

  def closeSparkSession = {
  	if(sparkSession!=null)
         sparkSession.stop()
  }
}

