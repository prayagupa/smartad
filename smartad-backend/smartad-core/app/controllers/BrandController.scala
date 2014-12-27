package controllers

import java.sql._
import models.orm._
import play.api.db.slick._
import play.api.db.slick.Config.driver.simple._

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


object BrandController extends Controller {

  val Brands = TableQuery[BrandsTable]

  def index =  Action {
      Ok(views.html.product.index("results"))
  }

  def add () : Unit = {
      //TODO
  }

  def list = DBAction { implicit rs =>
      val list = Brands.list
      val json = Json.generate(list)
      Ok(json).as("application/json")
  }

}
