
import org.scalatra.LifeCycle
import javax.servlet.ServletContext

import me.ulius.health.FoodsServlet

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new FoodsServlet , "/*")
  }
}