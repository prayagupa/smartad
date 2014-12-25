package models

import anorm._
import anorm.SqlParser._
import play.api.db.DB
import play.api.Play.current

case class Marketplace(id: Pk[Int], name: String, email: String, contact: Int)

object Marketplace {

  def save(user: Marketplace) {
    DB.withConnection { implicit connection =>
      SQL(""" 
            INSERT INTO Marketplace(name, email, contact) 
            VALUES({name}, {email}, {contact})
      """).on(
          'name -> user.name,
          'email -> user.email,
          'contact -> user.contact
      ).executeUpdate
    }
  }

  private val userParser: RowParser[Marketplace] = {
      get[Pk[Int]]("id") ~
      get[String]("name") ~
      get[String]("email") ~
      get[Int]("contact") map {
      case id ~ name ~ email ~ contact => Marketplace(id, name, email, contact)
    }
  }
  
  def list: List[Marketplace] = {
    DB.withConnection { implicit connection =>
      SQL("SELECT * from Marketplace").as(userParser *)
    }
  }
}
