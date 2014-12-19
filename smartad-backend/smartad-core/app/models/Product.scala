package models

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

