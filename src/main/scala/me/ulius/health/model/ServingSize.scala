package me.ulius.health.model

sealed trait ServingSize
case object Ounce extends ServingSize
case object Gram extends ServingSize
case object Cup extends ServingSize
case object Tablespoon extends ServingSize
case object Teaspoon extends ServingSize
