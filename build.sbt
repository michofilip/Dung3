name := "Dung3"

lazy val commonSettings = Seq(
    //    organization := "com.example",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "2.13.2"
)

lazy val Game = project
    .settings(
        commonSettings,
        libraryDependencies ++= Dependencies.gameDependencies
    )