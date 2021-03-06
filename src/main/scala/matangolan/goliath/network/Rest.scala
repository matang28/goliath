package matangolan.goliath.network

import io.undertow.server.HttpHandler

object Rest {

  /**
    * Enumeration of RESTful http method (verb).
    * @param name the actual string value of the http method.
    */
  sealed abstract class HttpMethod(name: String){override def toString: String = s"$name"}
  case object Get extends HttpMethod("GET")
  case object Put extends HttpMethod("PUT")
  case object Post extends HttpMethod("POST")
  case object Delete extends HttpMethod("DELETE")


  sealed abstract class RestfulRequest(method: HttpMethod,
                                       internetResource: InternetResource,
                                       headers: Seq[HttpHeader],
                                       queryParams: Seq[QueryParam],
                                       body: Option[Array[Byte]]
                                      )

  sealed case class GetRequest(internetResource: InternetResource,
                               headers: Seq[HttpHeader],
                               queryParams: Seq[QueryParam]
                              ) extends RestfulRequest(Get, internetResource, headers, queryParams, None)

  sealed case class PutRequest(internetResource: InternetResource,
                               headers: Seq[HttpHeader],
                               queryParams: Seq[QueryParam],
                               body: Option[Array[Byte]]
                              ) extends RestfulRequest(Put, internetResource, headers, queryParams, body)

  sealed case class PostRequest(internetResource: InternetResource,
                               headers: Seq[HttpHeader],
                               queryParams: Seq[QueryParam],
                               body: Option[Array[Byte]]
                              ) extends RestfulRequest(Post, internetResource, headers, queryParams, body)

  sealed case class DeleteRequest(internetResource: InternetResource,
                               headers: Seq[HttpHeader],
                               queryParams: Seq[QueryParam],
                               body: Option[Array[Byte]]
                              ) extends RestfulRequest(Delete, internetResource, headers, queryParams, body)

  sealed case class RestRoute(httpMethod: HttpMethod, path: String, handler: HttpHandler)

}
