package com.tookitaki.app
 import org.apache.spark.sql._
 import scala.collection._
 import org.apache.spark.sql.expressions.Window
 import org.apache.spark.sql.functions._

  
object MovingAvg{
  
def getAvgProce: Map[String, String] = {

  

  val session = SessionVariables.getSparkSession
   // Read data from hdfs path
  val srcDF = session.read.option("inferSchema", "true").csv(SessionVariables.hdfsPath)
  import session.implicits._   
  // Create a window spec.
  val windowSpec = Window.partitionBy("date").rowsBetween(-1, 1)
  val lstAvg = srcDF
    .withColumn("movingAvg", avg(srcDF("price")).over(windowSpec))
    .select("date", "movingAvg")
    .as[(String, String)]
    .collect
    .toMap
  lstAvg
}
}