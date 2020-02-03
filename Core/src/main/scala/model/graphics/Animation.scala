package model.graphics

import commons.temporal.Duration
import commons.utils.MathUtils.{MathUtilsImplicits, floor}

sealed abstract class Animation {
    val length: Int
    
    def frame(duration: Duration): Frame
}

object Animation {
    
    private def frameNo(duration: Duration, fps: Double): Int =
        floor(duration.time * fps / 1000)
    
    final case class SingleFrameAnimation(frame: Frame) extends Animation {
        override val length: Int = 1
        
        override def frame(duration: Duration): Frame = frame
    }
    
    final case class OneWayAnimation(fps: Double, frames: Vector[Frame]) extends Animation {
        override val length: Int = frames.length
        
        override def frame(duration: Duration): Frame = {
            val frameIndex = 0 <| frameNo(duration, fps) |> (length - 1)
            frames(frameIndex)
        }
    }
    
    final case class LoopingAnimation(fps: Double, frames: Vector[Frame]) extends Animation {
        override val length: Int = frames.length
        
        override def frame(duration: Duration): Frame = {
            val frameIndex = frameNo(duration, fps) %% length
            frames(frameIndex)
        }
    }
    
}
