package imageProcessing

import image.asciiImage.{Image, Pixel, PixelGrid}
import image.inputImage.Input
import imageProcessing.conversionSet.ConversionSet

import java.awt.Color

class Converter {

  // convert Image to Ascii image
  def convertImageToAscii(image: Input, conversionSet: ConversionSet): Image = {

    // init 2D array of AsciiPixels
    val pixels = Array.ofDim[Pixel](image.width, image.height)

    // fill 2D array of AsciiPixels by converting each pixel from image to character
   for(x <- 0 until image.width; y <- 0 until image.height) {
     val pixel = image.getGrid.getPixel(x,y)
     pixels(x)(y) = convertPixelToAsciiPixel(conversionSet, getGrayScale(pixel))
   }
    new Image(new PixelGrid(pixels))
  }

  // get gray scale value from pixel color
  private def getGrayScale(color: Color): Int = (0.3 * color.getRed + 0.59 * color.getGreen + 0.11 * color.getBlue).toInt

  // convert pixel to char using conversionSet and pixels color(RGB)
  private def convertPixelToAsciiPixel(conversionSet: ConversionSet, grayScale: Int) : Pixel = {
    new Pixel(conversionSet,grayScale)
  }

}

