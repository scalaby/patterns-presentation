/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalaby.patterns.adt

trait XML
case class Text(text: String) extends XML
case class Entity(tag: Tag, attrs: Attrs, nodes: Iterable[XML]) extends XML

object XML{
        
    val render: XML => String = _ match {
        case Text(text) => text
        case Entity(tag, attrs, nodes) => "<%s%s>%s</%s>".format(tag, 
                                                                 attrs.map(e => "%s=\"%s\"".format(e._1, e._2)).mkString, 
                                                                 nodes.map(render).mkString, 
                                                                 tag)
    }        
     
}
