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
      val start = java.lang.System.currentTimeMillis()
      implicit val timeout = Timeout(50000 milliseconds)
      val url = "http://localhost:8443/DasTest/esReport?clientId=2000&reportingBasis=ServiceDate&reportingTo=2013-10-31&reportingFrom=2012-11-01&comparisonFrom=2011-11-01&comparisonTo=2012-10-31&report=qualityMetric&reportingPaidThrough=2013-10-31&comparisonPaidThrough=2012-10-31&phiCSDate=11-10-2008&phiCEDate=10-31-2013&isParticipation=y&program_type=Wellness&program_type=Activity%20Tracker"
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
      Ok(views.html.index(size, json.toString))
  }

}