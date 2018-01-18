package me.ulius.health.controller

import me.ulius.health.model._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra._
import org.scalatra.json._
import org.slf4j.LoggerFactory
import slick.jdbc.PostgresProfile.api._

class FoodController(db: Database)
  extends ScalatraServlet
    with JacksonJsonSupport with CorsSupport with FutureSupport {

  private val log =  LoggerFactory.getLogger(getClass)
  implicit val executor = scala.concurrent.ExecutionContext.Implicits.global
  protected implicit lazy val jsonFormats: Formats =
    DefaultFormats.withBigDecimal + new ServingSizeSerializer

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

  get("/") {
    db.run(foods.result)
  }

  post("/") {
    log.info("hey")
    val food = parsedBody.extract[Food]
    db.run(foods += food).onComplete(dbio => {
      if (dbio.isSuccess) Ok()
      if (dbio.isFailure) InternalServerError()
    })
  }

}

