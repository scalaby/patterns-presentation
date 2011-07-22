package com.github.scalaby

object PathHandlerTest extends PathHandler {
	h("^test$") {
		println("test matched")
	}
	h("^test/(\\d+)$") { (a: Int) =>
		println("test with a digit matched: " + a)
	}
	h("^test/(\\d+)/(true|false)$") { (a: Int, b: Boolean) =>
		println("test with a digit and boolean matched: " + a + "," + b)
	}
	def main(args: Array[String]) {
		handle("test")
		handle("test/123")
		handle("test/123/true")
	}
}
