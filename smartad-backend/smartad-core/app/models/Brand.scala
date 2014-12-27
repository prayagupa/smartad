package models.orm

import play.api.db.slick.Config.driver.simple._

/* Brand mapping
 * @author : prayagupd
 * @on : 17 dec, 2014
 */

case class Brand(id: Option[Long], name: String, category: String)

class BrandTable(tag: Tag) extends Table[Brand](tag, "Brand") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name", O.NotNull)
  def category = column[String]("category", O.NotNull)

  def * = 
	  (id.?,name, category) <> (Brand.tupled, Brand.unapply _)
}
