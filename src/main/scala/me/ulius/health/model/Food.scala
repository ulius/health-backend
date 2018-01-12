package me.ulius.health.model

import slick.jdbc.PostgresProfile.api._

case class Food(
  name: String,
  protein: Float,
  carbs: Float,
  fat: Float,
  servingSize: ServingSize,
  amount: Float,
  id: Long = 0L
)

final class FoodTable(tag: Tag) extends Table[Food](tag, "food") {
  implicit val servingSizeMapping =
    MappedColumnType.base[ServingSize, String](
      servingSizeType => servingSizeType.toString,
      servingSizeStr  => ServingSize.fromString(servingSizeStr)
    )

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def protein = column[Float]("protein")
  def carbs = column[Float]("carbs")
  def fat = column[Float]("fat")
  def servingSize = column[ServingSize]("serving_size")
  def amount = column[Float]("amount")

  override def * = (name, protein, carbs, fat, servingSize, amount, id).mapTo[Food]
}
