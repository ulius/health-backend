
import javax.servlet.ServletContext

import grizzled.slf4j.Logger
import me.ulius.health.FoodsServlet
import me.ulius.health.model.{FoodTable, Seed}
//import me.ulius.health.model.FoodTable
import org.scalatra.LifeCycle
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration._

class ScalatraBootstrap extends LifeCycle {

  private val log = Logger[this.type]
  private val db = Database.forConfig("database")

  val foods = TableQuery[FoodTable]
  exec(foods.schema.create)
  exec(foods ++= Seed.food())

  override def init(context: ServletContext) {
    context.mount(new FoodsServlet(db) , "/*")
  }

  override def destroy(context: ServletContext) {
    super.destroy(context)
  }

  def exec[T](action: DBIO[T]): T =
    Await.result(db.run(action), 2.seconds)

}