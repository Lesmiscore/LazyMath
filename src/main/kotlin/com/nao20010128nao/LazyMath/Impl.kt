package com.nao20010128nao.LazyMath

abstract internal class LazyMathImpl : LazyMath {
    override fun plus(right: LazyMath): LazyMath = PlusOperation(this, right)
    override fun minus(right: LazyMath): LazyMath = MinusOperation(this, right)
    override fun times(right: LazyMath): LazyMath = MultiplyOperation(this, right)
    override fun div(right: LazyMath): LazyMath = DivisionOperation(this, right)
    override fun unaryMinus(): LazyMath = NegateOperation(this)
    override fun unaryPlus(): LazyMath = Plus2Operation(this)
    override fun rem(rem: LazyMath): LazyMath = ReminderOperation(this, rem)
    override fun power(factor: LazyMath): LazyMath = PowerOperation(this, factor)
    override fun sqrt(): LazyMath = SqrtOperation(this)
    abstract override fun <T> calculate(visitor: LazyMathVisitor<T>): T
}

abstract internal class LazyMathGenesisImpl<T>: LazyMathImpl(){
    abstract internal val value:T
    override fun toString(): String = "$value"
}

@MiddleOperation
internal class PlusOperation(private val left: LazyMath, private val right: LazyMath) : LazyMathImpl() {
    override fun <T> calculate(visitor: LazyMathVisitor<T>): T =
            visitor.plus(left.calculate(visitor), right.calculate(visitor))

    override fun toString(): String = "($left + $right)"
}

@MiddleOperation
internal class MinusOperation(private val left: LazyMath, private val right: LazyMath) : LazyMathImpl() {
    override fun <T> calculate(visitor: LazyMathVisitor<T>): T =
            visitor.minus(left.calculate(visitor), right.calculate(visitor))

    override fun toString(): String = "($left - $right)"
}

@MiddleOperation
internal class MultiplyOperation(private val left: LazyMath, private val right: LazyMath) : LazyMathImpl() {
    override fun <T> calculate(visitor: LazyMathVisitor<T>): T =
            visitor.times(left.calculate(visitor), right.calculate(visitor))

    override fun toString(): String = "($left * $right)"
}

@MiddleOperation
internal class DivisionOperation(private val left: LazyMath, private val right: LazyMath) : LazyMathImpl() {
    override fun <T> calculate(visitor: LazyMathVisitor<T>): T =
            visitor.div(left.calculate(visitor), right.calculate(visitor))

    override fun toString(): String = "($left / $right)"
}

@MiddleOperation
internal class NegateOperation(private val value: LazyMath) : LazyMathImpl() {
    override fun <T> calculate(visitor: LazyMathVisitor<T>): T =
            visitor.negate(value.calculate(visitor))

    override fun toString(): String = "(-$value)"
}

@MiddleOperation
internal class Plus2Operation(private val value: LazyMath) : LazyMathImpl() {
    override fun <T> calculate(visitor: LazyMathVisitor<T>): T =
            visitor.plus(value.calculate(visitor))

    override fun toString(): String = "(+$value)"
}

@MiddleOperation
internal class ReminderOperation(private val value: LazyMath, private val rem: LazyMath) : LazyMathImpl() {
    override fun <T> calculate(visitor: LazyMathVisitor<T>): T =
            visitor.mod(value.calculate(visitor), rem.calculate(visitor))

    override fun toString(): String = "($value) % ($rem)"
}

@MiddleOperation
internal class PowerOperation(private val base: LazyMath, private val factor: LazyMath) : LazyMathImpl() {
    override fun <T> calculate(visitor: LazyMathVisitor<T>): T =
            visitor.power(base.calculate(visitor), factor.calculate(visitor))

    override fun toString(): String = "($base) ** ($factor)"
}

@MiddleOperation
internal class SqrtOperation(private val value: LazyMath) : LazyMathImpl() {
    override fun <T> calculate(visitor: LazyMathVisitor<T>): T =
            visitor.sqrt(value.calculate(visitor))

    override fun toString(): String = "√($value)"
}

