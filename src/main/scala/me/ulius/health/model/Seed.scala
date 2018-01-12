package me.ulius.health.model

import scala.io.Source

object Seed {

  def food(): Seq[Food] = {
    Source.fromResource("foods.csv").getLines.map(line => {
      val cols = line.split(",")
      Food(
        cols(0),
        cols(1).toFloat,
        cols(2).toFloat,
        cols(3).toFloat,
        ServingSize.fromString(cols(4)),
        cols(5).toFloat
      )
    }).toSeq
  }

}
