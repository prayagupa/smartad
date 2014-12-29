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
import play.api.libs.json._
import play.api.db._
import anorm._
import play.api.libs.functional.syntax._

//import com.codahale.jerkson.Json

/*
 * @author : prayagupd
 * @on : 17 dec, 2014
 */

object ProductController extends Controller {

  val brandsQuery = TableQuery[BrandTable]
  val productsQuery = TableQuery[ProductTable]
  val marketplacesQuery = TableQuery[MarketplaceTable]

//  implicit val reads = (
//    (__ \ 'name).read[String],
//    (__ \ 'brand).read[Long] and
//    (__ \ 'marketplace).read[Long]
//   ) tupled

  implicit val reads_ = Json.reads[Product]

  def add  = DBAction(parse.json) { implicit request =>
   Logger.info("adding")
   request.body.validate(reads_).map{
        p => 
              productsQuery.insert(p)
              Ok(Json.obj("status" ->"OK", 
                          "message" -> ("Product "+p.name+", saved successfully.") ))
      }.recoverTotal{
        e =>
                Logger.info("adding failed")
		BadRequest(Json.obj("status" ->"KO", "message" -> JsError.toFlatJson(e)))
      }
  }

  def products = DBAction { implicit rs =>
      val list = productsQuery.list
      val implicitInnerJoin = for {
        product <- productsQuery
	marketplace <- marketplacesQuery if product.marketplace === marketplace.id
        brand   <- brandsQuery if product.brand === brand.id
      } yield ("id" -> product.id, 
	       "name" -> product.name, 
	       "brand"->brand.name, 
	       "price" -> product.price, 
	       "category" -> brand.category, 
	       "marketplace" -> marketplace.name, 
	       "releasedOn" -> product.created)

      Logger.info("implicitInnerJoin => " + implicitInnerJoin.selectStatement) 
      Logger.info("implicitInnerJoin => " + implicitInnerJoin) 
      var response = Map("products" -> implicitInnerJoin.list)
      val productsJson = com.codahale.jerkson.Json.generate(response)
      Ok(productsJson)
  }
}
