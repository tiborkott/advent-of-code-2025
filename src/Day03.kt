data class EmergencyPower(
    var batteries: MutableList<MutableList<Int>> = mutableListOf(),
    var joltages: MutableList<Int> = mutableListOf(),
    var unsafeJoltages: MutableList<Long> = mutableListOf()
){
    init{
        readInput("Day03").forEach { line ->
            batteries.add(line.map { it.digitToInt() }.toMutableList())
        }
    }
}

fun main() {

    val emergencyPower = EmergencyPower()

    fun part1(): Int {
        /**
         * What is the total output joltage?
         */
        emergencyPower.batteries.forEach { battery ->
            emergencyPower.joltages.add(
                intArrayOf(
                    battery.dropLast(1).max(),
                    battery.subList(
                        fromIndex = battery.subList(0,battery.size-1)
                            .indexOfFirst {
                                it == battery.dropLast(1).max()
                            } + 1,
                        toIndex = battery.size
                    ).max()
                )
                .joinToString("")
                .toInt()
            )
        }
        return emergencyPower.joltages.sum()
    }

    fun part2(): Long {
        /**
         * What is the new total output joltage?
         */
        emergencyPower.batteries.forEach { battery ->
            var list = battery.toMutableList()
            val joltages = mutableListOf<Int>()

            for (offset in 11 downTo 0){
                val joltage = list.subList(0, list.size - offset).max()
                joltages.add(joltage)
                val index = list.subList(0, list.size - offset).indexOfFirst { it == joltage }
                list = list.drop(index + 1).toMutableList()
            }
            emergencyPower.unsafeJoltages.add(joltages.joinToString("").toLong())
        }
        return emergencyPower.unsafeJoltages.sum()
    }

    val (result1, duration1)  = measure { part1() }
    val (result2, duration2)  = measure { part2() }

    println("+-------------------------------------------")
    println("| Day 3: Lobby")
    println("+-------------------------------------------")
    println("| Part 1 solution = $result1 (took $duration1 ms)")
    println("| Part 2 solution = $result2 (took $duration2 ms)")
    println("+-------------------------------------------")
}