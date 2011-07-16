/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalaby.patterns.phantom

import javax.servlet.http._
import scala.collection.JavaConversions._

trait Form {
  
    def readFormData (request: HttpServletRequest): FormData[Unvalidated] = 
        FormData[Unvalidated](    
            request.getParameterMap
            .map(e => (e._1.toString -> Option(e._2).map(_.toString).orNull))
            .toMap
        )
  
}

object BasicForm extends Form{

    def validate(formData: FormData[Unvalidated]): FormData[Validated] = 
        FormData[Validated](    
            formData.data.map(e => (e._1 -> Option(e._2).getOrElse("")))
        )
  
    def printFormData(formData: FormData[Validated]) = {
        println("Validated form data: " + formData)
    }

}
