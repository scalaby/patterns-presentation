/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalaby.patterns.phantom

import javax.servlet.http._
import scala.collection.JavaConversions._

trait FormValidator[T <: Data]{  
  def validate(formData: FormData[T]): FormData[Validated]  
}

trait ImprovedForm extends Form{

  implicit val validated = new FormValidator[Validated]{
    def validate(formData: FormData[Validated]) = formData
  }
  
  implicit val unvalidated = new FormValidator[Unvalidated]{
    def validate(formData: FormData[Unvalidated]) = FormData[Validated](    
      formData.data.map(e => (e._1 -> Option(e._2).getOrElse("")))
    )
  }  
  
}

object ImprovedForm extends ImprovedForm{
    
  def printFormData[T <: Data](formData: FormData[T])
  (implicit validator: FormValidator[T]) = {
    println("Validated form data: " + validator.validate(formData))
  }

}
