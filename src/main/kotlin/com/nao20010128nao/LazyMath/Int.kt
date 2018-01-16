package com.nao20010128nao.LazyMath

import java.math.BigDecimal
import java.math.BigInteger

fun Int.lazy(): LazyMath = LazyInt(this)
fun LazyMath.toInt(): Int = calculate(LazyIntVisitor)

@GenesisOperation
private class LazyInt(override val value: Int) : LazyMathGenesisImpl<Int>() {
    override fun <T> calculate(visitor: LazyMathVisitor<T>): T = visitor.from(value)
}

private object LazyIntVisitor : LazyMathVisitor<Int> {
    override fun from(v: Int): Int = v

    override fun from(v: Long): Int = v.toInt()

    override fun from(v: Short): Int = v.toInt()

    override fun from(v: Byte): Int = v.toInt()

    override fun from(v: Float): Int = v.toInt()

    override fun from(v: Double): Int = v.toInt()

    override fun from(v: BigInteger): Int = v.toInt()

    override fun from(v: BigDecimal): Int = v.toInt()

    override fun plus(left: Int, right: Int): Int = left + right

    override fun minus(left: Int, right: Int): Int = left - right

    override fun times(left: Int, right: Int): Int = left * right

    override fun div(left: Int, right: Int): Int = left / right

    override fun negate(value: Int): Int = -value

    override fun mod(value: Int, rem: Int): Int = value % rem

    override fun power(base: Int, factor: Int): Int = when (base) {
        0 -> when (factor) {
            0 -> pow00
            else -> 0
        }
        1 -> 1
        2 -> 1 shl factor
        else -> when (factor) {
            0 -> 1
            1 -> base
            2 -> base * base
            3 -> base * base * base
            else -> Math.pow(base.toDouble(), factor.toDouble()).toInt()
        }
    }

    override fun sqrt(value: Int): Int = Math.sqrt(value.toDouble()).toInt()
}
