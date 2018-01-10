package me.ulius.health.model

import slick.jdbc.PostgresProfile.api._


final case class Message(
  sender: String,
  content: String,
  id: Long = 0L
)

final class MessageTable(tag: Tag) extends Table[Message](tag, "message") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def sender = column[String]("sender")
  def content = column[String]("content")

  def * = (sender, content, id).mapTo[Message]

}