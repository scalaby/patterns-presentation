/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalaby.patterns.phantom

import javax.servlet.http._
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import scala.annotation.implicitNotFound
import scala.collection.JavaConversions._

trait Logged extends Validated
trait Printed extends Validated

/* Defines accepted values of a phantom type for some methods */
@implicitNotFound(msg = "This method cannot be used with ${T}!")
case class Accepts[T <: Data]()

object ImprovedMoreForm extends ImprovedForm{
    
    val log: Log = LogFactory.getLog(this.getClass)
    
    implicit val logged = new FormValidator[Logged]{
        def validate(formData: FormData[Logged]) = formData
    }  
    
    /* This method logs form data */
    def logFormData[T <: Data](formData: FormData[T])
    (implicit validator: FormValidator[T]) = {
        log.info("Validated form data: " + validator.validate(formData))
        FormData[Logged](data = formData.data)
    }      
    
    implicit val acceptsUnvalidated = Accepts[Unvalidated]
    implicit val acceptsValidated = Accepts[Validated]
     
    /* This method acceptes either validated or unvalidated data */
    def printFormData[T <: Data](formData: FormData[T])
    (implicit validator: FormValidator[T], order: Accepts[T]) = {        
        ("Validated form data: " + validator.validate(formData))
        FormData[Printed](data = formData.data)
    }  
      
}
