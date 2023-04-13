package tests.inputArgumentTest

import exception.{ArgumentCountException, UnknownArgumentException}
import input.ArgumentReader
import org.scalatest.FunSuite
import outputs.{ConsoleOutput, FileOutput}

class OutputArgumentTest extends FunSuite{
  private var arguments: Array[String] = Array()
  private var reader = new ArgumentReader(arguments)

  test("Incorrect console output argument") {

    arguments = Array("--image", "./picture.png", "--output-console", "file.txt")
    reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    assertThrows[UnknownArgumentException] {
      reader.readOutputSpecification
    }
  }
  test("Correct console output syntax") {
    arguments = Array("--image", "./picture.png", "--output-console")
    reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    val loader = reader.readOutputSpecification
    assert(loader.length == 1)
    assert(loader(0).isInstanceOf[ConsoleOutput])
  }
  test("Default output - console output") {
    arguments = Array("--image", "./picture.png")
    reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    val loader = reader.readOutputSpecification
    assert(loader(0).isInstanceOf[ConsoleOutput])
  }
  test("Correct file output syntax") {
    arguments = Array("--image", "./picture.png", "--output-file", "file.txt")
    reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    val loader = reader.readOutputSpecification
    assert(loader(0).isInstanceOf[FileOutput])
  }
  test("Missing file output argument") {
    arguments = Array("--image", "./picture.png", "--output-file")
    reader = new ArgumentReader(arguments)
    reader.getLoaderByPath
    assertThrows[ArgumentCountException] {
      reader.readOutputSpecification
    }
  }
}
