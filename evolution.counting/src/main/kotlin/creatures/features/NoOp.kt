package creatures.features

class NoOp: Feature {
  override fun act(number: Long): Long = number

  override fun mutate(): Feature = NoOp()
  override fun species(): String = "N"


  override fun toString(): String = "NoOp"

  companion object {
    fun random() = NoOp()
  }
}
