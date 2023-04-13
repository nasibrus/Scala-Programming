package filter

import image.asciiImage.{Image, Pixel, PixelGrid}

class Invert extends Filter {

  private val white: Int = 255

  // invert all pixels in image
  override def process(image: Image): Image = {
    val pixels = Array.ofDim[Pixel](image.width, image.height)

    for(a <- 0 until image.width; b <- 0 until image.height){
      pixels(a)(b) = getInvertedPixel(image.getGrid.getPixel(a,b))
    }
    new Image(new PixelGrid(pixels))
  }

  // invert grayscale of one pixel
  private def getInvertedPixel(pixel: Pixel): Pixel = {
    val invertedGrayScale = white - pixel.grayScale
    new Pixel(pixel.set, invertedGrayScale)
  }
}
