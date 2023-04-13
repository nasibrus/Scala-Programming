package outputs

import image.asciiImage.Image

// create
class OutputHandler {
  def handle(outputs: Seq[Output], image: Image): Unit = {
    for(out <- outputs)
      out.render(image)
  }
}
