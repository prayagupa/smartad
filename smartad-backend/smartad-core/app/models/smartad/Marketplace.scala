package models.orm

import play.api.db.slick.Config.driver.simple._

case class Marketplace(id: Option[Long], name: String, email: String, contact: String)

class MarketplaceTable(tag: Tag) extends Table[Marketplace](tag, "Marketplace") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name", O.NotNull)
  def email = column[String]("email", O.NotNull)
  def contact = column[String]("contact", O.NotNull)

  def * = 
	  (id.?,name, email, contact) <> (Marketplace.tupled, Marketplace.unapply _)
}
