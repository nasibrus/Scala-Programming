package tests.filterProcessesTest

import image.asciiImage.{Image, Pixel, PixelGrid}
import filter.Invert
import imageProcessing.conversionSet.SimpleConversionSet
import org.scalatest.FunSuite

class InvertTest extends FunSuite{

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


  test("1x1 invert") {
    // invert 1x1 image
    val grayscale: Int = 10
    var image = createOnePixelAsciiImage(grayscale)
    val invertedGrayScale = 255 - grayscale
    val inverter = new Invert
    image = inverter.process(image)
    assert(invertedGrayScale == image.getGrid.getPixel(0, 0).grayScale)
  }


  test("2x2 invert"){
    // invert 2x2 image
    val col0 = 0
    val col1 = 10
    val col2 = 100
    val col3 = 250
    var image = create2x2AsciiImage(col0, col1, col2, col3)
    val inverter = new Invert
    image = inverter.process(image)
    assert(255 - col0 == image.getGrid.getPixel(0,0).grayScale)
    assert(255 - col1 == image.getGrid.getPixel(0,1).grayScale)
    assert(255 - col2 == image.getGrid.getPixel(1,0).grayScale)
    assert(255 - col3 == image.getGrid.getPixel(1,1).grayScale)
  }

}
