package main

import imageProcessing.conversionSet.PaulBourkeConversionSet
import imageProcessing.{Converter, FilterHandler}
import input.ArgumentReader
import outputs.OutputHandler

class AsciiArtHandler {

  def execute(args: Array[String]): Unit ={
    // create argument handler
    val inputHandler = new ArgumentReader(args)

    // get loader from arguments
    val loader = inputHandler.getLoaderByPath

    // load image to BufferedImage
    val inputImage = loader.load

    // use Paul Bourke Conversion Set for conversion
    val conversionSet = new PaulBourkeConversionSet

    // create ascii converter
    val converter = new Converter

    // convert image to Ascii Image
    val asciiImage = converter.convertImageToAscii(inputImage, conversionSet)

    // get filters from arguments
    val filters = inputHandler.readFilterArguments

    // create filter handler
    val filterHandler = new FilterHandler

    // apply all filters on Ascii Image
    val filteredImage = filterHandler.handle(filters, asciiImage)

    // get outputs from arguments
    val outputs = inputHandler.readOutputSpecification

    // create output handler
    val outputHandler = new OutputHandler

    // render result Ascii Image to all outputs
    outputHandler.handle(outputs, filteredImage)

  }

}
