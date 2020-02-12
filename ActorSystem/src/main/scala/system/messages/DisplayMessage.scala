package system.messages

import world.WorldFrame

sealed abstract class DisplayMessage

object DisplayMessage {
    
    final case class DisplayWorldFrame(worldFrame: WorldFrame) extends DisplayMessage
    
}
