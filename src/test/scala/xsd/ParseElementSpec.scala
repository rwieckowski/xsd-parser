package xsd

class ParseElementSpec extends UnitSpec {
  "parseElement" should "return None for non element node" in {
    parseElement(<nonElement/>) mustEqual None
  }

  it should "parse element" in {
    parseElement(<element/>) mustEqual Some(Element())
  }

  it should "parse element with prefix" in {
    parseElement(<ns:element xmlns:ns="http://tempuri.org"/>) mustEqual Some(Element())
  }

  it should "parse element name" in {
    parseElement(<element name="elementName"/>) mustEqual Some(Element(name=Some("elementName")))
  }

  it should "parse element type" in {
    parseElement(<element type="xsi:string"/>) mustEqual Some(Element(ty = Some("xsi:string")))
  }

  it should "parse element min occurs" in {

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
