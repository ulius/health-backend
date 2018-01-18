
import javax.servlet.ServletContext

import grizzled.slf4j.Logger
import me.ulius.health.controller.{FoodController, FoodLogController}
import me.ulius.health.model.{FoodLog, FoodLogTable, FoodTable, Seed}
import slick.jdbc.meta.MTable
//import me.ulius.health.model.FoodTable
import org.scalatra.LifeCycle
import slick.jdbc.PostgresProfile.api._
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Await
import scala.concurrent.duration._

class ScalatraBootstrap extends LifeCycle {

  private val log = Logger[this.type]
  private val db = Database.forConfig("database")

  val foods = TableQuery[FoodTable]
  val foodlogs = TableQuery[FoodLogTable]
//  exec(foods.schema.create)
//  exec(foods ++= Seed.food())

  val tables = List(foods, foodlogs)
  val existing = db.run(MTable.getTables)
  val f = existing.flatMap( v => {
    val names = v.map(mt => mt.name.name)
    val createIfNotExist = tables.filter( table =>
      (!names.contains(table.baseTableRow.tableName))).map(_.schema.create)
    db.run(DBIO.sequence(createIfNotExist))
  })
  Await.result(f, Duration.Inf)


  override def init(context: ServletContext) {
    context.mount(new FoodController(db) , "/foods")
    context.mount(new FoodLogController(db) , "/foodlog")
  }

  override def destroy(context: ServletContext) {
    super.destroy(context)
  }

  def exec[T](action: DBIO[T]): T =
    Await.result(db.run(action), 2.seconds)

}