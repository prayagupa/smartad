package domains

/**
 * Created by prayagupd
 * on 5/17/15.
 */

trait DatabaseConnector extends Serializable {
  def connect() : Unit
}
