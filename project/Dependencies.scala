import sbt._

object Dependencies {
    
    private val scalaTest = "org.scalatest" %% "scalatest" % "3.2.0-M2"
    
    private val commonDependencies: Seq[ModuleID] = Seq(scalaTest % Test)
    
    val gameDependencies: Seq[ModuleID] = commonDependencies
    val coreDependencies: Seq[ModuleID] = commonDependencies
    val valueDependencies: Seq[ModuleID] = commonDependencies
    val commonsDependencies: Seq[ModuleID] = commonDependencies
}
