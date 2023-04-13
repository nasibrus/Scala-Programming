package tests.filterProcessesTest

import image.asciiImage.{Image, Pixel, PixelGrid}
import filter.Rotate
import imageProcessing.conversionSet.SimpleConversionSet
import org.scalatest.FunSuite

class RotateTest extends FunSuite {
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


  test("1x1 rotate 90") {
    // rotate 1x1 image, no change
    val grayscale: Int = 10
    var image = createOnePixelAsciiImage(grayscale)
    val rotter = new Rotate(90)
    image = rotter.process(image)
    assert(grayscale == image.getGrid.getPixel(0, 0).grayScale)
  }


  test("2x2 rotate 90") {
    // rotate 2x2 image by 90 degree clockwise AC  ->  BA
    //                                         BD      DC
    val A = 0 // (0,0)
    val B = 10 // (0,1)
    val C = 100 // (1,0)
    val D = 250 // (1,1)
    val rotter = new Rotate(90)
    var image = create2x2AsciiImage(A, B, C, D)
    image = rotter.process(image)
    assert(B == image.getGrid.getPixel(0, 0).grayScale)
    assert(D == image.getGrid.getPixel(0, 1).grayScale)
    assert(A == image.getGrid.getPixel(1, 0).grayScale)
    assert(C == image.getGrid.getPixel(1, 1).grayScale)
  }


  test("2x2 rotate -180"){
    // rotate 2x2 image by 180 degree anti-clockwise AC  ->  CD  ->  DB
    //                                               BD      AB      CA
    val A = 0     // (0,0)
    val B = 10    // (0,1)
    val C = 100   // (1,0)
    val D = 250   // (1,1)
    val rotter = new Rotate(-180)
    var image = create2x2AsciiImage(A, B, C, D)
    image = rotter.process(image)
    assert(D == image.getGrid.getPixel(0,0).grayScale)
    assert(C == image.getGrid.getPixel(0,1).grayScale)
    assert(B == image.getGrid.getPixel(1,0).grayScale)
    assert(A == image.getGrid.getPixel(1,1).grayScale)
  }

  test("2x2 rotate -360"){
    val A = 0     // (0,0)
    val B = 10    // (0,1)
    val C = 100   // (1,0)
    val D = 250   // (1,1)
    val rotter = new Rotate(-360)
    var image = create2x2AsciiImage(A, B, C, D)
    image = rotter.process(image)
    assert(A == image.getGrid.getPixel(0,0).grayScale)
    assert(B == image.getGrid.getPixel(0,1).grayScale)
    assert(C == image.getGrid.getPixel(1,0).grayScale)
    assert(D == image.getGrid.getPixel(1,1).grayScale)
  }

  test("2x2 rotate 0"){
    val A = 0     // (0,0)
    val B = 10    // (0,1)
    val C = 100   // (1,0)
    val D = 250   // (1,1)
    val rotter = new Rotate(0)
    var image = create2x2AsciiImage(A, B, C, D)
    image = rotter.process(image)
    assert(A == image.getGrid.getPixel(0,0).grayScale)
    assert(B == image.getGrid.getPixel(0,1).grayScale)
    assert(C == image.getGrid.getPixel(1,0).grayScale)
    assert(D == image.getGrid.getPixel(1,1).grayScale)
  }

  test("2x2 rotate 450"){
    val A = 0     // (0,0)
    val B = 10    // (0,1)
    val C = 100   // (1,0)
    val D = 250   // (1,1)
    val rotter = new Rotate(450)
    var image = create2x2AsciiImage(A, B, C, D)
    image = rotter.process(image)
    assert(B == image.getGrid.getPixel(0,0).grayScale)
    assert(D == image.getGrid.getPixel(0,1).grayScale)
    assert(A == image.getGrid.getPixel(1,0).grayScale)
    assert(C == image.getGrid.getPixel(1,1).grayScale)
  }

}
