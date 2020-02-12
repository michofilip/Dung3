package system.messages

import world.WorldFrame

sealed abstract class WorldFrameMessage

object WorldFrameMessage {
    
    final case class SetWorldFrame(worldFrame: WorldFrame) extends WorldFrameMessage
    
}
