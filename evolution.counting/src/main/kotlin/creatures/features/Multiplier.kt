package creatures.features

import java.util.*

class Multiplier(val multiple: Long): Feature {
  override fun act(number: Long): Long = number * multiple

  override fun mutate(): Feature = Multiplier(multiple)
  override fun species(): String = "M"

  override fun toString(): String = "Multiplier $multiple"

  companion object {
    val rand = Random()
    fun random() = Multiplier(rand.nextInt(3).toLong() - 2)
  }
}
