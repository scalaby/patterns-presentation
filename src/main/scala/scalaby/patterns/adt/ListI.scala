/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalaby.patterns.adt

import scalaz._
import Scalaz._

/* List of integers algebraic data type */
sealed trait ListI {
    def ::(value: Int) = ConsI(value, this)
}
case object NilI extends ListI
case class ConsI(head: Int, tail: ListI) extends ListI

object ListIExample{
    
    val list = 3 :: 2 :: 1 :: NilI
    
}

/* Generalized list algebraic data type  */

sealed trait ListG[+A] {
    def ::[B >: A](value: B) = ConsG[B](value, this)            
}
case object NilG extends ListG[Nothing]
case class ConsG[A](head: A, tail: ListG[A]) extends ListG[A]

object ListGExample{
        
    def foldL[A, B](n: B)(f: (B, A) => B)(list: ListG[A]): B = list match {
        case NilG => n
        case ConsG(head, tail) => f(foldL(n)(f)(tail), head)
    }
    
    def main(args: Array[String]) = {
        val list = 3 :: 2 :: 1 :: NilG
        println("Sum of the elements: %s" format(foldL[Int, Int](0)(_ + _)(list)))
    }
    
}

/* Generalized binary tree algebraic data type  */

sealed trait BTreeG[+A]

case class Tip[A](value: A) extends BTreeG[A]
case class Bin[A](left: BTreeG[A], right: BTreeG[A]) extends BTreeG[A]

object BTreeGExample{
        
    def foldT[A, B](f: A => B)(g: (B, B) => B)(tree: BTreeG[A]): B = tree match {
        case Tip(value) => f(value)
        case Bin(left, right) => g(foldT(f)(g)(left), foldT(f)(g)(right))
    }
    
    def main(args: Array[String]) = {
        val tree = Bin(Tip(1), Bin(Tip(2), Tip(3)))
        println("Sum of the tips: %s" format(foldT[Int, Int](a => a)(_ + _)(tree)))
    }
    
}

/* Generalized list data type implemented through Fix data type  */

trait ListF[+A, +B]
case object NilF extends ListF[Nothing, Nothing]
case class ConsF[A, B](head: A, tail: B) extends ListF[A, B]

object ListF{

    type List[A] = Fix[ListF, A]   
    
    implicit val listFBifunctor = new Bifunctor[ListF]{        
        def bimap[A, B, C, D](k: ListF[A,B], f: A => C, g: B => D): ListF[C,D]  = k match {
            case NilF => NilF
            case ConsF(head, tail) => ConsF(f(head), g(tail))  
        }        
    }

}
