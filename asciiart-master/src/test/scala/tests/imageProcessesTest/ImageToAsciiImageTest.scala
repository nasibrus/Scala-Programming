package tests.imageProcessesTest

import java.awt.Color
import java.awt.image.BufferedImage

import imageProcessing.Converter
import imageProcessing.conversionSet.PaulBourkeConversionSet
import image.inputImage.{Grid, Input}
import org.scalatest.FunSuite

class ImageToAsciiImageTest extends FunSuite{

  // support function to create buffer image 2x2 of one color
  private def create2x2BufferedImage(value: Int): Input = {
    val pixels = Array.ofDim[Color](2,2)
    pixels(0)(0)  = new Color(value,value,value)
    pixels(0)(1)  = new Color(value,value,value)
    pixels(1)(0)  = new Color(value,value,value)
    pixels(1)(1)  = new Color(value,value,value)
    new Input(new Grid(pixels))
  }

  test("Black color image") {
    val converter = new Converter
    val conversionSet = new PaulBourkeConversionSet

    // blackest char of PaulBourkeConversionSet is '$'
    val blackChar = '$'
    val color = 0

    // create black 2x2 buffered image
    val bufferedImage = create2x2BufferedImage(color)
    val asciiImage = converter.convertImageToAscii(bufferedImage, conversionSet)

    assert(asciiImage.getGrid.getPixel(0, 0).char == blackChar)
    assert(asciiImage.getGrid.getPixel(0, 1).char == blackChar)
    assert(asciiImage.getGrid.getPixel(1, 0).char == blackChar)
    assert(asciiImage.getGrid.getPixel(1, 1).char == blackChar)
  }


  test("Custom color image"){

    val converter = new Converter
    val conversionSet = new PaulBourkeConversionSet
    val blackChar = '$'
    // custom color
    val color = 100
    val bufferedImage = create2x2BufferedImage(color)
    val asciiImage = converter.convertImageToAscii(bufferedImage, conversionSet)

    assert(asciiImage.getGrid.getPixel(0,0).char != blackChar)
    assert(asciiImage.getGrid.getPixel(0,1).char != blackChar)
    assert(asciiImage.getGrid.getPixel(1,0).char != blackChar)
    assert(asciiImage.getGrid.getPixel(1,1).char != blackChar)

  }
}
