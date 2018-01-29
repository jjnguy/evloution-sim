package creatures

import creatures.features.Feature

class Counter(val features: List<Feature>) {

  fun count(seed: Long): Long {
    var result = seed
    features.forEach { result = it.act(result) }
    return result
  }

  fun species(): String = features.groupBy { it.species() }.map { "${it.key}${it.value.size}" }.sorted().joinToString("")
}
