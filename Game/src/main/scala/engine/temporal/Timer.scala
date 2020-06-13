package engine.temporal

case class Timer(initialTimestamp: Timestamp, isRunning: Boolean) {
  private val creationTimestamp: Timestamp = Timestamp.now

  def start: Timer = Timer(initialTimestamp, isRunning = true)

  def stop: Timer = Timer(getTimestamp, isRunning = false)

  def getTimestamp: Timestamp =
    if (isRunning)
      initialTimestamp + (Timestamp.now - creationTimestamp)
    else
      initialTimestamp
}