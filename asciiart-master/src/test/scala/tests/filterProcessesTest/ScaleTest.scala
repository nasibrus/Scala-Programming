package tests.filterProcessesTest

import image.asciiImage.{Image, Pixel, PixelGrid}
import filter.Scale
import imageProcessing.conversionSet.SimpleConversionSet
import org.scalatest.FunSuite

class ScaleTest extends FunSuite{

  // support function to create image 1x1
  private def createOnePixelAsciiImage(grayscale: Int): Image = {
    val pixel = new Pixel(new SimpleConversionSet, grayscale)
    val pixels = Array.ofDim[Pixel](1,1)
    pixels(0)(0)  = pixel
    new Image(new PixelGrid(pixels))
  }

  // support function to create image 2x2
  private def create2x2AsciiImage(col0: Int, col1: Int, col2: Int, col3: Int): Image = {
    val pixels = Array.ofDim[Pixel](2,2)
    pixels(0)(0)  = new Pixel(new SimpleConversionSet, col0)
    pixels(0)(1)  = new Pixel(new SimpleConversionSet, col1)
    pixels(1)(0)  = new Pixel(new SimpleConversionSet, col2)
    pixels(1)(1)  = new Pixel(new SimpleConversionSet, col3)
    new Image(new PixelGrid(pixels))
  }

  test("Scale by 1") {

    // scale 1x1 image by 1 -> 1x1
    val grayscale: Int = 10
    var image = createOnePixelAsciiImage(grayscale)
    val scaler = new Scale(1)
    image = scaler.process(image)
    assert(image.width == 1)
    assert(image.height == 1)
    assert(image.getGrid.getPixel(0, 0).grayScale == grayscale)
  }

  test("Scale by 4") {
    // scale 1x1 image by 4 -> 2x2
    val grayscale: Int = 10
    var image = createOnePixelAsciiImage(grayscale)
    val scaler = new Scale(4)
    image = scaler.process(image)
    assert(image.width == 2)
    assert(image.height == 2)
    assert(image.getGrid.getPixel(0, 0).grayScale == grayscale)
    assert(image.getGrid.getPixel(0, 1).grayScale == grayscale)
    assert(image.getGrid.getPixel(1, 0).grayScale == grayscale)
    assert(image.getGrid.getPixel(1, 1).grayScale == grayscale)
  }

  test("Scale by 0.25"){
    // scale 2x2 image by 0.25 -> 1x1
    val grayscale: Int = 10
    var image = create2x2AsciiImage(grayscale,grayscale,grayscale,grayscale)
    val scaler = new Scale(0.25)
    image = scaler.process(image)
    assert(image.width == 1)
    assert(image.height == 1)
    assert(image.getGrid.getPixel(0,0).grayScale == grayscale)
  }
}
