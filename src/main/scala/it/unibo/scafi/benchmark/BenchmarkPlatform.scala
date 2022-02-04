package it.unibo.scafi.benchmark
import it.unibo.scafi.config.GridSettings
import it.unibo.scafi.incarnations.BasicSimulationIncarnation._
object BenchmarkPlatform extends App {
  val howMany = 5
  val range = 2
  val ticks = 10000
  val simulator = simulatorFactory.gridLike(
    GridSettings(howMany, howMany, range, range),
    range
  )
  val program = new RepCheck // find a way to pass program as arguments
  // Count the execution time, check if some framework exists
  val startingTime =
    System.currentTimeMillis() // Todo, this is not the correct way to benchmark time, verify if exists some lib
  (0 to ticks) foreach { _ => simulator.exec(program) }
  println(s"Execution time: ${System.currentTimeMillis() - startingTime}")
}
