package xsd

case class Element(name: Option[String] = None, ty: Option[String] = None)

trait Occurence

case class Number(i: Int) extends Occurence

case object Unbounded extends Occurence
