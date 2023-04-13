package image.asciiImage

class Image(grid: PixelGrid) {
  def height: Int = grid.height
  def width: Int = grid.width
  def getGrid: PixelGrid = grid
}
