package model

sealed abstract class Category

object Category {
    
    final case object Switch extends Category
    final case object Door extends Category
    final case object Character extends Category
    
}
