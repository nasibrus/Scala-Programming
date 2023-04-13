package tests.inputArgumentTest

import exception.{ArgumentCountException, BadArgumentFormatException}
import filter.Flip
import input.ArgumentReader
import org.scalatest.FunSuite

class FlipArgumentTest extends FunSuite {

  private var arguments: Array[String] = Array()
  private var reader = new ArgumentReader(arguments)

  test ("Missing flip parameter") {

    arguments = Array("--image", "./picture.png", "--flip")
    reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    assertThrows[ArgumentCountException] {
      reader.readFilterArguments
    }
  }

  test ("Correct flip filter format \"x\" coordinate") {
    arguments = Array("--image", "./picture.png", "--flip", "x")
    reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    val filter = reader.readFilterArguments
    assert(filter(0).isInstanceOf[Flip])
    assert(filter(0).asInstanceOf[Flip].getCoordinate == 'x')
  }

  test ("Correct flip filter format \"y\" coordinate") {
    // test correct flip filter format
    arguments = Array("--image", "./picture.png", "--flip", "y")
    reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    val filter = reader.readFilterArguments
    assert(filter(0).isInstanceOf[Flip])
    assert(filter(0).asInstanceOf[Flip].getCoordinate == 'y')
  }

  test ("Incorrect parameter format") {
    arguments = Array("--image", "./picture.png", "--flip", "yahoo")
    reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    assertThrows[BadArgumentFormatException] {
      reader.readFilterArguments
    }
  }

  test ("Incorrect parameter format \"yx\""){
    // test incorrect parameter format
    arguments = Array("--image", "./picture.png", "--flip", "yx")
    reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    assertThrows[BadArgumentFormatException] {
      reader.readFilterArguments
    }
  }
}
