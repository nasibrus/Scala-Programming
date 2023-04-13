package tests.filterProcessesTest

import image.asciiImage.{Image, Pixel, PixelGrid}
import filter.{Coordinate, Flip}
import imageProcessing.conversionSet.SimpleConversionSet
import org.scalatest.FunSuite

class FlipTest extends FunSuite{

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

  test("1x1 flip x") {

    // flip 1x1 image by x -> the same image
    val grayscale: Int = 10
    var image = createOnePixelAsciiImage(grayscale)
    val flipper = new Flip(Coordinate.X)
    image = flipper.process(image)
    assert(grayscale == image.getGrid.getPixel(0, 0).grayScale)
  }

  test("1x1 flip y") {

    // flip 1x1 image by y -> the same image
    val grayscale: Int = 10
    var image = createOnePixelAsciiImage(grayscale)
    val flipper = new Flip(Coordinate.Y)
    image = flipper.process(image)
    assert(grayscale == image.getGrid.getPixel(0, 0).grayScale)
  }
  test("2x2 flip x") {
    // flip 2x2 image by x  AC  ->  BD
    //                      BD      AC
    val A = 0 // (0,0)
    val B = 10 // (0,1)
    val C = 100 // (1,0)
    val D = 250 // (1,1)
    val flipper = new Flip(Coordinate.X)
    var image = create2x2AsciiImage(A, B, C, D)
    image = flipper.process(image)
    assert(B == image.getGrid.getPixel(0, 0).grayScale)
    assert(A == image.getGrid.getPixel(0, 1).grayScale)
    assert(D == image.getGrid.getPixel(1, 0).grayScale)
    assert(C == image.getGrid.getPixel(1, 1).grayScale)
  }

  test("2x2 flip y"){
    // flip 2x2 image by y  AC  ->  CA
    //                      BD      DB
    val A = 0     // (0,0)
    val B = 10    // (0,1)
    val C = 100   // (1,0)
    val D = 250   // (1,1)
    val flipper = new Flip(Coordinate.Y)
    var image = create2x2AsciiImage(A, B, C, D)
    image = flipper.process(image)
    assert(C == image.getGrid.getPixel(0,0).grayScale)
    assert(D == image.getGrid.getPixel(0,1).grayScale)
    assert(A == image.getGrid.getPixel(1,0).grayScale)
    assert(B == image.getGrid.getPixel(1,1).grayScale)
  }

}
