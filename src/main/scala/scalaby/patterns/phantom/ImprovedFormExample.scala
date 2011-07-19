/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalaby.patterns.phantom

import javax.servlet.http.HttpServletRequest

object ImprovedFormExample {

    import ImprovedForm._
      
    def printForm(request: HttpServletRequest) = 
        printFormData(readFormData(request))
  
}
