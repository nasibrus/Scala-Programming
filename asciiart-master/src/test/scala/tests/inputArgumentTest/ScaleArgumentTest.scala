package tests.inputArgumentTest

import exception.{ArgumentCountException, BadArgumentFormatException}
import filter.Scale
import input.ArgumentReader
import org.scalatest.FunSuite

class ScaleArgumentTest extends FunSuite{

  private var arguments: Array[String] = Array()
  private var reader = new ArgumentReader(arguments)

  test ("Missing scale parameter") {
    arguments = Array("--image", "./picture.png", "--scale")
    reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    assertThrows[ArgumentCountException] {
      reader.readFilterArguments
    }
  }

  test ("Correct scale arg 0.25") {
    arguments = Array("--image", "./picture.png", "--scale", "0.25")
    reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    val filter = reader.readFilterArguments
    assert(filter(0).isInstanceOf[Scale])
    assert(filter(0).asInstanceOf[Scale].getScale == 0.25)
  }

  test ("Correct scale arg 4") {
    arguments = Array("--image", "./picture.png", "--scale", "4")
    reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    val filter = reader.readFilterArguments
    assert(filter(0).isInstanceOf[Scale])
    assert(filter(0).asInstanceOf[Scale].getScale == 4)
  }
  test ("Correct scale arg 1") {
    arguments = Array("--image", "./picture.png", "--scale", "1")
    reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    val filter = reader.readFilterArguments
    assert(filter(0).isInstanceOf[Scale])
    assert(filter(0).asInstanceOf[Scale].getScale == 1)
  }
  test ("Incorrect scale arg 12") {
    arguments = Array("--image", "./picture.png", "--scale", "12")
    reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    assertThrows[BadArgumentFormatException] {
      reader.readFilterArguments
    }
  }
  test ("Incorrect parameter format"){
    arguments = Array("--image", "./picture.png", "--scale", "a 0.25")
    reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    assertThrows[NumberFormatException] {
      reader.readFilterArguments
    }
  }
}
