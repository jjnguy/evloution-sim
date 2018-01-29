package simulation

import creaturebuilder.RandomCreature
import java.awt.event.ActionListener

class Simulation {
  val creaturRandomizer = RandomCreature()
  var genCount = 0

  val creatureCount = 1000

  val creatureSnapshots = mutableListOf<SimulationFrame>()

  var currentCreatures = (1..creatureCount).map { creaturRandomizer.generateCreature() }

  private val genListeners = mutableListOf<ActionListener>()

  fun runGeneration() {
    val creatureTrackers = currentCreatures.map { CreatureTracker(0, it) }
    creatureSnapshots.add(SimulationFrame(creatureTrackers))

    val simSteps = 2

    (1..simSteps).forEach {
      creatureTrackers.forEach { it.act() }
    }

    val sorted = creatureTrackers.sortedByDescending { it.result() }

    val survivors = sorted.take(currentCreatures.size / 2)

    val newCreatures1 = survivors.map { creaturRandomizer.mutateCreature(it.creature) }

    currentCreatures = survivors.map { it.creature }.union(newCreatures1).toList()
    genCount++

    genListeners.forEach { it.actionPerformed(null) }
  }

  fun addGenListener(function: () -> Unit) {
    genListeners.add(ActionListener { function.invoke() })
  }
}
