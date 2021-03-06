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
      name <- text(n \\ "@name")
      if label == "element"
    } yield Element(name = name,
      _type = text(n \\ "@type"),
      minOccurs = occurs(n \\ "@minOccurs").getOrElse(Number(1)),
      maxOccurs = occurs(n \\ "@maxOccurs").getOrElse(Number(1))
    )
  }

}
