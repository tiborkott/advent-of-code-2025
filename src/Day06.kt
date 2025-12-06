data class  MathHomework(
    var operands: MutableList<MutableList<Int>> = mutableListOf(),
    var operators: MutableList<Char> = mutableListOf()
){
    init {
        readInput("Day06").forEach { line ->
            if(!(line.contains('*') or line.contains('+'))){
                line.trim()
                    .split(Regex("\\s+"))
                    .map { it.toInt() }
                    .toMutableList()
                    .also { operands.add(it) }
            }
            if(line.contains('*') or line.contains('+')){
                line.split(Regex("\\s+"))
                    .forEach { operators.add(it[0]) }
            }
        }
    }
}


fun MathHomework.solve(): Long{
    var sum = 0L
    for (index in 0 ..< operators.size){
        when(operators[index]){
            '*' -> operands.map { it[index] }.fold(1L) { acc, o -> acc * o }.also { sum += it }
            '+' -> operands.map { it[index] }.fold(0L) { acc, o -> acc + o }.also { sum += it }
        }
    }
    return sum
}

data class CephalopodMathHomework(
    val input: MutableList<String> = mutableListOf()
){
    init {
        readInputWithoutTrim("Day06").forEach { line ->
            if(line.contains('*') or line.contains('+')){
               input.add(
                   line.replace(Regex("([+*])\\s+")) { m ->
                       m.groupValues[1].repeat(m.value.length)
                   }.reversed()
               ) 
            }else{
                input.add(line.reversed())
            }
        }
        input.forEach { println(it) }
        
    }
}

fun CephalopodMathHomework.solve(): Long{
    var sum = 0L
    var result = 0L
    var operator = input.last().first()
    when(operator){
        '*' -> result = 1L
        '+' -> result = 0L
    }
    for(i in 0..input[0].lastIndex){
        val column = input.map { it[i] }
        if(column.dropLast(1).all{it == ' '}){
            // new result calculation will start here
            sum += result
            operator = column.last()
            when(operator){
                '*' -> result = 1L
                '+' -> result = 0L
            }
            continue 
        }
        when(operator){
            '+' -> {
                result += column.dropLast(1).joinToString("").trim(' ').toLong()
            }
            '*' -> {
                result *= column.dropLast(1).joinToString("").trim(' ').toLong()
            }
        }
        if(i == input[0].lastIndex){
            sum += result
        }
    }
    return sum
}

fun main() {


    fun part1(): Long {
        /**
         * What is the grand total found by adding together all of the answers to the individual problems?
         */
        val homework = MathHomework()
        return homework.solve()
    }

    fun part2(): Long {
        /**
         * What is the grand total found by adding together all of the answers to the individual problems?
         */
        val homework = CephalopodMathHomework()
        return homework.solve()
    }

    val (result1, duration1)  = measure { part1() }
    val (result2, duration2)  = measure { part2() }

    println("+-------------------------------------------")
    println("| Day 6: Trash Compactor ")
    println("+-------------------------------------------")
    println("| Part 1 solution = $result1 (took $duration1 ms)")
    println("| Part 2 solution = $result2 (took $duration2 ms)")
    println("+-------------------------------------------")
}