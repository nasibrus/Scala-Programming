package outputs

import image.asciiImage.Image

trait Output {
  def render(image: Image): Unit
}
