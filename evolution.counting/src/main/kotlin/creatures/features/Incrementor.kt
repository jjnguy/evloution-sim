package creatures.features

import java.util.*

class Incrementor(val amnt: Long): Feature {

  val maxInc = 100L

  override fun act(number: Long): Long = number + amnt

  override fun mutate(): Feature = Incrementor(Math.min(amnt + rand.nextInt(2) - 1, maxInc))

  override fun species(): String = "I"

  override fun toString(): String = "Incrementor $amnt"

  companion object {
    val rand = Random()
    fun random() = Incrementor(rand.nextInt(10).toLong() - 5)
  }
}