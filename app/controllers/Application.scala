package controllers

import play.api._
import play.api.mvc._
import play.api.libs.ws.WS
import play.api.libs.concurrent.Execution.Implicits._

object Application extends Controller {

  def index = Action {
      val url = "http://localhost:8443/DasTest/esReport?clientId=2000&reportingBasis=ServiceDate&reportingTo=2013-10-31&reportingFrom=2012-11-01&comparisonFrom=2011-11-01&comparisonTo=2012-10-31&report=qualityMetric&reportingPaidThrough=2013-10-31&comparisonPaidThrough=2012-10-31&phiCSDate=11-10-2008&phiCEDate=10-31-2013&isParticipation=y&program_type=Wellness&program_type=ActivityTracker"
      val responseFuture = WS.url(url).get().map { response =>
        Ok("Feed title: " + (response.json \ "title").as[String])
        println("Response : " + response.json.as[String])
      }
      val future = responseFuture.value
      Ok(views.html.index(future.toString))
  }

}