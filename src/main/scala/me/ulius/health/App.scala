package me.ulius.health

//import me.ulius.health.model.{Food, FoodTable, Message, MessageTable}

import scala.concurrent.ExecutionContext.Implicits.global
import slick.jdbc.PostgresProfile.api._
import grizzled.slf4j.Logger

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

object App {
  private val log = Logger[this.type]
//  val db = Database.forConfig("database")

  def main(args: Array[String]): Unit = {
    JettyLauncher.launch()



//    val messages = TableQuery[MessageTable]

//    val action: DBIO[Unit] = messages.schema.create
//    val future: Future[Unit] = db.run(action)
//    Await.result(future, 2.seconds)

//    val insert: DBIO[Option[Int]] = messages ++= data
//    val result: Future[Option[Int]] = db.run(insert)
//    val rowCount = Await.result(result, 2.seconds)

//    val halSays = messages.filter(_.sender === "Uli")

//    log.info(
//      exec(messages.result)
//    )

//    log.info(
//      exec(
//        messages.filter(_.sender === "Uli").result
//      )
//    )






  }

//  def populate[T](table: TableQuery[T]): DBIOAction[Option[Int], NoStream,Effect.All] = {
//    for {
//      //Drop table if it already exists, then create the table:
//      _ <- messages.schema.drop.asTry andThen messages.schema.create
//      // Add some data:
//      count <- messages ++= testData
//    } yield count
//  }

//  def exec[T](action: DBIO[T]): T =
//    Await.result(db.run(action), 2.seconds)

}
