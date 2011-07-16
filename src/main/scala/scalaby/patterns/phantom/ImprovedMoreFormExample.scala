/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalaby.patterns.phantom

import javax.servlet.http.HttpServletRequest

object ImprovedMoreFormExample {

    import ImprovedMoreForm._
    
    def printForm(request: HttpServletRequest) = 
        printFormData(readFormData(request))
  
    /* def printFormTwice(request: HttpServletRequest) = 
     printFormData(printFormData(readFormData(request))) */
  
}
