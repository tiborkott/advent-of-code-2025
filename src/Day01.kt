data class Dial(
    var pointing: Int = 50,
    var password: Int = 0,
    var password_0x434C49434B: Int = 0,
    var rotations: MutableList<Int> = mutableListOf()
){
    init {
        readInput("Day01").forEach{ line ->
            if(line.startsWith('R')){
                rotations.add(line.removePrefix("R").toInt())
            }else{
                rotations.add(line.removePrefix("L").toInt() * -1)
            }
        }
    }
}

fun Dial.rotate(){
    if((pointing + rotations[0])%100 == 0){
        password++
    }
    pointing = (pointing + rotations.removeFirst()) % 100
}

fun Dial.rotateEach(){
    if(rotations[0]>0){
        for (position in 1..rotations[0] step 1) {
            if((pointing + position)%100 == 0){
                password_0x434C49434B++
            }
        }
    }else{
        for (position in 1..(rotations[0]*-1) step 1) {
            if((pointing - position)%100 == 0){
                password_0x434C49434B++
            }
        }
    }
    pointing = (pointing + rotations.removeFirst()) % 100

}
fun Dial.analyze(): Int{
    return password
}

fun Dial.analyze_0x434C49434B(): Int{
    return password_0x434C49434B
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
        return dial.analyze()
    }

    fun part2(): Int {
        /**
         * Using password method 0x434C49434B, what is the password to open the door?
         */
        val dial = Dial()
        while(dial.rotations.isNotEmpty()){
            dial.rotateEach()
        }
        return dial.analyze_0x434C49434B()
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