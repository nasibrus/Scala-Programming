package imageProcessing

import image.asciiImage.Image
import filter.Filter

class FilterHandler {

  // apply all filters on image
  def handle(filters: Seq[Filter], image: Image): Image = {
    var mutableImage = image
    for(filter <- filters){
      mutableImage = filter.process(mutableImage)
    }
    mutableImage
  }
}
