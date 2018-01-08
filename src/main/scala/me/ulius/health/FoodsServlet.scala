package me.ulius.health

import me.ulius.health.model.{Food, Ounce, Teaspoon}
import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import org.slf4j.{Logger, LoggerFactory}

class FoodsServlet
  extends ScalatraServlet
    with JacksonJsonSupport
    with CorsSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats
  private val logger =  LoggerFactory.getLogger(getClass)

  before() {
    contentType = formats("json")
  }

  options("/*"){
    response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"))
    response.setHeader("Access-Control-Allow-Origin", "*")
  }

  get("/") {
    response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"))
    response.setHeader("Access-Control-Allow-Origin", "*")
    foods
  }

  private val foods = Seq(
    Food("Chicken breast (baked)", 5.61f, 0f, 1.47f, Ounce, 1f),
    Food("Carlson's Fish Oil", 0f, 0f, 4.5f, Teaspoon, 1f),

  )
}

