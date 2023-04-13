package filter
import image.asciiImage.{Image, Pixel, PixelGrid}
import filter.Coordinate._

class Flip(coordinate: Coordinate) extends Filter {

  // used for testing
  def getCoordinate: Char = if (coordinate == X) 'x' else 'y'

  // flip image by coordinate
  override def process(image: Image): Image = {
    val pixels = if (coordinate == X) flipByX(image) else flipByY(image)
    new Image(new PixelGrid(pixels))
  }

  // flip image by horizontal coordinate
  private def flipByX(image: Image): Array[Array[Pixel]] = {

    val pixels = Array.ofDim[Pixel](image.width, image.height)

    for(m <- 0 until image.width; n <- 0 until (image.height + 1) / 2) {
      pixels(m)(n) = image.getGrid.getPixel(m, image.height - n - 1)
      pixels(m)(image.height - n - 1) = image.getGrid.getPixel(m, n)
    }
    pixels
  }

  // flip image by horizontal coordinate
  private def flipByY(image: Image): Array[Array[Pixel]] = {

    val pixels = Array.ofDim[Pixel](image.width, image.height)

    for(m <- 0 until (image.width + 1) / 2 ; n <- 0 until image.height) {
      pixels(m)(n) = image.getGrid.getPixel(image.width - m - 1 , n)
      pixels(image.width - m - 1)(n) = image.getGrid.getPixel(m, n)
    }
    pixels
  }

}
