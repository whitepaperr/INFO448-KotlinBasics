package edu.uw.basickotlin

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg: Any): String {
    return when (arg) {
        "Hello" -> "world"
        is String -> "Say what?"
        0 -> "zero"
        1 -> "one"
        in 2..10 -> "low number"
        is Int -> "a number"
        else -> "I don't understand"
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(lhs: Int, rhs: Int): Int = lhs + rhs

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(lhs: Int, rhs: Int): Int = lhs - rhs

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(lhs: Int, rhs: Int, op: (Int, Int) -> Int) : Int = op(lhs, rhs)

// write a class "Person" with first name, last name and age
class Person(val firstName: String, val lastName: String, val age: Int) {
    val debugString: String
        get() = "[Person firstName:$firstName lastName:$lastName age:$age]"
}

// write a class "Money"
class Money(var amount: Int, var currency: String) {
    init {
        require(amount >= 0)
        require(currency in listOf("USD", "EUR", "CAN", "GBP"))
    }

    fun convert(toCurrency: String): Money {
        if (currency == toCurrency) return this
        val conversionRate = getConversionRate(currency, toCurrency)
        val convertedAmount = ((amount * conversionRate)+0.5).toInt()
        return Money(convertedAmount, toCurrency)
    }
    
    private fun getConversionRate(from: String, to: String): Double {
        return when (from to to) {
            "USD" to "EUR" -> 1.5
            "USD" to "CAN" -> 1.25
            "USD" to "GBP" -> 0.5

            "EUR" to "USD" -> 0.6667
            "EUR" to "GBP" -> 0.3333
            "EUR" to "CAN" -> 1.6667

            "CAN" to "EUR" -> 0.6
            "CAN" to "GBP" -> 0.4
            "CAN" to "USD" -> 0.8

            "GBP" to "USD" -> 2.0
            "GBP" to "EUR" -> 3.0
            "GBP" to "CAN" -> 2.5

            else -> 1.0
        }
    }

    operator fun plus(money: Money): Money {
        val converted = money.convert(currency)
        return Money(amount + converted.amount, currency)
    }
}

