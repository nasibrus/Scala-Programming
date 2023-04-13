package exception

import java.sql.DriverManager.println

class PixelOutOfRange extends Exception { override def getMessage: String = "Pixel position isn't in the picture" }
class MissingImageException extends Exception { override def getMessage: String = "Missing image or image path argument" }
class ArgumentCountException extends Exception { override def getMessage: String = "Invalid argument count" }
class UnknownArgumentException extends Exception { override def getMessage: String = "Unknown argument" }
class BadArgumentFormatException extends Exception { override def getMessage: String = "Invalid argument format" }
class OpenImageException extends Exception { override def getMessage: String = "Image not found or cannot be opened" }
class ImageFormatException extends Exception { override def getMessage: String = "Unsupported image format" }

class ExceptionHandler {
  def getMessage(exception: Exception): Unit = {
    println("Error: " + exception.getMessage)
  }
}
