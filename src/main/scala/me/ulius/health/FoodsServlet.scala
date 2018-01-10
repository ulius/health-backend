package me.ulius.health

import me.ulius.health.model.{Food, MessageTable, Ounce, Teaspoon}
import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import org.slf4j.{Logger, LoggerFactory}
import slick.jdbc.PostgresProfile.api._

class FoodsServlet(db: Database)
  extends ScalatraServlet
    with JacksonJsonSupport with CorsSupport with FutureSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats
  private val logger =  LoggerFactory.getLogger(getClass)
  implicit val executor = scala.concurrent.ExecutionContext.Implicits.global

  private val messages = TableQuery[MessageTable]

  before() {
    contentType = formats("json")
    response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"))
    response.setHeader("Access-Control-Allow-Origin", "*")
  }

  options("/*"){
    response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"))
    response.setHeader("Access-Control-Allow-Origin", "*")
  }

  get("/foods/:food") {
    db.run(
      messages.filter(_.sender === params("food")).result
    )
  }

  private val foods = Seq(
    Food("Chicken breast (baked)", 5.61f, 0f, 1.47f, Ounce, 1f),
    Food("Carlson's Fish Oil", 0f, 0f, 4.5f, Teaspoon, 1f),

  )
}

