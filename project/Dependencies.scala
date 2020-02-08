import sbt._

object Dependencies {
    private val scalaTestVersion = "3.2.0-M2"
    private val logbackClassicVersion = "1.2.3"
    private val akkaActorTypedClassicVersion = "2.6.3"
    
    private val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVersion % Test
    private val logbackClassic = "ch.qos.logback" % "logback-classic" % logbackClassicVersion
    private val akkaActorTypedClassic = "com.typesafe.akka" %% "akka-actor-typed" % akkaActorTypedClassicVersion
    
    private val commonDependencies: Seq[ModuleID] = Seq(scalaTest)
    
    val coreDependencies: Seq[ModuleID] = commonDependencies
    val valueDependencies: Seq[ModuleID] = commonDependencies
    val commonsDependencies: Seq[ModuleID] = commonDependencies
    val actorSystemDependencies: Seq[ModuleID] = commonDependencies ++ Seq(logbackClassic, akkaActorTypedClassic)
}
