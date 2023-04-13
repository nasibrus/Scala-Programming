package tests.filterProcessesTest

import image.asciiImage.{Image, Pixel, PixelGrid}
import filter.Brightness
import imageProcessing.conversionSet.SimpleConversionSet
import org.scalatest.FunSuite

class BrightnessTest extends FunSuite {

  // support function to create image 2x2
  private def create2x2AsciiImage(col0: Int, col1: Int, col2: Int, col3: Int): Image = {
    val pixels = Array.ofDim[Pixel](2,2)
    pixels(0)(0)  = new Pixel(new SimpleConversionSet, col0)
    pixels(0)(1)  = new Pixel(new SimpleConversionSet, col1)
    pixels(1)(0)  = new Pixel(new SimpleConversionSet, col2)
    pixels(1)(1)  = new Pixel(new SimpleConversionSet, col3)
    new Image(new PixelGrid(pixels))
  }

  test("Brightness +100") {
    // edit brightness 2x2 image by +100    0 100  ->  100 200
    //                                     10 250      110 255
    val brightness = 100
    val A = 0 // (0,0)
    val B = 10 // (0,1)
    val C = 100 // (1,0)
    val D = 250 // (1,1)
    val brighter = new Brightness(brightness)
    var image = create2x2AsciiImage(A, B, C, D)
    image = brighter.process(image)
    assert(100 == image.getGrid.getPixel(0, 0).grayScale)
    assert(110 == image.getGrid.getPixel(0, 1).grayScale)
    assert(200 == image.getGrid.getPixel(1, 0).grayScale)
    assert(255 == image.getGrid.getPixel(1, 1).grayScale)
  }

  test("Brightness 0"){
    // edit brightness 2x2 image by 0       0 100  ->  0  95
    //                                     10 250      5 240
    val brightness = 0
    val A = 0     // (0,0)
    val B = 10    // (0,1)
    val C = 100   // (1,0)
    val D = 250   // (1,1)
    val brighter = new Brightness(brightness)
    var image = create2x2AsciiImage(A, B, C, D)
    image = brighter.process(image)
    assert(0 == image.getGrid.getPixel(0,0).grayScale)
    assert(10 == image.getGrid.getPixel(0,1).grayScale)
    assert(100 == image.getGrid.getPixel(1,0).grayScale)
    assert(250 == image.getGrid.getPixel(1,1).grayScale)
  }

  test("Brightness -5"){
    // edit brightness 2x2 image by -5      0 100  ->  0  95
    //                                     10 250      5 240
    val brightness = -5
    val A = 0     // (0,0)
    val B = 10    // (0,1)
    val C = 100   // (1,0)
    val D = 250   // (1,1)
    val brighter = new Brightness(brightness)
    var image = create2x2AsciiImage(A, B, C, D)
    image = brighter.process(image)
    assert(0 == image.getGrid.getPixel(0,0).grayScale)
    assert(5 == image.getGrid.getPixel(0,1).grayScale)
    assert(95 == image.getGrid.getPixel(1,0).grayScale)
    assert(245 == image.getGrid.getPixel(1,1).grayScale)
  }

  test("Brightness -1000"){
    // edit brightness 2x2 image by -1000      0 100  ->  0  0
    //                                        10 250      0  0
    val brightness = -1000
    val A = 0     // (0,0)
    val B = 10    // (0,1)
    val C = 100   // (1,0)
    val D = 250   // (1,1)
    val brighter = new Brightness(brightness)
    var image = create2x2AsciiImage(A, B, C, D)
    image = brighter.process(image)
    assert(0 == image.getGrid.getPixel(0,0).grayScale)
    assert(0 == image.getGrid.getPixel(0,1).grayScale)
    assert(0 == image.getGrid.getPixel(1,0).grayScale)
    assert(0 == image.getGrid.getPixel(1,1).grayScale)
  }

  test("Brightness +1000"){
    // edit brightness 2x2 image by 1000      0 100  ->  0  0
    //                                       10 250      0  0
    val brightness = 1000
    val A = 0     // (0,0)
    val B = 10    // (0,1)
    val C = 100   // (1,0)
    val D = 250   // (1,1)
    val brighter = new Brightness(brightness)
    var image = create2x2AsciiImage(A, B, C, D)
    image = brighter.process(image)
    assert(255 == image.getGrid.getPixel(0,0).grayScale)
    assert(255 == image.getGrid.getPixel(0,1).grayScale)
    assert(255 == image.getGrid.getPixel(1,0).grayScale)
    assert(255 == image.getGrid.getPixel(1,1).grayScale)
  }

}
