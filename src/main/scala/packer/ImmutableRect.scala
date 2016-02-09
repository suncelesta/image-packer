package packer

class ImmutableRect(val centerX: Double,
                    val centerY: Double,
                    val width: Double,
                    val height: Double) extends Rect {

  override val left = super.left

  override val right = super.right

  override val top = super.top

  override val bottom = super.bottom

  override val diagonalSize = super.diagonalSize
}
