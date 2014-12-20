package controllers

import java.sql._
import models._

import java.net.URLEncoder
import play.api.Play.current
import play.api.mvc._
import play.api.libs.ws.WS
import akka.util.Timeout
import play.api.libs.concurrent.Execution.Implicits._
import scala.concurrent.{ Await, Future }
import scala.concurrent.duration._
import play.Logger
import play.api.libs.json.JsValue
import play.api.db._
import anorm._


object ProductController extends Controller {

  var connection = DB.getConnection()

  def index =  Action {
      val products_ = products();
      Ok(views.html.product.index(products_)) //TODO return list
  }

  def products () : scala.collection.mutable.ArrayBuffer[Product] = {
  var listProduct = scala.collection.mutable.ArrayBuffer[Product]()
  
  try {
    //Class.forName(driver)
    //connection = DB.getConnection()
    val statement = connection.createStatement
    val res = statement.executeQuery("SELECT * FROM products")
    while(res.next){
      //create product
      val product = new Product
      product.id = res.getInt("id")
      product.brand = res.getInt("brand")
      product.name = res.getString("name")
      product.date = res.getDate("date")
      //add product to list
      listProduct += product
    }
  } catch {
    case e: Exception => e.getMessage
    println("error getting products " + e.getMessage)
  } finally  {
    //print all products
    listProduct.foreach{
      product => println(product.toString)
    }
    connection.close
  }
  listProduct
  }
}
