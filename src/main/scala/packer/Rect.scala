package packer

import org.scalajs.dom

object Rect {
  def apply(centerX: Double, centerY: Double, width: Double, height: Double) =
    new ImmutableRect(centerX, centerY, width, height)

  def mutable(rect: Rect) =
    new MutableRect(rect.centerX, rect.centerY, rect.width, rect.height)
}

trait Rect {
  def centerX: Double
  def centerY: Double
  def width: Double
  def height: Double

  def left = centerX - width / 2

  def right = centerX + width / 2

  def top = centerY - height / 2

  def bottom = centerY + height / 2

  def diagonalSize = Math.sqrt(width * width + height * height)

  def within(other: Rect): Boolean = {
    left > other.left &&
      right < other.right &&
      top > other.top &&
      bottom < other.bottom
  }

  def hasCollisionsWith(others: Seq[Rect]): Boolean = {
    others exists { other =>
      !(left > other.right || right < other.left) &&
        !(top > other.bottom || bottom < other.top)
    }
  }

}
