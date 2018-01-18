package me.ulius.health.model

import org.json4s.CustomSerializer
import org.json4s.JsonAST.{JField, JObject, JString}

sealed trait ServingSize

object ServingSize {
  case object Cup extends ServingSize { override def toString = "cup" }
  case object Gram extends ServingSize { override def toString = "gram" }
  case object Ounce extends ServingSize { override def toString = "ounce" }
  case object Tablespoon extends ServingSize { override def toString = "tablespoon" }
  case object Teaspoon extends ServingSize { override def toString = "teaspoon" }
  case object Large extends ServingSize { override def toString = "large" }
  case object Medium extends ServingSize { override def toString = "medium" }
  case object Small extends ServingSize { override def toString = "small" }

  def fromString(servingSize: String): ServingSize = servingSize match {
    case "cup" => Cup
    case "gram" => Gram
    case "ounce" => Ounce
    case "tablespoon" => Tablespoon
    case "teaspoon" => Teaspoon
    case "large" => Large
    case "medium" => Medium
    case "small" => Small
    case _ => throw new RuntimeException(s"Unknown servingSize: $servingSize")

  }
}

class ServingSizeSerializer extends CustomSerializer[ServingSize](format => (
  {
    case JString(servingSize) => ServingSize.fromString(servingSize)
  },
  {
    case x: ServingSize => JString(x.toString)
  }
))

