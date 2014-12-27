package models.orm

import java.sql.{Date => SqlDate}
import java.util.Date
import play.api.db.slick.Config.driver.simple._
import java.sql.Timestamp

/* Product
 * @author : prayagupd
 * @on : 17 dec, 2014
 */

case class Product (
  id: Option[Long],
  name: String,
  brand: Long ,
//  var images : String,
  var price : Double,
  created: Option[Date] = None
//  var description  : String,
//  var marketplace : String,
//  var rating : Double,
//  var reviews : String,
//  var releaseDate : Date

)

class ProductTable(tag: Tag) extends Table[Product](tag, "Product") {
  implicit val dateColumnType = MappedColumnType.base[Date, Long](d => d.getTime, d => new Date(d))

  def id    = column[Long]     ("id",    O.PrimaryKey, O.AutoInc)
  def name  = column[String]   ("name",  O.NotNull)
  def brand = column[Long]     ("brand", O.NotNull)
  def price = column[Double]   ("price", O.NotNull)
  def created  = column[Date]  ("created",  O.Nullable)
  def * = 
        (id.?, name, brand, price, created.?) <> (Product.tupled, Product.unapply _)
}
