package simulation

class SimulationFrame(val creatures: List<CreatureTracker>) {

  val average = creatures.map { it.result() }.average()
  val species = creatures.groupBy { it.creature.species() }
}