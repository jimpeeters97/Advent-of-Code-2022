import java.io.File

fun main() {
    val topCrateList1 = arrayListOf<String>()
    val topCrateList2 = arrayListOf<String>()

    getCrates(false).values.forEach {
        if (it.isNotEmpty()) {
            topCrateList1.add(it.last())
        }
    }

    getCrates(true).values.forEach {
        if (it.isNotEmpty()) {
            topCrateList2.add(it.last())
        }
    }

    println("The result of part 1 is: " + topCrateList1.joinToString(""))
    println("The result of part 2 is: " + topCrateList2.joinToString(""))
}

fun getCrates(inOrder: Boolean): Map<Int, List<String>> {
    val result = mutableMapOf<Int, List<String>>()

    val crateList = arrayListOf<String>()
    File("src/files/day5.txt").forEachLine {
        if (it.contains("[")) {
            crateList.add(it)
        } else if (it.isNotEmpty() && it.trim()[0].isDigit()) {
            for (numberChar in it) {
                if (numberChar.isDigit()) {
                    val numberIdx = it.indexOf(numberChar)
                    for (crate in crateList.reversed()) {
                        if (crate.length >= numberIdx) {
                            val crateID = crate[numberIdx]

                            if (crateID != ' ') {
                                val mapCrateList = result[numberChar.digitToInt()]
                                val newCrateList = arrayListOf<String>()
                                if (mapCrateList != null) {
                                    newCrateList.addAll(mapCrateList)
                                }
                                newCrateList.add(crateID.toString())
                                result[numberChar.digitToInt()] = newCrateList
                            }
                        }
                    }
                }
            }
        } else if (it.isNotEmpty()) {
            val moveAmount = it.substring(it.indexOf("from") - 3, it.indexOf("from") - 1).trim().toInt()
            val fromCrate = it[it.indexOf("to") - 2]
            val toCrate = it[it.indexOf("to") + 3]

            val fromCrateList = mutableListOf<String>()
            fromCrateList.addAll(result[fromCrate.digitToInt()]!!)

            val transferList = fromCrateList.takeLast(moveAmount)
            val newFromCrateList = fromCrateList.dropLast(moveAmount)

            val toCrateList = mutableListOf<String>()
            toCrateList.addAll(result[toCrate.digitToInt()]!!)

            if (inOrder) {
                toCrateList.addAll(transferList)
            } else {
                toCrateList.addAll(transferList.reversed())
            }

            result[fromCrate.digitToInt()] = newFromCrateList
            result[toCrate.digitToInt()] = toCrateList
        }
    }
    return result
}