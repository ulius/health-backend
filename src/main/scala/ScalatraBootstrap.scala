
import org.scalatra.LifeCycle
import javax.servlet.ServletContext

import scala.concurrent.ExecutionContext.Implicits.global
import slick.jdbc.PostgresProfile.api._
import grizzled.slf4j.Logger

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import me.ulius.health.FoodsServlet
import me.ulius.health.model.MessageTable

class ScalatraBootstrap extends LifeCycle {

  private val log = Logger[this.type]
  private val db = Database.forConfig("database")

  override def init(context: ServletContext) {


//    log.info(
//      exec(
//        messages.filter(_.sender === "Uli").result
//      )
//    )


    context.mount(new FoodsServlet(db) , "/*")
  }

  override def destroy(context: ServletContext) {
    super.destroy(context)
  }

  def exec[T](action: DBIO[T]): T =
    Await.result(db.run(action), 2.seconds)

}