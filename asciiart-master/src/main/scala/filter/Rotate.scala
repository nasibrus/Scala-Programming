package filter
import image.asciiImage.{Image, Pixel, PixelGrid}
import imageProcessing.Converter
import imageProcessing.conversionSet.PaulBourkeConversionSet

class Rotate(degree: Int) extends Filter {

  // used for testing
  def getDegree: Int = degree

  // rotate image by corresponding degrees
  override def process(image: Image): Image = {
    var mutableImage = image
    var steps: Int = getSteps

    while(steps > 0){
      val prevImage = mutableImage
      mutableImage = rotateOnce(prevImage)
      steps -= 1
    }
    mutableImage
  }

  // rotate image by 90 degree clockwise
  private def rotateOnce(image: Image): Image = {
    val pixels = Array.ofDim[Pixel](image.height, image.width)
    for(i <- 0 until image.height; j <- 0 until image.width) {
      pixels(i)(j) = image.getGrid.getPixel(j, image.height - i - 1)
    }
    new Image(new PixelGrid(pixels))
  }

  // calculate number of 90 degree clockwise rotations
  private def getSteps: Int ={
    var steps = degree / 90 % 4
    if(steps < 0)
      steps += 4
    steps
  }

}
