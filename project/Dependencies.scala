import sbt._

object Dependencies {
    
    private val scalaTestVersion = "3.3.0-SNAP2"
    
    private val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVersion % Test
    
    private val commonDependencies: Seq[ModuleID] = Seq(
        scalaTest
    )
    
    val coreDependencies: Seq[ModuleID] = commonDependencies
    val valueDependencies: Seq[ModuleID] = commonDependencies
    val commonsDependencies: Seq[ModuleID] = commonDependencies
}
