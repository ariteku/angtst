package controllers

import play.api.mvc._
import models._
import play.api.libs.json._
import collection.mutable.MutableList

object Users extends Controller {

  var users = MutableList(
    User(Some(1), "user1@doe.com", "user1", "Doe1"),
    User(Some(2), "user2@doe.com", "user2", "Doe2"),
    User(Some(3), "user3@doe.com", "user3", "Doe3"),
    User(Some(4), "user4@doe.com", "user4", "Doe4"),
    User(Some(5), "user5@doe.com", "user5", "Doe5"),
    User(Some(6), "user6@doe.com", "user6", "Doe6")
  )

  def index() = Action { implicit request =>
    Ok(Json.toJson(users))
  }

  def edit(id: Long) = Action { implicit request =>
    users.find(_.id == Some(id)).map { user =>
      Ok(Json.toJson(user))
    } getOrElse {
      NotFound(s"No User with id: $id")
    }
  }

  def save(id: Long) = Action(parse.json) { implicit request =>
   request.body.validate[User].fold(
      invalid = e => BadRequest(JsError.toFlatJson(e)),
      valid = { user =>
        users.find(_.id == Some(id)).map { storedUser =>
          // Update user with same id
          users.update(users.indexOf(storedUser), user)
          Ok(Json.toJson(user))

        } getOrElse {

          // Insert new user
          val lastId = users.maxBy(_.id).id.get
          val userWithNewId = user.copy(id = Some(lastId + 1))
          users += userWithNewId
          Created(Json.toJson(userWithNewId))
        }
      }
    )
  }

  def add() = Action { implicit request =>
    val lastId = users.maxBy(_.id).id.getOrElse(1L)
    users += User(Some(lastId + 1),"none@none.com","none","none")
    Ok("")
  }

  def remove(id: Long) = Action { implicit request =>
    println(s"request: remove id=$id")
    users = users filter {
      case User(Some(x),_,_,_) if x != id => true
      case _ => false
    }
    Ok("")
  }

}
