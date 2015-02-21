package xsd

case class Element(name: String, _type: Option[String] = None, minOccurs: Occurence = Number(1),
                   maxOccurs: Occurence = Number(1))

trait Occurence
case class Number(i: Int) extends Occurence
case object Unbounded extends Occurence
