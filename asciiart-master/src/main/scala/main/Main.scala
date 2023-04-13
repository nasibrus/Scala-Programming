package main

import exception.ExceptionHandler

object Main {

  def main(args: Array[String]): Unit = {
    val asciiArtHandler: AsciiArtHandler = new AsciiArtHandler()
    try asciiArtHandler.execute(args)
    catch{
      case e: Exception => {
        val exceptionHandler: ExceptionHandler = new ExceptionHandler
        exceptionHandler getMessage e
      }
    }
  }
}
