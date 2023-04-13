package filter

import image.asciiImage.{Image, Pixel, PixelGrid}

class Scale(scale: Double) extends Filter{

  // used for testing
  def getScale: Double = scale

  // scale image
  override def process(image: Image): Image = {

    // no scaling
    if(scale == 1)
      return image

    val pixels =
      // enlarge image
      if (scale > 1)
        enlargeImage(image)
      // compress image
      else
        compressImage(image)

    new Image(new PixelGrid(pixels))
  }

  // enlarge image by value parameter
  private def enlargeImage(image: Image): Array[Array[Pixel]] = {
    val scaledHeight = (image.height * scale / 2).toInt
    val scaledWidth = (image.width * scale / 2).toInt
    val pixels = Array.ofDim[Pixel](scaledWidth, scaledHeight)

    for(i <- 0 until scaledWidth; j <- 0 until scaledHeight){
      pixels(i)(j) = getEnlargedPixel(image, i, j)
    }
    pixels
  }

  // get corresponding pixel when enlarging image
  private def getEnlargedPixel(image: Image, i: Int, j: Int): Pixel = {
    image.getGrid.getPixel(i * 2 / scale.toInt, j * 2 / scale.toInt)
  }

  // compress image by value
  private def compressImage(image: Image): Array[Array[Pixel]] = {
    val scaledHeight = (image.height * scale * 2).toInt
    val scaledWidth = (image.width * scale * 2).toInt
    val pixels = Array.ofDim[Pixel](scaledWidth, scaledHeight)
    for(i <- 0 until scaledWidth; j <- 0 until scaledHeight){
      pixels(i)(j) = compressPixel(image, i, j)
    }
    pixels
  }

  // compress multiple pixels into one
  private def compressPixel(image: Image, i: Int, j: Int): Pixel = {
    val pixelCount: Int = (scale / scale / scale).toInt / 2
    var pixels = new Array[Pixel](0)
    for(k <- i * pixelCount until i * pixelCount + pixelCount; l <- j * pixelCount until j * pixelCount + pixelCount){
      pixels = pixels :+ image.getGrid.getPixel(k, l)
    }
    calculateAverage(pixels)
  }

  // calculate average grayscale of pixel and use first first ones (in the array) ConversionSet for result AsciiPixel
  private def calculateAverage(pixels: Array[Pixel]): Pixel = {
    var avgGrayScale: Int = 0
    for(pixel <- pixels){
      avgGrayScale += pixel.grayScale
    }
    avgGrayScale = avgGrayScale / pixels.length
    new Pixel(pixels(0).set, avgGrayScale)
  }
}
