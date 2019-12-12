package commons.utils

object MathUtils {
    def mod(x: Int, n: Int): Int = if (x < 0) x % n + n else if (x < n) x else x % n
    
    def bound(x: Int, min: Int, max: Int): Int = if (x < min) min else if (x < max) x else max
    
    def ceil(x: Double): Int = Math.ceil(x).toInt
    
    def floor(x: Double): Int = Math.floor(x).toInt
    
}
