package tests.imageProcessesTest

import exception.PixelOutOfRange
import imageProcessing.conversionSet.SimpleConversionSet
import image.asciiImage.{Image, Pixel, PixelGrid}
import org.scalatest.FunSuite

class AsciiTest extends FunSuite{
  test("0x0 Image"){
    val pixels = Array.ofDim[Pixel](0, 0)
    assertThrows[IllegalArgumentException] {
      val image = new Image(new PixelGrid(pixels))
    }
  }

  test("5x0 Image"){
    val pixels = Array.ofDim[Pixel](5, 0)
    assertThrows[IllegalArgumentException] {
      val image = new Image(new PixelGrid(pixels))
    }
  }

  test("0x5 Image"){
    val pixels = Array.ofDim[Pixel](0, 5)
    assertThrows[IllegalArgumentException] {
      val image = new Image(new PixelGrid(pixels))
    }
  }

  test("Out of grid too big width"){
    val pixels = Array.ofDim[Pixel](5, 5)
    val image = new Image(new PixelGrid(pixels))
    assertThrows[PixelOutOfRange] {
      image.getGrid.getPixel(6,1)
    }
  }

  test("Out of grid too big height"){
    val pixels = Array.ofDim[Pixel](5, 5)
    val image = new Image(new PixelGrid(pixels))
    assertThrows[PixelOutOfRange] {
      image.getGrid.getPixel(1,6)
    }
  }

  test("Out of grid too small width"){
    val pixels = Array.ofDim[Pixel](5, 5)
    val image = new Image(new PixelGrid(pixels))
    assertThrows[PixelOutOfRange] {
      image.getGrid.getPixel(-1,1)
    }
  }

  test("Out of grid too small height"){
    val pixels = Array.ofDim[Pixel](5, 5)
    val image = new Image(new PixelGrid(pixels))
    assertThrows[PixelOutOfRange] {
      image.getGrid.getPixel(1,-1)
    }
  }

  test("In Grid pixel"){
    val pixels = Array.ofDim[Pixel](5, 5)
    for(x <- 0 until 5; y <- 0 until 5){
      pixels(x)(y) = new Pixel(new SimpleConversionSet, 0)
    }
    val image = new Image(new PixelGrid(pixels))
    assert(image.getGrid.getPixel(0,0).isInstanceOf[Pixel])
    assert(image.getGrid.getPixel(4,4).isInstanceOf[Pixel])
  }


}
