package engine.utils

object MathUtils {
    
    def mod(x: Int, n: Int): Int = {
        val y = x % n
        if (y < 0) y + n else y
    }
    
    def bound(x: Int, min: Int, max: Int): Int = min <| x |> max
    
    def ceil(x: Double): Int = Math.ceil(x).toInt
    
    def floor(x: Double): Int = Math.floor(x).toInt
    
    def round(x: Double, prec: Int): Double = {
        val tens = Math.pow(10, prec)
        Math.round(tens * x) / tens
    }
    
    implicit class MathUtilsImplicits(x: Int) {
        def %%(n: Int): Int = mod(x, n)
        
        def <|(n: Int): Int = Math.max(x, n)
        
        def |>(n: Int): Int = Math.min(x, n)
    }
    
}
