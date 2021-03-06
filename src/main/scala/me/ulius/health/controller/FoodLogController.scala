package me.ulius.health.controller

import java.text.SimpleDateFormat

import me.ulius.health.model._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra._
import org.scalatra.json._
import org.slf4j.LoggerFactory
import slick.jdbc.PostgresProfile.api._

class FoodLogController(db: Database)
  extends ScalatraServlet
    with JacksonJsonSupport with CorsSupport with FutureSupport {

  private val log =  LoggerFactory.getLogger(getClass)
  implicit val executor = scala.concurrent.ExecutionContext.Implicits.global

  protected implicit lazy val jsonFormats: Formats =
    DefaultFormats.withBigDecimal + new ServingSizeSerializer

  //  implicit val formats = new DefaultFormats {
  //    override def dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
  //  }

  private val foodTable = TableQuery[FoodTable]
  private val foodLogTable = TableQuery[FoodLogTable]

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
    val blah = for {
      fl <- foodLogTable
      f <- foodTable if fl.foodId === f.id
    } yield (f, fl)

    db.run(blah.result)
  }

  post("/") {
    log.info("hey")
    val foodLog = parsedBody.extract[FoodLog]
    println(foodLog)
    db.run(foodLogTable += foodLog).onComplete(dbio => {
      if (dbio.isSuccess) Ok()
      if (dbio.isFailure) InternalServerError()
    })
  }

}

