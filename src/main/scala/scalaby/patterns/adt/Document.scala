/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalaby.patterns.adt
import scalaz._
import Scalaz._
import XML._
import Fix._

/*
 * ========= COMPOSITE PATTERN =========
 */
trait DocF[+A, +B]    
case class Para[A, R](s: A) extends DocF[A, R]
case class Sec[A, R](s: A, xs: Iterable[R]) extends DocF[A, R]

object Document {
                          
    implicit val docBifunctor = new Bifunctor[DocF]{        
        def bimap[A, B, C, D](k: DocF[A,B], f: (A) => C, g: (B) => D): DocF[C,D] = k match {
            case Para(s) => Para(f(s))
            case Sec(s, xs) => Sec(f(s), xs map g)
        }        
    } 

    /*
     *  ========= ITERATOR PATTERN =========
     */       
    val correct: String => String = x => "> " + x        
    val corrector: Doc => Doc = map(correct)
    
    /*
     *  ========= VISITOR PATTERN =========
     */            
    val combine: DocF[String, Iterable[String]] => Iterable[String] = _ match {
        case Para(s) => List(s)
        case Sec(s, xs) => s :: xs.flatten.toList
    }    
    val printDoc: Doc => Iterable[String] = fold(combine)
    
    /*
     *  ========= BUILDER PATTERN =========
     */                       
    def title(tag: String, attrs: Attrs): String = 
        if(attrs.isEmpty) tag 
    else "%s (%s)".format(tag, attrs.map(e => "%s = '%s'" format(e._1, e._2)).mkString(", "))     
    
    val step: XML => DocF[String, XML] =_ match {
        case Text(text) => Para(text)
        case Entity(tag, attrs, nodes) => Sec(title(tag, attrs), nodes)    
    }
    
    val fromXML: XML => Doc = unfold(step)    
    val printXML: XML => Iterable[String] = hylo(step)(combine)
    
    /*
     *  ========= BUILD DOCUMENT =========
     */                
    
    type DocBuilder[B] = (DocF[String, B] => B) => B
    
    trait Document{        
        def apply[B]: DocBuilder[B]
        val instance = build(this)        
    }

    /*
     *  ========= EXAMPLE =========
     */                
    
    def main(args: Array[String]) = {
                    
        val xml = Entity("html", Nil, List(
                Entity("head", Nil, List(
                        Entity("title", Nil, List(Text("Title"))) 
                    )),
                Entity("body", Nil, List(
                        Entity("h1", List("align" -> "left"), List(Text("Body")))
                    ))
            ))
        
        (render andThen println)(xml)
        
        val docFromXml = fromXML(xml)
        printDoc(docFromXml) foreach println 
        
        val doc = (new Document{        
                def apply[B]: DocBuilder[B] = 
                    f => f(Sec("Heading", List(
                                f(Para("p1")),
                                f(Para("p2"))
                            )))        
            }).instance        
        
        (corrector andThen printDoc)(doc) foreach println 
        
    }
        
    
}

