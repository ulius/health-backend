package me.ulius.health.model

import scala.io.Source

object Seed {

  def food(): Seq[Food] = {
    Source.fromResource("foods.csv").getLines.map(line => {
      val cols = line.split(",")
      Food(
        cols(0),
        BigDecimal(cols(1)),
        BigDecimal(cols(2)),
        BigDecimal(cols(3)),
        ServingSize.fromString(cols(4)),
        BigDecimal(cols(5))
      )
    }).toSeq
  }

}
