import java.sql._

// Entity
class Product {
  var id: Long = _
  var brand: Long = _
  var name: String = _
  var date: Date = _
  override def toString = {
    "id: " + id + "* name: " + name + " isbn: " + brand + " date: " + date
  }
}

object Connect extends App{

  val url = "jdbc:mysql://localhost:3306/smartad"
  val driver = "com.mysql.jdbc.Driver"
  val userName = "root"
  val userPassword = "mysql55"

  var connection:Connection = _
  var listProduct = scala.collection.mutable.ArrayBuffer[Product]()

  try {
    Class.forName(driver)
    connection = DriverManager.getConnection(url, userName, userPassword)
    val statement = connection.createStatement
    val res = statement.executeQuery("SELECT * FROM products")
    while(res.next){
      //create book
      val product = new Product
      product.id = res.getInt("id")
      product.brand = res.getInt("brand")
      product.name = res.getString("name")
      product.date = res.getDate("date")
      //add book to list
      listProduct += product
    }
  }catch {
    case e: Exception => e.getMessage
  } finally  {
    //print all book
    listProduct.foreach{
      product => println(product.toString)
    }
    connection.close
  }
}
