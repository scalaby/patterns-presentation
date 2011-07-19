/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalaby.patterns.adt

sealed trait Boolean
case object True extends Boolean
case object False extends Boolean
