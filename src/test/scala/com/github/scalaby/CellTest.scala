package com.github.scalaby

object CellTest {
	def main(args: Array[String]) {
		implicit val context = new CellContext
		val a = Cell(1)
		val b = Cell(a() + 2)
		val c = Cell(a() + b())
		val x = Cell(a() + b() + c())
		val y = Cell(a() + c())
		println(x())
		println(y())
		c() = 2
		println(x())
		println(y())
	}
}
