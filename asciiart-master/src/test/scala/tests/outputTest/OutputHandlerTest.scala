package tests.outputTest

import java.nio.file.{Files, Paths}

import image.asciiImage.{Image, Pixel, PixelGrid}
import imageProcessing.conversionSet.SimpleConversionSet
import org.scalatest.FunSuite
import outputs.{ConsoleOutput, FileOutput, OutputHandler}

class OutputHandlerTest extends FunSuite{
  private val outputHandler = new OutputHandler

  private val pixel = new Pixel(new SimpleConversionSet, 10)
  private val pixels = Array.ofDim[Pixel](1, 1)
  pixels(0)(0) = pixel
  private val asciiImage = new Image(new PixelGrid(pixels))

  test("Console output") {
    // pixel with char '@'
    val consoleOutput = new ConsoleOutput
    // no exception thrown, successful write in console (char '@')
    outputHandler.handle(Seq(consoleOutput), asciiImage)
  }

  test("File output") {
    val fileOutput = new FileOutput("./test1.txt")
    outputHandler.handle(Seq(fileOutput), asciiImage)
    assert(Files.exists(Paths.get("./test1.txt")))
    Files.delete(Paths.get("./test1.txt"))
  }

  test("Console and file outputs"){
    val consoleOutput = new ConsoleOutput
    val fileOutput = new FileOutput("./test3.txt")
    outputHandler.handle(Seq(consoleOutput, fileOutput), asciiImage)
    assert(Files.exists(Paths.get("./test3.txt")))
    Files.delete(Paths.get("./test3.txt"))
  }

  test("Two file outputs"){
    val fileOutput1 = new FileOutput("./test1.txt")
    val fileOutput2 = new FileOutput("./test2.txt")
    outputHandler.handle(Seq(fileOutput1, fileOutput2), asciiImage)
    assert(Files.exists(Paths.get("./test1.txt")))
    assert(Files.exists(Paths.get("./test2.txt")))

    Files.delete(Paths.get("./test1.txt"))
    Files.delete(Paths.get("./test2.txt"))
  }

}
