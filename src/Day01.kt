data class Dial(
    var pointer: Int = 50,
    var password: Int = 0,
    var securePassword: Int = 0,
    var rotations: MutableList<Int> = mutableListOf()
){
    init {
        readInput("Day01").forEach { line ->
            val value = line.drop(1).toInt()
            rotations.add(
                when (line.first()) {
                    'R' -> value
                    else -> -value
                }
            )
        }
    }
}

fun Dial.rotate(){
    if((pointer + rotations[0])%100 == 0){
        password++
    }
    pointer = (pointer + rotations.removeFirst()) % 100
}

fun Dial.rotateEach(){
    if(rotations[0]>0){
        for (position in 1..rotations[0] step 1) {
            if((pointer + position)%100 == 0){
                securePassword++
            }
        }
    }else{
        for (position in 1..(rotations[0]*-1) step 1) {
            if((pointer - position)%100 == 0){
                securePassword++
            }
        }
    }
    pointer = (pointer + rotations.removeFirst()) % 100

}

fun main() {

    fun part1(): Int {
        /**
         * What's the actual password to open the door?
         */
        val dial = Dial()
        while(dial.rotations.isNotEmpty()){
            dial.rotate()
        }
        return dial.password
    }

    fun part2(): Int {
        /**
         * Using password method 0x434C49434B, what is the password to open the door?
         */
        val dial = Dial()
        while(dial.rotations.isNotEmpty()){
            dial.rotateEach()
        }
        return dial.securePassword
    }



    val (result1, duration1)  = measure { part1() }
    val (result2, duration2)  = measure { part2() }

    println("+-------------------------------------------")
    println("| Day 1: Secret Entrance")
    println("+-------------------------------------------")
    println("| Part 1 solution = $result1 (took $duration1 ms)")
    println("| Part 2 solution = $result2 (took $duration2 ms)")
    println("+-------------------------------------------")
}