package controllers

import play.api.mvc._

class IndexController extends Controller {

  def index() = Action {
    val user = Map("username" -> "shekhargulati")
    val posts = List(
      Map("author" -> "Shekhar",
        "body" -> "Getting started with Play"
      ),
      Map("author" -> "Rahul",
        "body" -> "Getting started with Docker"
      )
    )
    Ok(views.html.index("Welcome to Blogy", user, posts))
  }

}
