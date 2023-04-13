package tests.inputArgumentTest

import exception.{ArgumentCountException, BadArgumentFormatException}
import filter.Rotate
import input.ArgumentReader
import org.scalatest.FunSuite

class RotateArgumentTest extends FunSuite {
  private var arguments: Array[String] = Array()
  private var reader = new ArgumentReader(arguments)
  test("missing rotate arg") {

    arguments = Array("--image", "./picture.png", "--rotate")
    reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    assertThrows[ArgumentCountException] {
      reader.readFilterArguments
    }
  }

  test("Incorrect rotate arg format") {
    arguments = Array("--image", "./picture.png", "--rotate", "9a0")
    reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    assertThrows[NumberFormatException] {
      reader.readFilterArguments
    }
  }
  test("incorrect rotate arg format - not divisible by 90") {
    arguments = Array("--image", "./picture.png", "--rotate", "91")
    reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    assertThrows[BadArgumentFormatException] {
      reader.readFilterArguments
    }
  }
  test("Correct rotate argument format") {
    arguments = Array("--image", "./picture.png", "--rotate", "90")
    reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    val filters = reader.readFilterArguments
    assert(filters(0).isInstanceOf[Rotate])
    assert(filters(0).asInstanceOf[Rotate].getDegree == 90)
  }
}
