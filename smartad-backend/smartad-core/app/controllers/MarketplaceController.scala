package controllers

import java.sql._
import models._

import java.net.URLEncoder
import play.api.Play.current
import play.api.mvc._
import play.api.libs.ws.WS
import akka.util.Timeout
import play.api.libs.concurrent.Execution.Implicits._
import scala.collection.mutable
import scala.concurrent.{ Await, Future }
import scala.concurrent.duration._
import play.Logger
import play.api.db._
import anorm._

import com.codahale.jerkson.Json

/*
 * @author : prayagupd
 * @on : 17 dec, 2014
 */


object MarketplaceController extends Controller {

  var connection = DB.getConnection()

  def index =  Action {
      Ok(views.html.product.index("results"))
  }

  def add () : Unit = {
      //TODO
  }

  def list = Action {
      val list = Marketplace.list
      val json = Json.generate(list) // Json.toJson (list).toString
      Ok(json).as("application/json")
  }

}
