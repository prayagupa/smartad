package models

import java.sql._
import play.api.libs.json._

/*
 * @author : prayagupd
 * @on : 17 dec, 2014
 */

case class Product (
  var id: Long ,
  var brand: Long ,
  var name: String,
  var date: Date ,
  var images : String,
  var price : Double,
  var description  : String,
  var marketplace : String,
  var rating : Double,
  var reviews : String,
  var releaseDate : Date

) {
def this(id: Int, brand : Long, name : String, date : Date) = this(id, 
	                                                             brand, 
								     name,
								     date,
								     "",
								     0.0,
								     "na",
								     "na",
								     0.0,
								     "na",
								     new Date(new java.util.Date().getTime()))  
//  override def toString = {
//    " id   : " + id + 
//    " name : " + name + 
//    " brand: " + brand + 
//    " date : " + date
//  }

}
