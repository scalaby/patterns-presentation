/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalaby.patterns.phantom

case class FormData[T <: Data](data: Map[String, String])

trait Data
trait Validated extends Data
trait Unvalidated extends Data