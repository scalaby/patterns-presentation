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
    
    /* 
     // You can log form data twice
     def logForm(request: HttpServletRequest) = 
     logFormData(logFormData(readFormData(request)))    
     */

    /* 
     // You cannot print data that already has been logged
     def printLoggedData(request: HttpServletRequest) = 
     printFormData(logFormData(readFormData(request)))         
     */
    
    /*  
     // You cannot print data twice
     def printFormTwice(request: HttpServletRequest) = 
     printFormData(printFormData(readFormData(request)))         
     */
  
}
