package com.tookitaki.app
import com.cloudera.sparkts.models.ARIMA
import org.apache.spark.mllib.linalg.Vectors
import scala.collection._

object Forecast {
  def getCoinPrices: List[Double] = {

  val lines = scala.io.Source.fromFile(SessionVariables.hdfsPath).getLines()
  val ts = Vectors.dense(lines.split(',')(2).toDouble).toArray
  val arimaModel = ARIMA.fitModel(1, 0, 1, ts)
  val forecast = arimaModel.forecast(ts, 15)
  forecast.toList

}
}