package controllers

import play.api.mvc._

class IndexController extends Controller {

  def index() = Action {
    Ok("Hello, World!")
  }

}
