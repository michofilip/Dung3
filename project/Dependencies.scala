import sbt._

object Dependencies {

    private val scalaTest = "org.scalatest" %% "scalatest" % "3.2.0-M2" % Test

    private val commonDependencies: Seq[ModuleID] = Seq(scalaTest)

    val gameDependencies: Seq[ModuleID] = commonDependencies
}
