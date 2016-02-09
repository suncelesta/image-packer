package packer

class MutableRect(var centerX: Double,
                  var centerY: Double,
                  var width: Double,
                  var height: Double) extends Rect {

  def moveBy(dx: Double, dy: Double): Unit = {
    centerX = centerX + dx
    centerY = centerY + dy
  }

  def immutable: ImmutableRect = new ImmutableRect(centerX, centerY, width, height)
}