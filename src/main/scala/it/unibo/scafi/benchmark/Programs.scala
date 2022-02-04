package it.unibo.scafi.benchmark

import it.unibo.scafi.incarnations.BasicSimulationIncarnation._

// Basic constructor checks
class StaticFieldCheck extends AggregateProgram {
  override def main = 10
}

class RepCheck extends AggregateProgram {
  override def main = rep(0)(_ + 1)
}

class FoldhoodCheck extends AggregateProgram {
  override def main() = foldhood(0)(_ + _)(1)
}

class FoldhoodAndNbrCheck extends AggregateProgram {
  override def main() = foldhood(Set(mid()))(_ ++ _)(Set(nbr(mid())))
}

// Building block checks!
class GCheck extends AggregateProgram with StandardSensors with BlockG {
  override def main() = G[ID](mid() == 0, mid(), a => a, nbrRange)
}

class GradientCheck extends AggregateProgram with StandardSensors with BlockG with Gradients {
  override def main() = classicGradient(mid() == 0, nbrRange)
}

class CCheck extends AggregateProgram with StandardSensors with BlockC with BlockG {
  override def main() =
    C[Double, ID](hopDistance(mid() == 0), math.max, mid(), -1)
}

class TCheck extends AggregateProgram with BlockT {
  override def main(): Any = T(100)
}

class SCheck extends AggregateProgram with StandardSensors with BlockS {
  override def main(): Any = S(10, nbrRange)
}

// Used to verify the bundle size using all the building blocks
class BuildingBlocksBundleCheck extends AggregateProgram with StandardSensors with BuildingBlocks {
  override def main(): Any = 10
}
// High level pattern check
class ChannelCheck extends AggregateProgram with BlockG with StandardSensors {
  override def main(): Any = channel(mid() == 0, mid() == 10, 1)
}

class SCRPattern extends AggregateProgram with StandardSensors with BuildingBlocks {
  override def main(): Any = {
    val leader = S(10, nbrRange)
    val collectInfo = C[Double, ID](classicGradient(leader, nbrRange), _ + _, 1, 0)
    G[Int](leader, collectInfo, a => a, nbrRange)
  }
}

// Process check
class FewProcessCheck
    extends AggregateProgram
    with FieldUtils
    with CustomSpawn
    with BlockG
    with TimeUtils
    with StateManagement
    with StandardSensors {
  override def main(): Any =
    sspawn2[ID, Unit, Double](id => _ => POut(classicGradient(id == mid()), SpawnInterface.Output), Set(0), ())
}

class ManyProcessCheck
    extends AggregateProgram
    with FieldUtils
    with CustomSpawn
    with BlockG
    with TimeUtils
    with StateManagement
    with StandardSensors {
  override def main(): Any =
    sspawn2[ID, Unit, Double](id => _ => POut(classicGradient(id == mid()), SpawnInterface.Output), Set(mid()), ())
}
