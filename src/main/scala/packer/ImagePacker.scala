package packer

import org.scalajs.dom
import org.scalajs.dom.html

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

@JSExport
object ImagePacker {
  val rectPacker = new RectPacker()

  @JSExport
  def pack(canvasId: String,
           imageId: String,
           count: Int,
           maxDistanceFactor: Double,
           centerX: Double,
           centerY: Double,
           width: Double,
           height: Double): Unit = {

    val weights = for (i <- 1 to count) yield Math.random()

    pack(canvasId, imageId, weights.distinct.sorted.reverse, maxDistanceFactor, centerX, centerY, width, height)
  }

  @JSExport
  def pack(canvasId: String,
           imageId: String,
           weights: Seq[Double],
           maxDistanceFactor: Double,
           centerX: Double,
           centerY: Double,
           width: Double,
           height: Double): Unit = {

    val imageElement = dom.document.getElementById(imageId).asInstanceOf[html.Image]

    val sizes = weights map { weight => (imageElement.width * weight, imageElement.height * weight)}
    val area = Rect(centerX, centerY, width, height)
    val packedRects = pack(sizes, area, maxDistanceFactor)

    val canvas = dom.document.getElementById(canvasId).asInstanceOf[html.Canvas]
    val context = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

    for (rect <- packedRects) {
      context.drawImage(imageElement, rect.left, rect.top, rect.width, rect.height)
    }

  }

  private def pack(sizes: Seq[(Double, Double)], area: Rect, maxDistanceFactor: Double): Seq[Rect] = {
    val rects = sizes map { case (width, height) => Rect(area.centerX, area.centerY, width, height) }

    val (packed, unpackedCount) = rectPacker.pack(area, rects, maxDistanceFactor)

    println(s"$unpackedCount images were not packed")

    packed
  }
}
