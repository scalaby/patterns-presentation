package com.github.scalaby

object CellTest {
	def main(args: Array[String]) {
		implicit val context = new CellContext
		val a = Cell(1)("a")
		val b = Cell(a() + 2)("b")
		val c = Cell(a() + b())("c")
		val x = Cell(a() + b() + c())("x")
		val y = Cell(a() + c())("y")
		println(x())
		println(y())
		c.update(2)
		println(x())
		println(y())
	}
}
