/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalaby.patterns.phantom

import javax.servlet.http._
import scala.collection.JavaConversions._

trait Printed extends Validated

trait StateOrder[From <: Data, To <: Data]

object ImprovedMoreForm extends ImprovedForm{
    
    implicit val unToP = new StateOrder[Unvalidated, Printed]{}
    implicit val vToP = new StateOrder[Validated, Printed]{}
  
    implicit val printed = new FormValidator[Printed]{
        def validate(formData: FormData[Printed]) = formData
    }  
  
    def printFormData[T <: Data](formData: FormData[T])
    (implicit validator: FormValidator[T], order: StateOrder[T, Printed]) = {
        println("Validated form data: " + validator.validate(formData))
        FormData[Printed](data = formData.data)
    }  
  
}
