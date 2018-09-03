package com.tookitaki.app
import org.apache.spark.sql._
import org.apache.spark.sql.types._
import scala.util.parsing.json._

object CoinbaseClient {

  /**
    * Returns the json content from a REST URL. Returns a blank String if there
    * is a problem. (Probably should use Option/Some/None)
    */
  def getRestContent(url: String): String = {
    val result = scala.io.Source.fromURL(url).mkString
    return result
  }

  def saveCoinDetails(url: String): Unit = {

    val coinInfo = getRestContent(url)
    //val coninInfoJson = JSON.parseFull(coinInfo)
    val sparkSession = SessionVariables.getSparkSession

    import sparkSession.implicits._
    import sparkSession.sql

    /** Specify the schema as part of read, helps to improve performance by avoiding
      * the extra pass over data to infer schema
      */
    val schema = StructType(
      List(
        StructField("Date", DateType, true),
        StructField("price", DoubleType, true)
      )
    )
    val coinRdd = sparkSession.sparkContext.parallelize(coinInfo)
	import sparkSession.sparkContext.implicits._
    val coinDF =  coinRdd.toDF								       

    // Read data from hdfs path
    val srcDF = sparkSession.read.schema(schema).csv(SessionVariables.hdfsPath)

    //Merge lastest result with existing deatails
    val finalDF = srcDF.unionAll(coinDF.sort(desc("date")).first())

    //Write final df to HDFS as single file
    finalDF
      .repartition(1)
      .write
      .mode(SaveMode.Overwrite)
      .csv(SessionVariables.hdfsPath)

  }

  def dailyRun( ) = {
    saveCoinDetails(SessionVariables.url)
  }
}

 