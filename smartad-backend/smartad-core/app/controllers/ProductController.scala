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
import play.api.libs.json._
import play.api.db._
import anorm._

/*
 * @author : prayagupd
 * @on : 17 dec, 2014
 */


object ProductController extends Controller {

  var connection = DB.getConnection()

  def index =  Action {
      Ok(views.html.product.index("results"))
  }

  def add () : Unit = {
      //TODO
  }

  def products = Action {
      implicit val productWrites = new Writes[Product] {
        def writes(user: Product) = JsObject(Seq(
          "id" -> JsNumber(user.id),
          "brand" -> JsNumber(user.brand),
          "name" -> JsString(user.name),
          "created" -> Json.toJson(user.date)
        ))
      }

      val im = scala.collection.immutable.Map("products" -> "jackets")

      val productsBuffer = getProducts()
      val list = productsBuffer.toList
      val productsJson = Json.toJson (list).toString
      Ok(productsJson)
  }

  def getProducts () : scala.collection.mutable.ListBuffer[Product] = {
  var listProduct = scala.collection.mutable.ListBuffer[Product]()
  
  try {
    val statement = connection.createStatement
    val res = statement.executeQuery("SELECT * FROM products")
    while(res.next){
      val productId = res.getInt("id")
      val productBrand = res.getInt("brand")
      val productName = res.getString("name")
      val productDate = res.getDate("date")

      val product = new Product(productId, productBrand, productName, productDate)
      listProduct += product
    }
  } catch {
    case e: Exception => e.getMessage
    println("error getting products " + e.getMessage)
  } finally  {
    listProduct.foreach{
      product => Logger.info(product.toString)
    }
    connection.close
  }
  listProduct
  }
}
