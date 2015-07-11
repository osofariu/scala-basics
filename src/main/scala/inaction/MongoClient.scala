package inaction

import com.mongodb.MongoClient

class MongoClientWrapper(val host: String, val port: Int) {
  require(host != null, "You must provide a valid host name")
  private val mongo = new MongoClient(host, port)

  def this() = this("127.0.0.1", 27017)
}

