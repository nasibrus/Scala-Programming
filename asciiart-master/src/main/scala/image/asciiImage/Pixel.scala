package image.asciiImage

import imageProcessing.conversionSet.ConversionSet

class Pixel(val set: ConversionSet, val grayScale: Int){
    def char: Char = set.getConvertedChar(grayScale)
}
