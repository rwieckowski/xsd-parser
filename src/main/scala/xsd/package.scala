import scala.language.postfixOps
import scala.util.Try
import scala.xml.{NodeSeq, Node}

package object xsd {
  type Parser[T] = Node => Option[T]

  def text(ns: NodeSeq) = ns.headOption map (_.text)

  def occurs(ns: NodeSeq) = text(ns) flatMap {
    case "unbounded" => Some(Unbounded)
    case t => Try(Number(t.toInt)) toOption
  }

  def parseElement(n: Node): Option[Element] = {
    for {
      label <- Some(n.label)
      if label == "element"
    } yield {
      val name = text(n \\ "@name")
      val ty = text(n \\ "@type")
      Element(name, ty)
    }
  }

}
