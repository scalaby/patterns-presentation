package com.github.scalaby

import scala.collection.{mutable => M}

object Cell {
	private def wrapFunc[T](f: =>T) = new Function0[T] {
		def apply: T = f			
	}
	def apply[T](value: =>T)(implicit ctx: CellContext) = new Cell(value)(ctx)
}

class Cell[T](initialValue: =>T)(ctx: CellContext) {
	private var value:Function0[T] = Cell.wrapFunc(initialValue)
	private var currentValue:Option[T] = None
	private val inputs: M.Set[Cell[_]] = M.Set()
	private val outputs: M.Set[Cell[_]] = M.Set()
	def apply() = {
		updateDeps()
		ctx.synchronized {
			if(currentValue.isEmpty)
				compute()
		}
		currentValue.get
	}
	def update(t: =>T) = {
		value = Cell.wrapFunc(t)
		ctx.synchronized { 
			inputs.foreach(_.outputs -= this)
			inputs.clear()
			clear()
		}
	}
	private def clear() {
		currentValue = None
		outputs.foreach(_.clear())
	}
	private def updateDeps() = ctx.synchronized { 
		if(!ctx.stack.isEmpty) {
			outputs += ctx.stack(0)
			ctx.stack(0).inputs += this
		}
	}
	private def compute() { 
		ctx.stack.push(this)
		currentValue = Some(value())
		ctx.stack.pop()
	}
}

class CellContext {
	private[scalaby] val stack: M.Stack[Cell[_]] = M.Stack()
}
