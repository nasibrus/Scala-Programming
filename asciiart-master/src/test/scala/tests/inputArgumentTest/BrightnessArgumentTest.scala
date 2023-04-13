package tests.inputArgumentTest

import exception.ArgumentCountException
import filter.Brightness
import input.ArgumentReader
import org.scalatest.FunSuite

class BrightnessArgumentTest extends FunSuite {

  private var arguments: Array[String] = Array()
  private var reader = new ArgumentReader(arguments)

  test ("Missing brightness parameter") {
    arguments = Array("--image", "./picture.png", "--brightness")
    reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    assertThrows[ArgumentCountException] {
      reader.readFilterArguments
    }
  }

  test ("Correct brightness filter format 30") {
    arguments = Array("--image", "./picture.png", "--brightness", "30")
    reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    val filter = reader.readFilterArguments
    assert(filter(0).isInstanceOf[Brightness])
    assert(filter(0).asInstanceOf[Brightness].getBrightness == 30)
  }

  test ("Correct brightness filter format +30") {
    arguments = Array("--image", "./picture.png", "--brightness", "+30")
    reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    val filter = reader.readFilterArguments
    assert(filter(0).isInstanceOf[Brightness])
    assert(filter(0).asInstanceOf[Brightness].getBrightness == 30)
  }

  test ("Correct brightness filter format -30") {
    arguments = Array("--image", "./picture.png", "--brightness", "-30")
    reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    val filter = reader.readFilterArguments
    assert(filter(0).isInstanceOf[Brightness])
    assert(filter(0).asInstanceOf[Brightness].getBrightness == -30)
  }

  test ("Incorrect parameter format"){
    arguments = Array("--image", "./picture.png", "--brightness", "90a")
    reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    assertThrows[NumberFormatException] {
      reader.readFilterArguments
    }
  }
}
