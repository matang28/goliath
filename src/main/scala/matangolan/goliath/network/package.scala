package matangolan.goliath

import java.net.URLEncoder
import java.nio.charset.Charset

package object network {

  val encoding: String = Charset.forName("UTF-8").name()
  def urlEncode(str: String) = {
    URLEncoder.encode(str, encoding)
  }

  /**
    * Enumeration of supported network protocols.
    * @param name the plain text name of the protocol.
    */
  sealed abstract class NetworkProtocol(name: String){override def toString: String = s"$name"}
  case object Http extends NetworkProtocol("http")
  case object Https extends NetworkProtocol("https")

  /**
    * Abstraction of internet address which contains an host address, port and protocol.
    * @param protocol the protocol used to
    * @param host the IP address or hostname of the machine.
    * @param port the port to be used to communicate with the machine.
    */
  sealed case class InternetAddress(protocol: NetworkProtocol, host: String, port: Int) {
    override def toString: String = s"$protocol://$host:$port"
  }

  /**
    * Abstraction of internet resource
    * @param internetAddress the address of the remote machine.
    * @param resource the resource path, without the "/" prefix.
    */
  sealed case class InternetResource(internetAddress: InternetAddress, resource: String){
    override def toString: String = s"${super.toString}/$resource"
  }


  sealed abstract class KeyValuePair[K, V](key: K, value: V)
  sealed case class QueryParam(key: String, value: String) extends KeyValuePair[String, String](key, value) {
    override def toString: String = s"${urlEncode(key)}=${urlEncode(value)}"
  }
  sealed case class HttpHeader(name: String, value: String) extends KeyValuePair[String, String](name, value){
    override def toString: String = s"$name: $value"
  }

}
