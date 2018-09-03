package com.tookitaki.app
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._  
import scala.collection._

object FetechData {
  
	//extract data for custom dates
def getQueryResult(start_dt: String, end_dt: String): List[SessionVariables.Record] = {

   //get session
   val session = SessionVariables.getSparkSession
   
  // Read data from hdfs path
  
  val srcDF = session.read.option("inferSchema", "true").csv(SessionVariables.hdfsPath)

  //Filter result
  import session.implicits._  
  
  val filterDF = srcDF.where(
    unix_timestamp("date", "yyyy-MM-dd HH:mm:ss")
      .cast("timestamp")
      .between(
        Timestamp.valueOf(unix_timestamp(start_dt, "yyyy-MM-dd HH:mm:ss")),
        Timestamp.valueOf(unix_timestamp(end_dt, "yyyy-MM-dd HH:mm:ss"))
      ))
  
  //return result
  
  val filRecs = filterDF.as[SessionVariables.Record].collect.toList

  filRecs
}
}