package temporal

case class Timestamp(time: Long) {
    def +(duration: Duration): Timestamp = Timestamp(time + duration.time)
    
    def -(duration: Duration): Timestamp = Timestamp(time - duration.time)
}

object Timestamp {
    def zero: Timestamp = Timestamp(0)
    
    def now: Timestamp = Timestamp(System.currentTimeMillis())
}