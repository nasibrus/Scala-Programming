package imageProcessing.conversionSet

class PaulBourkeConversionSet extends ConversionSet {


  private val set = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. "

  // get char on corresponding position
  override def getConvertedChar(value: Int): Char = {
    var pom = value
    while(pom < 0)
      pom += set.length
    set(pom % set.length)
  }

}
