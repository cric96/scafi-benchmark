ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.8"
ThisBuild / resolvers +=
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
val scafiVersion = "0.3.3+334-8d4e68d5-SNAPSHOT"
lazy val root = (project in file("."))
  .settings(
    name := "scafi-benchmark",
    libraryDependencies ++= Seq(
      "it.unibo.scafi" %% "scafi-core" % scafiVersion,
      "it.unibo.scafi" %% "scafi-simulator" % scafiVersion
    )
  )
