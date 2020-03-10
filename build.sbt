name := "Dung3"

lazy val commonSettings = Seq(
    //    organization := "com.example",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "2.13.1"
)

lazy val Commons = project
        .settings(
            commonSettings,
            libraryDependencies ++= Dependencies.commonsDependencies
        )

lazy val Core = project
        .dependsOn(Commons, Value)
        .settings(
            commonSettings,
            libraryDependencies ++= Dependencies.coreDependencies
        )

lazy val Value = project
        .dependsOn(Commons)
        .settings(
            commonSettings,
            libraryDependencies ++= Dependencies.valueDependencies
        )