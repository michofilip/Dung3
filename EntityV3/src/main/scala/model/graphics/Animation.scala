package model.graphics

import commons.temporal.Duration
import commons.utils.MathUtils.{bound, floor, mod}

sealed abstract class Animation {
    def frame(duration: Duration): Frame
}

object Animation {
    
    final case class SingleFrameAnimation(frame: Frame) extends Animation {
        override def frame(duration: Duration): Frame = frame
    }
    
    final case class OneWayAnimation(fps: Double, frames: Vector[Frame]) extends Animation {
        private val length = frames.length
        
        override def frame(duration: Duration): Frame = {
            val frameNo = floor(duration.time * fps / 1000)
            val frameIndex = bound(frameNo, 0, length - 1)
            frames(frameIndex)
        }
    }
    
    final case class LoopingAnimation(fps: Double, frames: Vector[Frame]) extends Animation {
        private val length = frames.length
        
        override def frame(duration: Duration): Frame = {
            val frameNo = floor(duration.time * fps / 1000)
            val frameIndex = mod(frameNo, length)
            frames(frameIndex)
        }
    }
    
}
