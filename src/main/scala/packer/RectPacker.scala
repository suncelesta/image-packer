package packer

import scala.util.Random

class RectPacker(closenessStep: Double = 0.1, radialStep: Double = 5) {
  def pack(area: Rect, rects: Seq[Rect], maxDistanceFactor: Double): (Seq[Rect], Int) = {
    rects.foldLeft((Seq[Rect](), 0)) { case ((alreadyPlaced, unplacedCount), rectToPlace) =>

      placeRect(rectToPlace, alreadyPlaced, area, maxDistanceFactor) match {
        case Some(placedRect) => (alreadyPlaced :+ placedRect, unplacedCount)
        case None => (alreadyPlaced, unplacedCount + 1)
      }
    }
  }

  def placeRect(rect: Rect, placedRects: Seq[Rect], area: Rect, distanceSizeRatio: Double): Option[Rect] = {
    if ((rect within area) && !(rect hasCollisionsWith placedRects)) Some(rect)
    else {

      val maxDistance = distanceSizeRatio * rect.diagonalSize
      val rotationOffset = new Random().nextDouble() * 360
      val newRect = Rect.mutable(rect)
      
      for (
        radius <- 0.0 to maxDistance by rect.diagonalSize * closenessStep;
        angle <- 0.0 to 360 by radialStep
      ) {
        val dx = Math.cos(rotationOffset + angle * (Math.PI/180)) * radius
        val dy = Math.sin(rotationOffset + angle * (Math.PI/180)) * radius
        newRect.moveBy(dx, dy)

        if ((newRect within area) && !(newRect hasCollisionsWith placedRects))
          return Some(newRect.immutable)
      }

      None
    }
  }
}
