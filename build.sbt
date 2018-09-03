val ScalatraVersion = "2.6.3"

organization := "com.example"

name := "BitcoinAnalysis"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.8"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "9.4.9.v20180320" % "container",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",,
  "com.cloudera.sparkts" % "sparkts" % "0.4.0",
  "org.apache.spark" %% "spark-core" % "2.2.2",
  "org.apache.spark" %% "spark-sql" % "2.2.2",
  "org.apache.spark" %% "spark-mllib" % "2.2.2",
  "org.apache.spark" %% "spark-streaming" % "2.2.2"
)

enablePlugins(SbtTwirl)
enablePlugins(ScalatraPlugin)
