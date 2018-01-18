package me.ulius.health.model

import java.sql.Timestamp

import slick.jdbc.PostgresProfile.api._

case class FoodLog(
  id: Long = 0L,
  foodId: Long,
  amount: BigDecimal,
  time: Timestamp
)

final class FoodLogTable(tag: Tag) extends Table[FoodLog](tag, "foodlog") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def foodId = column[Long]("food_id")
  def amount = column[BigDecimal]("amount")
  def time = column[Timestamp]("time")

  def food = foreignKey("food_fk", foodId, TableQuery[FoodTable])(_.id)

  override def * = (id, foodId, amount, time).mapTo[FoodLog]

}

