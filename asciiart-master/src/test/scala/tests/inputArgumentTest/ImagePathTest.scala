package tests.inputArgumentTest

import exception.{ImageFormatException, MissingImageException, UnknownArgumentException}
import filter.Invert
import input.ArgumentReader
import loaders.{JpgLoader, PngLoader}
import org.scalatest.FunSuite

class ImagePathTest extends FunSuite{
  private var arguments: Array[String] = Array()
  private var reader = new ArgumentReader(arguments)

  test("Missing path parameter") {
    arguments = Array("--image")
    reader = new ArgumentReader(arguments)
    assertThrows[MissingImageException] {
      reader.getLoaderByPath
    }
  }

  test("Missing \"--image\"") {
    arguments = Array("--rotate x")
    reader = new ArgumentReader(arguments)
    assertThrows[MissingImageException] {
      reader.getLoaderByPath
    }
  }
  test("Unsupported image format") {
    // test unsupported image format
    arguments = Array("--image", "./picture.bmp")
    reader = new ArgumentReader(arguments)
    assertThrows[ImageFormatException] {
      reader.getLoaderByPath
    }
  }

  test("Correct image format with supported image format"){
    arguments = Array("--image", "./picture.png")
    reader = new ArgumentReader(arguments)
    assert(reader.getLoaderByPath.isInstanceOf[PngLoader])
    arguments = Array("--image", "./picture.jpg")
    reader = new ArgumentReader(arguments)
    assert(reader.getLoaderByPath.isInstanceOf[JpgLoader])
  }

}
