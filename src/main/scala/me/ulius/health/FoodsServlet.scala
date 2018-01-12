package me.ulius.health

import me.ulius.health.model.ServingSize.{Ounce, Teaspoon}
import me.ulius.health.model.{Food, FoodTable, MessageTable}
import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import org.slf4j.{Logger, LoggerFactory}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.duration._
import scala.concurrent.Await

class FoodsServlet(db: Database)
  extends ScalatraServlet
    with JacksonJsonSupport with CorsSupport with FutureSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats
  private val logger =  LoggerFactory.getLogger(getClass)
  implicit val executor = scala.concurrent.ExecutionContext.Implicits.global

  private val foods = TableQuery[FoodTable]

  before() {
    contentType = formats("json")
    response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"))
    response.setHeader("Access-Control-Allow-Origin", "*")
  }

  options("/*"){
    response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"))
    response.setHeader("Access-Control-Allow-Origin", "*")
  }

  get("/foods") {
    val result = Await.result(
      db.run(foods.result), 2.seconds
    )
    result
  }

}

