package outputs

import image.asciiImage.Image

class ConsoleOutput extends Output {

  // write image to console output
  override def render(image: Image): Unit = {
    for(x <- 0 until image.height){
      for(y <- 0 until image.width) {
        print(image.getGrid.getPixel(y, x).char)
      }
      println()
    }
  }

}
