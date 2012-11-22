name := "deforscher"

version := "1.0"

scalaVersion := "2.9.2"

libraryDependencies ++= Seq(
 "junit" % "junit" % "4.8.1" % "test",
 "org.scalatest" %% "scalatest" % "1.8" % "test",
 "net.liftweb" %% "lift-json" % "2.5-M2",
 "com.thoughtworks.paranamer" % "paranamer" % "2.1"
)
