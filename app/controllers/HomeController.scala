package controllers

import javax.inject._

import play.api.mvc._
import service.MathAPIClient

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def sum(maybeNumbers: Option[String] = None) = Action {

    maybeNumbers match {
      case Some(numbers) =>
        MathAPIClient.sum("http://localhost:9001", numbers) match {
          case Left(e) if e.code == 500 => InternalServerError(e.message)
          case Left(e) => BadRequest(e.message)
          case Right(s) => Ok(s.message)
        }
      case None => BadRequest("Bad request. Example Usage http://host/sum?numbers=1,2,1,3")
    }
  }
}
