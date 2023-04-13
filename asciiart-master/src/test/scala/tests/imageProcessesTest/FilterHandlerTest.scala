package tests.imageProcessesTest

import image.asciiImage.{Image, Pixel, PixelGrid}
import filter.{Coordinate, Flip, Rotate}
import imageProcessing.FilterHandler
import imageProcessing.conversionSet.SimpleConversionSet
import org.scalatest.FunSuite

class FilterHandlerTest extends FunSuite{

  // support function to create image 2x2
  private def create2x2AsciiImage(col0: Int, col1: Int, col2: Int, col3: Int): Image = {
    val pixels = Array.ofDim[Pixel](2,2)
    pixels(0)(0)  = new Pixel(new SimpleConversionSet, col0)
    pixels(0)(1)  = new Pixel(new SimpleConversionSet, col1)
    pixels(1)(0)  = new Pixel(new SimpleConversionSet, col2)
    pixels(1)(1)  = new Pixel(new SimpleConversionSet, col3)
    new Image(new PixelGrid(pixels))
  }


  test("Flip than rotate") {
    val flipFilter = new Flip(Coordinate.X)
    val rotateFilter = new Rotate(90)
    val filterHandler = new FilterHandler
    // apply flip by x, than rotate by 90 degree  AC  ->  BD  ->  AB
    //                                            BD      AC      CD
    val A = 0 // (0,0)
    val B = 10 // (0,1)
    val C = 100 // (1,0)
    val D = 250 // (1,1)
    val image = create2x2AsciiImage(A, B, C, D)

    val filteredImage = filterHandler.handle(Seq(flipFilter, rotateFilter), image)
    assert(filteredImage.getGrid.getPixel(0, 0).grayScale == A)
    assert(filteredImage.getGrid.getPixel(0, 1).grayScale == C)
    assert(filteredImage.getGrid.getPixel(1, 0).grayScale == B)
    assert(filteredImage.getGrid.getPixel(1, 1).grayScale == D)
  }
  test("Rotate then flip") {


    val flipFilter = new Flip(Coordinate.X)
    val rotateFilter = new Rotate(90)
    val filterHandler = new FilterHandler
    // apply rotate by 90 degree, than flip by x  AC  ->  BA  ->  DC
    //                                            BD      DC      BA
    val A = 0 // (0,0)
    val B = 10 // (0,1)
    val C = 100 // (1,0)
    val D = 250 // (1,1)
    val image = create2x2AsciiImage(A, B, C, D)

    val filteredImage = filterHandler.handle(Seq(rotateFilter, flipFilter), image)
    assert(filteredImage.getGrid.getPixel(0, 0).grayScale == D)
    assert(filteredImage.getGrid.getPixel(0, 1).grayScale == B)
    assert(filteredImage.getGrid.getPixel(1, 0).grayScale == C)
    assert(filteredImage.getGrid.getPixel(1, 1).grayScale == A)


  }
  test("No filter"){
    // apply no filter AC  ->  AC
    //                 BD      BD
    val A = 0 // (0,0)
    val B = 10 // (0,1)
    val C = 100 // (1,0)
    val D = 250 // (1,1)
    val image = create2x2AsciiImage(A, B, C, D)

    val filterHandler = new FilterHandler
    val filteredImage = filterHandler.handle(Seq(), image)
    assert(filteredImage.getGrid.getPixel(0,0).grayScale == A)
    assert(filteredImage.getGrid.getPixel(0,1).grayScale == B)
    assert(filteredImage.getGrid.getPixel(1,0).grayScale == C)
    assert(filteredImage.getGrid.getPixel(1,1).grayScale == D)
  }
}
