import sbt._

object Dependencies {
    
    private val scalaTest = "org.scalatest" %% "scalatest" % "3.2.0-M2"
    
    private val commonDependencies: Seq[ModuleID] = Seq(scalaTest % Test)
    
    val entityV1Dependencies: Seq[ModuleID] = commonDependencies
    val entityV2Dependencies: Seq[ModuleID] = commonDependencies
    val valueV1Dependencies: Seq[ModuleID] = commonDependencies
    val commonsDependencies: Seq[ModuleID] = commonDependencies
}
