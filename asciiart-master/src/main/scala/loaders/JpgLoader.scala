package loaders
import _root_.image.inputImage.{Grid, Input}
import exception.OpenImageException
import image.inputImage.{Grid, Input}

import java.awt._
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import image._

class JpgLoader(path: String) extends PathImageLoader {
  override val suffix: String = ".jpg"

  override def load: Input = {
    try {
      val file = new File(path)
      val bufferedImage = ImageIO.read(file)
      getImageFromBuffer(bufferedImage)
    }
    catch{
      case _: Exception => throw new OpenImageException
    }
  }

  def getImageFromBuffer(buffer: BufferedImage): Input = {
    val pixels = Array.ofDim[Color](buffer.getWidth, buffer.getHeight)
    for(x <- 0 until buffer.getWidth; y <- 0 until buffer.getHeight){
      pixels(x)(y) = new Color(buffer.getRGB(x,y))
    }
    new Input(new Grid(pixels))
  }


}
