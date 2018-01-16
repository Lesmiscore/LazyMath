package com.nao20010128nao.LazyMath

import java.math.BigDecimal
import java.math.BigInteger

fun Float.lazy(): LazyMath = LazyFloat(this)
fun LazyMath.toFloat(): Float = calculate(LazyFloatVisitor)

@GenesisOperation
private class LazyFloat(override val value: Float) : LazyMathGenesisImpl<Float>() {
    override fun <T> calculate(visitor: LazyMathVisitor<T>): T = visitor.from(value)
}

private object LazyFloatVisitor : LazyMathVisitor<Float> {
    override fun from(v: Int): Float = v.toFloat()

    override fun from(v: Long): Float = v.toFloat()

    override fun from(v: Short): Float = v.toFloat()

    override fun from(v: Byte): Float = v.toFloat()

    override fun from(v: Float): Float = v

    override fun from(v: Double): Float = v.toFloat()

    override fun from(v: BigInteger): Float = v.toFloat()

    override fun from(v: BigDecimal): Float = v.toFloat()

    override fun plus(left: Float, right: Float): Float = left + right

    override fun minus(left: Float, right: Float): Float = left - right

    override fun times(left: Float, right: Float): Float = left * right

    override fun div(left: Float, right: Float): Float = left / right

    override fun negate(value: Float): Float = -value

    override fun mod(value: Float, rem: Float): Float = value % rem

    override fun power(base: Float, factor: Float): Float = when (base) {
        0f -> when (factor) {
            0f -> pow00
            else -> 0f
        }
        1f -> 1f
        2f -> (1 shl factor.toInt()).toFloat()
        else -> when (factor) {
            0f -> 1f
            1f -> base
            2f -> base * base
            3f -> base * base * base
            else -> Math.pow(base.toDouble(), factor.toDouble()).toFloat()
        }
    }

    override fun sqrt(value: Float): Float = Math.sqrt(value.toDouble()).toFloat()
}
