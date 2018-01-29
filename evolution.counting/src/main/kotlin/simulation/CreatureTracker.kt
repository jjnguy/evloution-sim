package simulation

import creatures.Counter

class CreatureTracker(val seed: Long, val creature: Counter) {
  val steps = mutableListOf<Long>(seed)
  fun act() {
    steps.add(creature.count(steps.last()))
  }

  fun result(): Long = steps.last()
}
