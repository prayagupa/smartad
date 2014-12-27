package models.orm

import play.api.db.slick.Config.driver.simple._

/* Brand mapping
 * @author : prayagupd
 * @on : 17 dec, 2014
 */

case class Brand(name: String, color: String)

class BrandsTable(tag: Tag) extends Table[Brand](tag, "BRAND") {

  def name = column[String]("name", O.PrimaryKey)
  def color = column[String]("color", O.NotNull)

  def * = 
	  (name, color) <> (Brand.tupled, Brand.unapply _)
}
