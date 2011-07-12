package com.github.scalaby

import java.util.concurrent.{TimeUnit => JTimeUnit}
	
object TimeUnit {
	implicit def longToRichLong(duration: Long) = new RichLong(duration)
}

case class TimeUnit(duration: Long, unit: JTimeUnit)

class RichLong(duration: Long) {
	import JTimeUnit._
	def apply(u: JTimeUnit) = TimeUnit(duration, u)
	def days = apply(DAYS)
	def day = days
	def hours = apply(HOURS)
	def hour = hours
	def microseconds = apply(MICROSECONDS)
	def microsecond = microseconds
	def milliseconds = apply(MILLISECONDS)
	def millisecond = milliseconds
	def minutes = apply(MINUTES)
	def minute = minutes
	def nanoseconds = apply(NANOSECONDS)
	def nanosecond = nanoseconds
	def seconds = apply(SECONDS)
	def second = apply(SECONDS)
}
