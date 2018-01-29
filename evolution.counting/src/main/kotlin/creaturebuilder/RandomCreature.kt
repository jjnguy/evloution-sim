package creaturebuilder

import creatures.Counter
import creatures.features.Feature
import java.util.*

class RandomCreature {

  val rand = Random()

  val startMaxFeatures = 5
  val maxFeatures = 9

  fun generateCreature(): Counter {
    val featureCount = rand.nextInt(5) + 1L
    val features = (0..featureCount).map {
      randomFeature()
    }
    return Counter(features)
  }

  fun mutateCreature(toMutate: Counter): Counter {
    val newFeatures = mutableListOf<Feature>()
    var removedFeature = false
    toMutate.features.forEach {
      val keepFeature = rand.nextBoolean() || removedFeature
      if (keepFeature) {
        val randomizeFeature = rand.nextBoolean() && rand.nextBoolean() && rand.nextBoolean()
        if (randomizeFeature){
          newFeatures.add(randomFeature())
        } else {
          newFeatures.add(it.mutate())
        }
      } else {
        removedFeature = true
      }
    }
    val addFeature = rand.nextBoolean() && rand.nextBoolean() && newFeatures.size < maxFeatures
    if (addFeature) {
      newFeatures.add(randomFeature())
    }
    return Counter(newFeatures)
  }

  private fun randomFeature(): Feature = FeatureBuilders.all[rand.nextInt(FeatureBuilders.all.size)].invoke()
}
