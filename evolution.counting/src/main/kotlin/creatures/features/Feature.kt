package creatures.features

interface Feature {
  fun act(number: Long): Long

  fun mutate(): Feature
  fun species(): String
}
