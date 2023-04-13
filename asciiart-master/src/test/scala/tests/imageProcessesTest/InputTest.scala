package tests.imageProcessesTest

import java.awt.Color
import exception.PixelOutOfRange
import image.inputImage.{Grid, Input}
import org.scalatest.FunSuite

class InputTest extends FunSuite{
  test("0x0 Image"){
    val pixels = Array.ofDim[Color](0, 0)
    assertThrows[IllegalArgumentException] {
      val image = new Input(new Grid(pixels))
    }
  }

  test("5x0 Image"){
    val pixels = Array.ofDim[Color](5, 0)
    assertThrows[IllegalArgumentException] {
      val image = new Input(new Grid(pixels))
    }
  }

  test("0x5 Image"){
    val pixels = Array.ofDim[Color](0, 5)
    assertThrows[IllegalArgumentException] {
      val image = new Input(new Grid(pixels))
    }
  }

  test("Out of grid too big width"){
    val pixels = Array.ofDim[Color](5, 5)
    val image = new Input(new Grid(pixels))
    assertThrows[PixelOutOfRange] {
      image.getGrid.getPixel(5,1)
    }
  }

  test("Out of grid too big height"){
    val pixels = Array.ofDim[Color](5, 5)
    val image = new Input(new Grid(pixels))
    assertThrows[PixelOutOfRange] {
      image.getGrid.getPixel(1,5)
    }
  }

  test("Out of grid too small width"){
    val pixels = Array.ofDim[Color](5, 5)
    val image = new Input(new Grid(pixels))
    assertThrows[PixelOutOfRange] {
      image.getGrid.getPixel(-1,1)
    }
  }

  test("Out of grid too small height"){
    val pixels = Array.ofDim[Color](5, 5)
    val image = new Input(new Grid(pixels))
    assertThrows[PixelOutOfRange] {
      image.getGrid.getPixel(1,-1)
    }
  }

  test("In Grid pixel"){
    val pixels = Array.ofDim[Color](5, 5)
    for(x <- 0 until 5; y <- 0 until 5){
      pixels(x)(y) = new Color(0,0,0)
    }
    val image = new Input(new Grid(pixels))
    assert(image.getGrid.getPixel(0,0).isInstanceOf[Color])
    assert(image.getGrid.getPixel(4,4).isInstanceOf[Color])
  }


}
