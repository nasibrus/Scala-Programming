package outputs

import java.io.{BufferedWriter, File, FileWriter}

import image.asciiImage.Image

class FileOutput(path: String) extends Output {

  // write image to file
  override def render(image: Image): Unit = {
    val file = new File(path)
    val bw = new BufferedWriter(new FileWriter(file))
    for(x <- 0 until image.height){
      for(y <- 0 until image.width) {
        bw.write(image.getGrid.getPixel(y, x).char)
      }
      bw.write("\n")
    }
    bw.close()
  }

}
