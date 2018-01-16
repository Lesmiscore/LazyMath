package com.nao20010128nao.LazyMath

import java.math.BigDecimal
import java.math.BigInteger

fun Byte.lazy(): LazyMath = LazyByte(this)
fun LazyMath.toByte(): Byte = calculate(LazyByteVisitor)

@GenesisOperation
private class LazyByte(override val value: Byte) : LazyMathGenesisImpl<Byte>() {
    override fun <T> calculate(visitor: LazyMathVisitor<T>): T = visitor.from(value)
}

private object LazyByteVisitor : LazyMathVisitor<Byte> {
    override fun from(v: Int): Byte = v.toByte()

    override fun from(v: Long): Byte = v.toByte()

    override fun from(v: Short): Byte = v.toByte()

    override fun from(v: Byte): Byte = v

    override fun from(v: Float): Byte = v.toByte()

    override fun from(v: Double): Byte = v.toByte()

    override fun from(v: BigInteger): Byte = v.toByte()

    override fun from(v: BigDecimal): Byte = v.toByte()

    override fun plus(left: Byte, right: Byte): Byte = (left + right).toByte()

    override fun minus(left: Byte, right: Byte): Byte = (left - right).toByte()

    override fun times(left: Byte, right: Byte): Byte = (left * right).toByte()

    override fun div(left: Byte, right: Byte): Byte = (left / right).toByte()

    override fun negate(value: Byte): Byte = (-value).toByte()

    override fun mod(value: Byte, rem: Byte): Byte = (value % rem).toByte()

    override fun power(base: Byte, factor: Byte): Byte = when (base) {
        0.toByte() -> when (factor) {
            0.toByte() -> pow00
            else -> 0
        }
        1.toByte() -> 1
        2.toByte() -> (1 shl factor.toInt()).toByte()
        else -> when (factor) {
            0.toByte() -> 1
            1.toByte() -> base
            2.toByte() -> (base * base).toByte()
            3.toByte() -> (base * base * base).toByte()
            else -> Math.pow(base.toDouble(), factor.toDouble()).toByte()
        }
    }

    override fun sqrt(value: Byte): Byte = Math.sqrt(value.toDouble()).toByte()
}
