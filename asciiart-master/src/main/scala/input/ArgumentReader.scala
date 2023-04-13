package input

import exception._
import filter._
import loaders.{ImageLoader, JpgLoader, PngLoader}
import outputs.{ConsoleOutput, FileOutput, Output}

class ArgumentReader(args: Array[String]) {

  // current position in argument array
  private var position: Int = 0

  // get loader based on its path
  def getLoaderByPath: ImageLoader = {
    if(args.length < 2)
      throw new MissingImageException

    if(args(0) != "--image")
      throw new MissingImageException

    position += 2
    getLoaderBySuffix
  }

  // read output specification
  def readOutputSpecification: Seq[Output] = {
    var outputs: Array[Output] = new Array[Output](0)

    while(position != args.length){
      val currArg: String = args(position)
      outputs = outputs :+ {
        currArg match {
          case "--output-console" => new ConsoleOutput
          case "--output-file" =>
            handleNextArg()
            position += 1
            new FileOutput(args(position))
          case _ => throw new UnknownArgumentException
        }
      }
      position += 1

    }

    // no output was specified, use default console output
    if(outputs.length == 0)
      outputs = outputs :+ new ConsoleOutput

    outputs
  }

  // get filters from parameters
  def readFilterArguments: Seq[Filter] = {
    var filters: Array[Filter] = new Array[Filter](0)
    while(position != args.length){
      val currArg: String = args(position)
      filters = filters :+ {
        currArg match {
          case "--rotate" => getRotateFilter
          case "--scale" => getScaleFilter
          case "--invert" => getInvertFilter
          case "--flip" => getFlipFilter
          case "--brightness"  => getBrightnessFilter
          case "--output-console" => return filters
          case "--output-file" => return filters
          case _ => throw new UnknownArgumentException
        }
      }
      position += 2
    }
    filters
  }

  // get loader by suffix of an image
  private def getLoaderBySuffix: ImageLoader = {
    val suffix = args(1).substring(args(1).length - 4).toLowerCase()
    suffix match {
      case ".jpg" => new JpgLoader(args(1))
      case ".png" => new PngLoader(args(1))
      case _ => throw new ImageFormatException
    }
  }

  // get new rotate filter
  private def getRotateFilter: Filter = {
    handleNextArg()
    val rotate = args(position + 1).toInt
    if(rotate % 90 != 0)
      throw new BadArgumentFormatException
    new Rotate(rotate)
  }

  // get new scale filter
  private def getScaleFilter: Filter = {
    handleNextArg()
    val scale = args(position + 1).toDouble
    if(scale != 4 && scale != 0.25 && scale != 1)
      throw new BadArgumentFormatException
    new Scale(scale)
  }

  //get new invert filter
  private def getInvertFilter: Filter = {
    position -= 1
    new Invert
  }

  // get new flip filter
  private def getFlipFilter: Filter = {
    handleNextArg()
    val coordinate = flipParameterCheck
    if(coordinate == 'x') new Flip(Coordinate.X) else new Flip(Coordinate.Y)
  }

  // get new brightness filter
  private def getBrightnessFilter: Filter = {
    handleNextArg()
    val brightness = args(position + 1).toInt
    new Brightness(brightness)
  }

  // expected at least one more argument
  private def handleNextArg(): Unit = {
    if(!hasNextArg)
      throw new ArgumentCountException
  }

  // check if there is at least one more argument
  private def hasNextArg: Boolean = {
    position + 1 != args.length
  }

  // flip parameter must be "x" or "y"
  private def flipParameterCheck:Char = {
    if(args(position + 1) != "x" && args(position + 1) != "y")
      throw new BadArgumentFormatException
    args(position + 1)(0)
  }
}
