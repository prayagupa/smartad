package domains

import reactivemongo.bson.{BSONObjectID, BSONDocument, BSONDocumentReader}

/**
 * Created by prayagupd
 * on 5/16/15.
 */

case class Movie (movieTitle: String, similarTitle: String, relation : Double) {

}

//
//object Movie {
////  override def toString : String = {
////
////  }
//
//  implicit object MovieReader extends BSONDocumentReader[Movie] {
//    def read(doc: BSONDocument): Movie = {
//      val id = doc.getAs[BSONObjectID]("_id").get
//      val m = doc.getAs[String]("title").get
//      val s = doc.getAs[String]("similarTitle").get
//      val relation = doc.getAs[Double]("regularizedCorRelation").get
//
//      Movie(m, s, relation)
//    }
//  }
//}