package model.script

case class ScriptContainer(scripts: Map[String, Script]) {
    def getScript(scriptName: String): Option[Script] =
        scripts.get(scriptName)
}
