package me.ulius.health.model

import slick.jdbc.PostgresProfile.api._

case class Food(
  name: String,
  protein: BigDecimal,
  carbs: BigDecimal,
  fat: BigDecimal,
  servingSize: ServingSize,
  amount: BigDecimal,
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
  def protein = column[BigDecimal]("protein")
  def carbs = column[BigDecimal]("carbs")
  def fat = column[BigDecimal]("fat")
  def servingSize = column[ServingSize]("serving_size")
  def amount = column[BigDecimal]("amount")

  override def * = (name, protein, carbs, fat, servingSize, amount, id).mapTo[Food]
}
