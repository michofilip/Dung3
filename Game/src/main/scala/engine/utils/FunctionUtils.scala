package engine.utils

object FunctionUtils {
  def compose[A, B, C](f1: A => B, f2: B => C): A => C = x => f2(f1(x))

  implicit class Composer[A, B](f1: A => B) {
    def -->[C](f2: B => C): A => C = compose(f1, f2)
  }

}
