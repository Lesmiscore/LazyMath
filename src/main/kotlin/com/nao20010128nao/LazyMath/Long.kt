package com.nao20010128nao.LazyMath

import java.math.BigDecimal
import java.math.BigInteger

fun Long.lazy(): LazyMath = LazyLong(this)
fun LazyMath.toLong(): Long = calculate(LazyLongVisitor)

@GenesisOperation
private class LazyLong(override val value: Long) : LazyMathGenesisImpl<Long>() {
    override fun <T> calculate(visitor: LazyMathVisitor<T>): T = visitor.from(value)
}

private object LazyLongVisitor : LazyMathVisitor<Long> {
    override fun from(v: Int): Long = v.toLong()

    override fun from(v: Long): Long = v

    override fun from(v: Short): Long = v.toLong()

    override fun from(v: Byte): Long = v.toLong()

    override fun from(v: Float): Long = v.toLong()

    override fun from(v: Double): Long = v.toLong()

    override fun from(v: BigInteger): Long = v.toLong()

    override fun from(v: BigDecimal): Long = v.toLong()

    override fun plus(left: Long, right: Long): Long = left + right

    override fun minus(left: Long, right: Long): Long = left - right

    override fun times(left: Long, right: Long): Long = left * right

    override fun div(left: Long, right: Long): Long = left / right

    override fun negate(value: Long): Long = -value

    override fun mod(value: Long, rem: Long): Long = value % rem

    override fun power(base: Long, factor: Long): Long = when (base) {
        0L -> when (factor) {
            0L -> pow00
            else -> 0
        }
        1L -> 1
        2L -> 1L shl factor.toInt()
        else -> when (factor) {
            0L -> 1
            1L -> base
            2L -> base * base
            3L -> base * base * base
            else -> Math.pow(base.toDouble(), factor.toDouble()).toLong()
        }
    }

    override fun sqrt(value: Long): Long = Math.sqrt(value.toDouble()).toLong()
}
