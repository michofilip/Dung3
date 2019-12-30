package model

sealed abstract class Direction {
    val azimuth: Int
    
    def right: Direction
    
    def left: Direction
    
    def opposite: Direction
}

object Direction {
    
    final case object North extends Direction {
        override val azimuth: Int = 0
        
        override def right: Direction = NorthEast
        
        override def left: Direction = NorthWest
        
        override def opposite: Direction = South
    }
    
    final case object NorthEast extends Direction {
        override val azimuth: Int = 45
        
        override def right: Direction = East
        
        override def left: Direction = North
        
        override def opposite: Direction = SouthWest
    }
    
    final case object East extends Direction {
        override val azimuth: Int = 90
        
        override def right: Direction = SouthEast
        
        override def left: Direction = NorthEast
        
        override def opposite: Direction = West
    }
    
    final case object SouthEast extends Direction {
        override val azimuth: Int = 135
        
        override def right: Direction = South
        
        override def left: Direction = East
        
        override def opposite: Direction = NorthWest
    }
    
    final case object South extends Direction {
        override val azimuth: Int = 180
        
        override def right: Direction = SouthWest
        
        override def left: Direction = SouthEast
        
        override def opposite: Direction = North
    }
    
    final case object SouthWest extends Direction {
        override val azimuth: Int = 225
        
        override def right: Direction = West
        
        override def left: Direction = South
        
        override def opposite: Direction = NorthEast
    }
    
    final case object West extends Direction {
        override val azimuth: Int = 270
        
        override def right: Direction = NorthWest
        
        override def left: Direction = SouthWest
        
        override def opposite: Direction = East
    }
    
    final case object NorthWest extends Direction {
        override val azimuth: Int = 315
        
        override def right: Direction = North
        
        override def left: Direction = West
        
        override def opposite: Direction = SouthEast
    }
    
}
