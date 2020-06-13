package engine.temporal

case class Timestamp(time: Long) {
  def +(duration: Duration): Timestamp = Timestamp(time + duration.time)

  def -(duration: Duration): Timestamp = Timestamp(time - duration.time)

  def -(timestamp: Timestamp): Duration = Duration.difference(timestamp, this)

  def <(that: Timestamp): Boolean = time < that.time

  def <=(that: Timestamp): Boolean = time <= that.time

  def >(that: Timestamp): Boolean = time > that.time

  def >=(that: Timestamp): Boolean = time >= that.time
}

object Timestamp {
  def zero: Timestamp = Timestamp(0)

  def now: Timestamp = Timestamp(System.currentTimeMillis())
}