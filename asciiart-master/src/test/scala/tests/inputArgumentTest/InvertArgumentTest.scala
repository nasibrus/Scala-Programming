package tests.inputArgumentTest

import exception.UnknownArgumentException
import filter.Invert
import input.ArgumentReader
import org.scalatest.FunSuite

class InvertArgumentTest extends FunSuite{

  private var arguments:Array[String] = Array()

  test("Correct invert arg format") {
    arguments = Array("--image", "./picture.png", "--invert")
    val reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    val filters = reader.readFilterArguments
    assert(filters(0).isInstanceOf[Invert])
  }

  test("Incorrect invert arg format") {
    arguments = Array("--image", "./picture.png", "--invert", "90")
    val reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    assertThrows[UnknownArgumentException] {
      reader.readFilterArguments
    }
  }

}
