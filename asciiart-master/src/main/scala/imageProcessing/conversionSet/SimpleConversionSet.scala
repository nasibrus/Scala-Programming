package imageProcessing.conversionSet

class SimpleConversionSet extends ConversionSet {


  private val set  = "@%#*+=-:. "

  // get char on corresponding position
  override def getConvertedChar(value: Int): Char = {
    var pom = value
    while(pom < 0)
      pom += set.length
    set(pom % set.length)
  }

}
