package image.inputImage

import java.awt.Color

import exception.PixelOutOfRange

class Grid (pixels: Array[Array[Color]]) {

  // grid cannot be empty
  if(pixels.length == 0)
    throw new IllegalArgumentException

  // grid cannot be empty
  if(pixels.exists(e => e.length == 0))
    throw new IllegalArgumentException

  val width: Int = pixels.length
  val height: Int = pixels(0).length

  def getPixel(x: Int, y: Int): Color = {
    if(x > width - 1 || x < 0)
      throw new PixelOutOfRange

    if(y > height - 1 || y < 0)
      throw new PixelOutOfRange

    pixels(x)(y)
  }
}
