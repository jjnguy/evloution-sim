package creatures.features

class Dividor: Feature {
  override fun act(number: Long): Long = number / 2

  override fun mutate(): Feature = Dividor()

  override fun species(): String = "D"

  companion object {
    fun random(): Feature = Dividor()
  }
}