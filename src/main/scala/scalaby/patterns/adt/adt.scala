/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalaby.patterns

package object adt {

    type Attrs = List[(String, String)]
    type Tag = String
    type Doc = Fix[DocF, String]    
    
}
