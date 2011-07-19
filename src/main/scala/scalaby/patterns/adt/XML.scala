/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalaby.patterns.adt

trait XML
case class Text(text: String) extends XML
case class Entity(tag: Tag, nodes: Iterable[XML], attrs: Attrs = Nil) extends XML

object XML{
        
    val render: XML => String = _ match {
        case Text(text) => text
        case Entity(tag, nodes, attrs) => "<%s%s>%s</%s>".format(tag, 
                                                                 attrs.map(e => "%s=\"%s\"".format(e._1, e._2)).mkString, 
                                                                 nodes.map(render).mkString, 
                                                                 tag)
    }        
    
    def main(args: Array[String]) = {
                
        val xml = 
            Entity("html", List(
                    Entity("head",  List(
                            Entity("title",  List(Text("Title"))) 
                        )),
                    Entity("body",  List(
                            Entity("h1",  List(Text("Body")), List("align" -> "left"))
                        ))
                ))
        
        (render andThen println)(xml)
        
    }
    
}
