package model

sealed abstract class Direction

object Direction {
    
    case object North extends Direction
    
    case object East extends Direction
    
    case object South extends Direction
    
    case object West extends Direction
    
}
