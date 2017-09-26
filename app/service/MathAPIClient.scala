package service

import scalaj.http.{Http, HttpResponse}

object MathAPIClient {

  case class Success(message: String)
  case class Failed(code: Int, message: String)

  def sum(url:String, numbers: String): Either[Failed, Success] = {
    Http(s"$url/sum?numbers=$numbers").asString match {
      case s: HttpResponse[String] if s.is2xx => Right(Success(s.body))
      case e: HttpResponse[String] => Left(Failed(e.code, e.body))
      case _:Any => Left(Failed(500, "An unknown error occurred"))
    }
  }

}
