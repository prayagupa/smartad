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

def fetchLatitudeAndLongitude(address: String): Option[(Double, Double)] = {
    implicit val timeout = Timeout(50000 milliseconds)
    
    // Encoded the address in order to remove the spaces from the address (spaces will be replaced by '+')
    //@purpose There should be no spaces in the parameter values for a GET request
    val addressEncoded = URLEncoder.encode(address, "UTF-8");
    val jsonContainingLatitudeAndLongitude = WS.url("http://maps.googleapis.com/maps/api/geocode/json?address=" + addressEncoded + "&sensor=true").get()
   
    val future = jsonContainingLatitudeAndLongitude map {
      response => (response.json \\ "location")
    }

    // Wait until the future completes (Specified the timeout above)

    val result = Await.result(future, timeout.duration).asInstanceOf[List[play.api.libs.json.JsObject]]
    
    //Fetch the values for Latitude & Longitude from the result of future
    val latitude = (result(0) \\ "lat")(0).toString.toDouble
    val longitude = (result(0) \\ "lng")(0).toString.toDouble
    Option(latitude, longitude)
  }

  def getJson = Action { implicit request =>
    val url = request.queryString.get("url").flatMap(_.headOption).getOrElse("")

    val start = java.lang.System.currentTimeMillis()
    implicit val timeout = Timeout(70000 milliseconds)
   
    Logger.info("url : {}",url)
    val v = fetchLatitudeAndLongitude("Kathmandu")
    println(v)
    Ok("good")
  }


  def getJson_ = Action { implicit request =>
    val url = request.queryString.get("url").flatMap(_.headOption).getOrElse("")

    val start = java.lang.System.currentTimeMillis()
    implicit val timeout = Timeout(70000 milliseconds)

    Logger.info("url : {}",url)
    val responseFuture = WS.url(url).get()
    Logger.info("responseFuture : {}", responseFuture)
    val future = responseFuture map {
      response => (response.json)
    }
    Logger.info("future : {}", future)
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