package me.ulius.health.model

case class Food(
  name: String,
  protein: Float,
  carbs: Float,
  fat: Float,
  servingSize: ServingSize,
  amount: Float
)
