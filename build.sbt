name := "Dung2Experimental"

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

lazy val EntityV1 = project
        .dependsOn(Commons)
        .settings(
            commonSettings,
            libraryDependencies ++= Dependencies.entityV1Dependencies
        )

lazy val EntityV2 = project
        .dependsOn(Commons)
        .settings(
            commonSettings,
            libraryDependencies ++= Dependencies.entityV2Dependencies
        )

lazy val ValueV1 = project
        .dependsOn(Commons)
        .settings(
            commonSettings,
            libraryDependencies ++= Dependencies.valueV1Dependencies
        )