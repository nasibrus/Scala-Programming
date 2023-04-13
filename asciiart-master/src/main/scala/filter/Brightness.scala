package filter

import image.asciiImage.{Image, Pixel, PixelGrid}

class Brightness(brightness: Int) extends Filter{

  // used for testing
  def getBrightness: Int = brightness

  // apply brightness on all pixels in image
  override def process(image: Image): Image = {
    val pixels = Array.ofDim[Pixel](image.width, image.height)
    for(k <- 0 until image.width; l <- 0 until image.height){
      pixels(k)(l) = editPixelBrightness(image.getGrid.getPixel(k,l))
    }
    new Image(new PixelGrid(pixels))
  }

  // edit brightness of one pixel
  private def editPixelBrightness(pixel: Pixel): Pixel = {
    var brightValue = pixel.grayScale + brightness
    if(brightValue > 255)
      brightValue = 255
    if(brightValue < 0)
      brightValue = 0
    new Pixel(pixel.set, brightValue)
  }
}
