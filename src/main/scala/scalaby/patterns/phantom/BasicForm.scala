/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalaby.patterns.phantom

import javax.servlet.http._
import scala.collection.JavaConversions._

trait Form {
    
    /* Simply extracts unvalidated data from HTTP */
    def readFormData (request: HttpServletRequest): FormData[Unvalidated] = 
        (convertParamaterMap andThen FormData[Unvalidated] _)(request.getParameterMap)

    /* Converts Java map to Scala map */
    private val convertParamaterMap: java.util.Map[_, _] => Map[String, String] = 
        _.map(e => (e._1.toString -> Option(e._2).map(_.toString).orNull)).toMap
    
}

object BasicForm extends Form{

    /* Makes basic validation of the form data - replaces null parameter values with empty strings */
    def validate(formData: FormData[Unvalidated]): FormData[Validated] = 
        FormData[Validated](    
            formData.data.map(e => (e._1 -> Option(e._2).getOrElse("")))
        )
  
    /* Prints form data to the console */
    def printFormData(formData: FormData[Validated]) = 
        println("Validated form data: " + formData)

}
