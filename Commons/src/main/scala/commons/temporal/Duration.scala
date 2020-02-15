package commons.temporal

case class Duration(time: Long) {
    def unary_+ : Duration = this
    
    def unary_- : Duration = Duration(-time)
    
    def +(that: Duration): Duration = Duration(time + that.time)
    
    def -(that: Duration): Duration = this + -that
    
    def <(that: Duration): Boolean = time < that.time
    
    def <=(that: Duration): Boolean = time <= that.time
    
    def >(that: Duration): Boolean = time > that.time
    
    def >=(that: Duration): Boolean = time >= that.time
}

object Duration {
    
    implicit class ImplicitDuration(time: Int) {
        def milliseconds: Duration = Duration(time)
        
        def seconds: Duration = Duration(time * 1000)
        
        def minute: Duration = Duration(time * 1000 * 60)
        
        def hour: Duration = Duration(time * 1000 * 60 * 60)
    }
    
    def difference(from: Timestamp, to: Timestamp): Duration = Duration(to.time - from.time)
}