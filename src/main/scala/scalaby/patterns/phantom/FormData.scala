/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalaby.patterns.phantom

/* From data to be extracted from HTTP request (parameter-to-value map) */
case class FormData[+T <: Data](data: Map[String, String])

/* Phantom types for the data state - never instantiated */
trait Data
trait Validated extends Data
trait Unvalidated extends Data