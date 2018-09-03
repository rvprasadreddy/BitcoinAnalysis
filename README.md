# Bit Coin Analysis
  Features
   - Fetch RestApi Response & Maintain Respository 
   - Fetch Coin Details For Custom Dates
  -  Compute the Moving Average
  -  Forecast 15 days Bit Coin Prices

# Process

  - Call RestApi on dialy basis (this is a scheduled job) and compute the delta with respect to respository. Merge the delta info with original source
  - Fetch Data : Build dataframe on respository and fetch coin details for given dates(i.e., week, month, year, 2 days)
  - Moving Average : Compute moving average with window size 3 (i.e., unit is day)
  - Forecast Prices : Analyse the time series data with ARIMA Model of Spark-TS 
  