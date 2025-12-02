data class GiftShopDatabase(
    var ranges: MutableList<Pair<Long,Long>> = mutableListOf(),
    var invalids: MutableSet<Long> = mutableSetOf(),
){
    init{
        readInput("Day02")[0]
            .split(",")
            .forEach { range ->
                ranges.add(range.split("-").let { (min, max) -> min.toLong() to max.toLong() })
            }
    }
}

fun GiftShopDatabase.findInvalids(){
    ranges.forEach { range ->
        for (id in range.first..range.second) {
            id.toString().also { if (it.take(it.length / 2) == it.drop(it.length / 2)) invalids.add(id) }
        }
    }
}


fun GiftShopDatabase.findAllInvalids(){
    ranges.forEach { range ->
        for (id in range.first..range.second) {
            val string = id.toString()
            for(chunk in 1..string.length/2){
                if(string.chunked(chunk).distinct().size == 1){
                    invalids.add(id)
                    break
                }
            }
        }
    }
}

fun main() {

    val database = GiftShopDatabase()

    fun part1(): Long {
        /**
         * What do you get if you add up all of the invalid IDs?
         */
        database.findInvalids()
        return database.invalids.sum()
    }

    fun part2(): Long {
        /**
         * What do you get if you add up all of the invalid IDs using these new rules?
         */
        database.findAllInvalids()
        return database.invalids.sum()
    }

    val (result1, duration1)  = measure { part1() }
    val (result2, duration2)  = measure { part2() }

    println("+-------------------------------------------")
    println("| Day 2: Gift Shop")
    println("+-------------------------------------------")
    println("| Part 1 solution = $result1 (took $duration1 ms)")
    println("| Part 2 solution = $result2 (took $duration2 ms)")
    println("+-------------------------------------------")
}