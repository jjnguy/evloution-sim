package creaturebuilder

import creatures.features.*

class FeatureBuilders {
  companion object {
    val all = listOf<() -> Feature>(Incrementor.Companion::random, Incrementor.Companion::random, Multiplier.Companion::random,NoOp.Companion::random, NoOp.Companion::random)
  }
}

