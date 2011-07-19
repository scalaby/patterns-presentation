/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalaby.patterns.phantom

import javax.servlet.http._
import scala.collection.JavaConversions._

/* Form validator type class that relies on a data phantom type */
trait FormValidator[T <: Data]{  
    def validate(formData: FormData[T]): FormData[Validated]  
}

trait ImprovedForm extends Form{

    /* Instance of the validator that works with a validated data - does nothing, in fact */
    implicit val validated = new FormValidator[Validated]{
        def validate(formData: FormData[Validated]) = formData
    }
  
    /* Instance of the validator that validates unvalidated data */
    implicit val unvalidated = new FormValidator[Unvalidated]{
        def validate(formData: FormData[Unvalidated]) = FormData[Validated](    
            formData.data.map(e => (e._1 -> Option(e._2).getOrElse("")))
        )
    }  
  
}

object ImprovedForm extends ImprovedForm{
    
    /* This method accepts both validated and unvalidated data, and uses an appropriate 
     * instance of the validator */
    def printFormData[T <: Data](formData: FormData[T])
    (implicit validator: FormValidator[T]) = {
        println("Validated form data: " + validator.validate(formData))
    }

}
