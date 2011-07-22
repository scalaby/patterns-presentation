package com.github.scalaby

import java.util.regex.{MatchResult, Pattern}
import scala.util.matching.Regex
import scala.collection.{mutable => M}
	
trait Reader[T] {
	def read(input: String): T
}

trait PathHandler {
	implicit object StringReader extends Reader[String] {
		def read(input: String) = input
	}	
	implicit object IntReader extends Reader[Int] {
		def read(input: String) = input.toInt
	}
	implicit object BooleanReader extends Reader[Boolean] {
		def read(input: String) = input.toBoolean
	}
	private val hs: M.Map[String, (Pattern, Function1[MatchResult, Unit])] = new M.LinkedHashMap()
	private def registerHandler(pattern: String)(t: (MatchResult) => Unit) {
		hs += pattern -> (pattern.r.pattern, new Function1[MatchResult,Unit] {
			def apply(result: MatchResult) { t(result) }
		})
	}
	def handle(path: String) {
		hs.foreach{
			case (_, (p, f)) => { 
				val m = p.matcher(path)
				if(m.matches()) { 
					f(m)
					return
				}
			}
		}
	}
	case class h(pattern: String) { 
		def apply(t: => Unit) {
			registerHandler(pattern){ result =>
				t
			}
		}
		def apply[A](t: (A) => Unit)(implicit a: Reader[A]) {
			registerHandler(pattern){ r =>
				t(
					a.read(r.group(1))
				)
 	    }
		}
		def apply[A,B](t: (A, B) => Unit)(implicit a: Reader[A], b: Reader[B]) {
			registerHandler(pattern){ r =>
				t(
					a.read(r.group(1)),
					b.read(r.group(2))				
				)
 	    }
		}
		def apply[A,B,C](t: (A, B, C) => Unit)(implicit a: Reader[A], b: Reader[B], c: Reader[C]) {
			registerHandler(pattern){ r =>
				t(
					a.read(r.group(1)),
					b.read(r.group(2)),
					c.read(r.group(3))
				)
 	    }
		}
	}
}
