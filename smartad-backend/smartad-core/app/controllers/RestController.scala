package controllers

import java.net.URLEncoder

import play.api.mvc._
import play.api.libs.concurrent.Akka
import play.api.Play.current
import java.util.Date

// needed by Akka.future
import play.api.libs.iteratee._
import play.api.libs.concurrent.Execution.Implicits.defaultContext

/**
 * https://www.playframework.com/documentation/2.3.3/ScalaWebSockets
 */

object RestController extends Controller {

  /**
   * notification engine using WebSocket
   */

  //def notify = WebSocket.using[String] {
  //  val out = Enumerator.imperative[String]()
  //  val in = Iteratee.foreach[String] {
  //    msg =>
  //      out.push(msg)
  //  }
  //  (in, out)
  //}

  def notification =  WebSocket.using[String] { request =>
  //Concurernt.broadcast returns (Enumerator, Concurrent.Channel)
    val (out,channel) = Concurrent.broadcast[String]

    //log the message to stdout and send response back to client
    val in = Iteratee.foreach[String] { msg =>
        println("message from client : " + msg)
        //the channel will push to the Enumerator
        channel push("Response from Notification engine at " + new Date() + " : " + msg)
    }
    (in,out)
  }
}
