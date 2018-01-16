package com.nao20010128nao.LazyMath

import java.math.BigDecimal
import java.math.BigInteger

fun Double.lazy(): LazyMath = LazyDouble(this)
fun LazyMath.toDouble(): Double = calculate(LazyDoubleVisitor)

@GenesisOperation
private class LazyDouble(override val value: Double) : LazyMathGenesisImpl<Double>() {
    override fun <T> calculate(visitor: LazyMathVisitor<T>): T = visitor.from(value)
}

private object LazyDoubleVisitor : LazyMathVisitor<Double> {
    override fun from(v: Int): Double = v.toDouble()

    override fun from(v: Long): Double = v.toDouble()

    override fun from(v: Short): Double = v.toDouble()

    override fun from(v: Byte): Double = v.toDouble()

    override fun from(v: Float): Double = v.toDouble()

    override fun from(v: Double): Double = v

    override fun from(v: BigInteger): Double = v.toDouble()

    override fun from(v: BigDecimal): Double = v.toDouble()

    override fun plus(left: Double, right: Double): Double = left + right

    override fun minus(left: Double, right: Double): Double = left - right

    override fun times(left: Double, right: Double): Double = left * right

    override fun div(left: Double, right: Double): Double = left / right

    override fun negate(value: Double): Double = -value

    override fun mod(value: Double, rem: Double): Double = value % rem

    override fun power(base: Double, factor: Double): Double = when (base) {
        0.0 -> when (factor) {
            0.0 -> pow00
            else -> 0.0
        }
        1.0 -> 1.0
        2.0 -> (1 shl factor.toInt()).toDouble()
        else -> when (factor) {
            0.0 -> 1.0
            1.0 -> base
            2.0 -> base * base
            3.0 -> base * base * base
            else -> Math.pow(base, factor)
        }
    }

    override fun sqrt(value: Double): Double = Math.sqrt(value)
}
