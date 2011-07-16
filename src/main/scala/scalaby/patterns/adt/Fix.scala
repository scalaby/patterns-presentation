/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalaby.patterns.adt

import scalaz._
import Scalaz._

case class Fix [F [_, _], A] (out : F [A, Fix [F,  A]])
          
object Fix {
    
    def map [A, B, F [_, _]] (f : A => B) (t : Fix [F, A]) (implicit ft : Bifunctor [F]) : Fix [F, B] =
        Fix(ft.bimap(t.out, f , map(f )))
    
    def fold [A, B, F [_, _]] (f : F[A, B] => B) (t : Fix [F, A]) (implicit ft : Bifunctor [F]) : B =
        f(ft.bimap(t.out, (x: A) => x, fold(f)))
    
    def unfold [A, B, F [_, _]] (f : B => F[A, B]) (b : B) (implicit ft : Bifunctor [F]) : Fix[F, A] =
        Fix(ft.bimap(f(b), (x: A) => x, unfold(f)))

    def hylo [A, B, C, F [_, _]] (f : B => F[A, B]) (g : F[A, C] => C)(b: B) (implicit ft : Bifunctor [F]) : C =
        g(ft.bimap(f(b), (x: A) => x, hylo(f)(g)))
        
    def build [A, F [_, _]] (f : {def apply[B]: (F [A, B] => B) => B}): Fix[F, A] = 
        f.apply(Fix[F, A])

}