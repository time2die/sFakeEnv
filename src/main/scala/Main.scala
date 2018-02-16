//import scalaj.http.Http
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import better.files._

import scala.io.StdIn

object WebServer {
  def main(args: Array[String]) {

    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher

    def processFilePart(key: String): String = {
      val f = File(s"projects/$key")
      println(f.exists)
      if (!f.exists) {
        f.createFile()
        f.write("123123")
      }
      f.contentAsString
    }

    val route =
    //      projects/internal/sharings
      pathPrefix("projects") {
        pathPrefix("internal") {
          pathPrefix("sharings") {
            pathEnd {
              get {
                parameters('key.as[String]) { key =>
                  complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, processFilePart(key)))
                }
              }
            }
          }
        }
      }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
