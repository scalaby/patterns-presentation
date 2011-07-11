package scalaby.patterns.phantom

import javax.servlet.http._
import scala.collection.JavaConversions._

object BasicFormExample {
  
  import BasicForm._
  
  def printForm(request: HttpServletRequest) = 
    (readFormData _ andThen validate _ andThen printFormData _)(request)

  /*
   def printFormInvalid(request: HttpServletRequest) = 
   (readFormData _ andThen printFormData _)(request)
   */
  
}