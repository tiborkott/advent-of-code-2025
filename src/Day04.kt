
data class HelpfulDiagram(
    var grid: MutableList<MutableList<Char>> = MutableList(136){ MutableList<Char>(136){' '} }
){
    init {
        val input = readInput("Day04")
        for (i in input.indices){
            for (j in input.indices){
                grid[i][j] = input[i][j]
            }
        }
    }
}

fun HelpfulDiagram.mark() {
    for (i in grid.indices){
        for (j in grid.indices){
            if(grid[i][j] == '@'){
                var counter = 0
                if(i-1>=0 && (grid[i-1][j] == '@' || grid[i-1][j] == 'X')) counter++
                if(i-1>=0 && j-1>=0 && (grid[i-1][j-1] == '@' || grid[i-1][j-1] == 'X')) counter++
                if(i-1>=0 && j+1<grid[0].size && (grid[i-1][j+1] == '@' || grid[i-1][j+1]=='X')) counter++
                if(j-1>=0 && (grid[i][j-1] == '@' || grid[i][j-1] == 'X')) counter++
                if(j+1<grid.size && (grid[i][j+1] == '@' || grid[i][j+1] == 'X')) counter++
                if(i+1<grid.size && j-1>=0 && (grid[i+1][j-1] == '@' || grid[i+1][j-1] == 'X')) counter++
                if(i+1<grid.size && (grid[i+1][j] == '@' || grid[i+1][j] == 'X')) counter++
                if(i+1<grid.size && j+1<grid[0].size && (grid[i+1][j+1] == '@' || grid[i+1][j+1] == 'X')) counter++
                if(counter<4) grid[i][j] = 'X'
            }
        }
    }
}

fun HelpfulDiagram.remove() {
    for (i in grid.indices){
        for (j in grid.indices){
            if(grid[i][j] == 'X'){
                grid[i][j] = '.'
            }
        }
    }
}

fun HelpfulDiagram.count(): Int {
    var counter = 0
    for (i in grid.indices){
        for (j in grid.indices){
            if(grid[i][j] == 'X') counter++
        }
    }
    return counter
}

fun main() {


    fun part1(): Int {
        /**
         * How many rolls of paper can be accessed by a forklift?
         */
        val diagram = HelpfulDiagram()
        diagram.mark()
        return diagram.count()
    }

    fun part2(): Int {
        /**
         * How many rolls of paper in total can be removed by the Elves and their forklifts?
         */
        val diagram = HelpfulDiagram()
        var total = 0
        diagram.mark()
        var count = diagram.count()
        while(count != 0){
            total += count
            diagram.remove()
            diagram.mark()
            count = diagram.count()
        }
        return total
    }

    val (result1, duration1)  = measure { part1() }
    val (result2, duration2)  = measure { part2() }

    println("+-------------------------------------------")
    println("| Day 4: Printing Department")
    println("+-------------------------------------------")
    println("| Part 1 solution = $result1 (took $duration1 ms)")
    println("| Part 2 solution = $result2 (took $duration2 ms)")
    println("+-------------------------------------------")
}