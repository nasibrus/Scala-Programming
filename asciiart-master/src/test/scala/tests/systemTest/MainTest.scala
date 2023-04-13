package tests.systemTest

import java.nio.file.{Files, Paths}

import main.AsciiArtHandler
import org.scalatest.FunSuite


class MainTest extends  FunSuite {

  test("System test"){
    val arg = Array("--image", "./src/test/scala/resource/test1.png", "--rotate", "90", "--output-file", "systemTest.txt")
    val asciiArtHandler = new AsciiArtHandler
    asciiArtHandler.execute(arg)
    assert(Files.exists(Paths.get("./systemTest.txt")))
  }
}