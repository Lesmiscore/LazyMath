package com.nao20010128nao.LazyMath

import java.math.BigDecimal
import java.math.BigInteger

fun Short.lazy(): LazyMath = LazyShort(this)
fun LazyMath.toShort(): Short = calculate(LazyShortVisitor)

@GenesisOperation
private class LazyShort(override val value: Short) : LazyMathGenesisImpl<Short>() {
    override fun <T> calculate(visitor: LazyMathVisitor<T>): T = visitor.from(value)
}

private object LazyShortVisitor : LazyMathVisitor<Short> {
    override fun from(v: Int): Short = v.toShort()

    override fun from(v: Long): Short = v.toShort()

    override fun from(v: Short): Short = v

    override fun from(v: Byte): Short = v.toShort()

    override fun from(v: Float): Short = v.toShort()

    override fun from(v: Double): Short = v.toShort()

    override fun from(v: BigInteger): Short = v.toShort()

    override fun from(v: BigDecimal): Short = v.toShort()

    override fun plus(left: Short, right: Short): Short = (left + right).toShort()

    override fun minus(left: Short, right: Short): Short = (left - right).toShort()

    override fun times(left: Short, right: Short): Short = (left * right).toShort()

    override fun div(left: Short, right: Short): Short = (left / right).toShort()

    override fun negate(value: Short): Short = (-value).toShort()

    override fun mod(value: Short, rem: Short): Short = (value % rem).toShort()

    override fun power(base: Short, factor: Short): Short = when (base) {
        0.toShort() -> when (factor) {
            0.toShort() -> pow00
            else -> 0
        }
        1.toShort() -> 1
        2.toShort() -> (1 shl factor.toInt()).toShort()
        else -> when (factor) {
            0.toShort() -> 1
            1.toShort() -> base
            2.toShort() -> (base * base).toShort()
            3.toShort() -> (base * base * base).toShort()
            else -> Math.pow(base.toDouble(), factor.toDouble()).toShort()
        }
    }

    override fun sqrt(value: Short): Short = Math.sqrt(value.toDouble()).toShort()
}
