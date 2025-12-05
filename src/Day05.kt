import kotlin.math.max

data class Range(
    val start: Long,
    val end: Long
)

fun Range.contains(id: Long) = id in start..end

data class InventoryManagementSystem(
    var freshRanges: MutableList<Range> = mutableListOf(),
    var availableIngredients: MutableList<Long> = mutableListOf()
){
    init {
        val input = readInput("Day05")
        input.forEach { line ->
            if(line.contains("-")){
                val (start,end) = line.split("-").map { it.toLong() }
                freshRanges.add(Range(start,end))
            }else{
                if(line.isNotBlank()){
                    availableIngredients.add(line.toLong())
                }
            }
        }
    }
}

fun InventoryManagementSystem.count(): Int {
    var counter = 0
    availableIngredients.forEach { ingredient ->
        if (freshRanges.any { it.contains(ingredient) }) {
            counter++
        }
    }
    return counter
}

fun InventoryManagementSystem.total(): Long {
    var total = 0L
    val merged = mutableListOf<Range>()

    freshRanges = freshRanges.sortedWith(compareBy<Range> { it.start }.thenBy { it.end }).toMutableList()

    var index = 0
    var offset: Int
    var range = Range(freshRanges[index].start, freshRanges[index].end)
    while(index < freshRanges.size-1){
        offset = 1
        while(freshRanges[index+offset].start <= range.end){
            range = Range(freshRanges[index].start, max(freshRanges[index+offset].end,range.end))
            offset++
            if(range.end == freshRanges.last().end) break
        }
        merged.add(range)
        if(range.end == freshRanges.last().end) break
        index+=offset
        range = Range(freshRanges[index].start, freshRanges[index].end)
    }
    merged.forEach { range ->
        total += (range.end - range.start + 1)
    }
    return total
}

fun main() {

    fun part1(): Int {
        /**
         * How many of the available ingredient IDs are fresh?
         */
        val inventory = InventoryManagementSystem()
        return inventory.count()
    }

    fun part2(): Long {
        /**
         *  How many ingredient IDs are considered to be fresh according to the fresh ingredient ID ranges?
         */
        val inventory = InventoryManagementSystem()
        return inventory.total()
    }

    val (result1, duration1)  = measure { part1() }
    val (result2, duration2)  = measure { part2() }

    println("+-------------------------------------------")
    println("| Day 5: Cafeteria ")
    println("+-------------------------------------------")
    println("| Part 1 solution = $result1 (took $duration1 ms)")
    println("| Part 2 solution = $result2 (took $duration2 ms)")
    println("+-------------------------------------------")
}