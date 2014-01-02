package controllers

import java.net.URLEncoder
import play.api.mvc._
import play.api.libs.ws.WS
import akka.util.Timeout
import play.api.libs.concurrent.Execution.Implicits._
import scala.concurrent.{ Await, Future }
import scala.concurrent.duration._
import play.Logger
import play.api.libs.json.JsValue


object Application extends Controller {

  def index =  Action {

      Ok(views.html.index("Request Url"))

  }

  def getJson = Action { implicit request =>
    val url = request.queryString.get("url").flatMap(_.headOption).getOrElse("")

    val start = java.lang.System.currentTimeMillis()
    implicit val timeout = Timeout(70000 milliseconds)

    Logger.info("url : {}",url)
    val responseFuture = WS.url(url).get()
    val future = responseFuture map {
      response => (response.json \\ "reporting")
    }
    val result = Await.result(future, timeout.duration).asInstanceOf[List[play.api.libs.json.JsObject]]

    val size = result.size
    var jsonVal: JsValue = null
    var json: String = ""
    val json_ = result.foreach(jsonObject =>
      jsonObject.values.foreach(jsValue => {
        Logger.info("jsvalue : "+jsValue)
        jsonVal = jsValue
        json = jsValue.toString()
      }
      )
    )
    val end = java.lang.System.currentTimeMillis()
    Logger.info("size : {}", size.toString)
    Logger.info("Time taken : {} seconds", ((end - start)/1000).toString)
    Ok(views.html.json(jsonVal))
  }

}