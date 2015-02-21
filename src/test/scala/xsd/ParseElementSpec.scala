package xsd

class ParseElementSpec extends UnitSpec {
  "parseElement" should "return None for non element node" in {
    parseElement(<nonElement/>) mustEqual None
  }

  it should "parse element" in {
    parseElement(<element name="elementName"/>) mustEqual Some(Element(name = "elementName"))
  }

  it should "parse element with prefix" in {
    parseElement(<ns:element name="elementName" xmlns:ns="http://tempuri.org"/>) mustEqual Some(Element(name = "elementName"))
  }

  it should "parse element type" in {
    parseElement(<element name="elementName" type="xsi:string"/>) mustEqual Some(Element(name = "elementName", _type = Some("xsi:string")))
  }

  it should "parse element min occurs" in {
    parseElement(<element name="elementName" minOccurs="5"/>) mustEqual Some(Element(name = "elementName", minOccurs = Number(5)))
  }

  it should "parse element max occurs" in {
    parseElement(<element name="elementName" maxOccurs="5"/>) mustEqual Some(Element(name = "elementName", maxOccurs = Number(5)))
  }

  "occurs" should "parse numeric occurence" in {
    occurs(<occurs>1</occurs>) mustEqual Some(Number(1))
  }

  it should "parse unbounded occurence" in {
    occurs(<occurs>unbounded</occurs>) mustEqual Some(Unbounded)
  }

  it should "return None for parsable strings" in {
    occurs(<occurs>text</occurs>) mustEqual (None)
  }
}
