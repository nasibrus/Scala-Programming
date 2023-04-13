package tests.loaderTest

import exception.OpenImageException
import image.inputImage.{Input, Grid}
import loaders.{JpgLoader, PngLoader}
import org.scalatest.FunSuite

class LoadersTest extends FunSuite {

  test("successfully load png image") {
    val pngLoader = new PngLoader("src/test/scala/resource/test1.png")
    assert(pngLoader.suffix == ".png")
    assert(pngLoader.load.isInstanceOf[Input])
  }

  test("Not existing png image") {
    val pngLoader = new PngLoader("src/test/scala/resource/notexisting.png")
    assertThrows[OpenImageException] {
      pngLoader.load
    }
  }
  test("Successfully load jpg image") {
    val jpgLoader = new JpgLoader("src/test/scala/resource/test1.png")
    assert(jpgLoader.suffix == ".jpg")
    assert(jpgLoader.load.isInstanceOf[Input])
  }

  test("Not existing jpg image"){
    val jpgLoader = new JpgLoader("src/test/scala/resource/notexisting.jpg")
    assertThrows[OpenImageException]{
      jpgLoader.load
    }
  }

}

