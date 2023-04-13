package filter

import image.asciiImage.Image

trait Filter {
  def process(image: Image): Image
}
