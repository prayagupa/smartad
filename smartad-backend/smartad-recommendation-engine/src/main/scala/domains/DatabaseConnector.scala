package domains

import reactivemongo.bson.BSONDocument

/**
 * Created by prayagupd
 * on 5/17/15.
 */

trait DatabaseConnector extends Serializable {
  def connect() : Unit
  def insert(movie : BSONDocument ): Unit
  def list(name : String) : List[Movie]
}
