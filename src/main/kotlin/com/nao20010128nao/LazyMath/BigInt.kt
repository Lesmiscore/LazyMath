package com.nao20010128nao.LazyMath

import java.math.BigDecimal
import java.math.BigInteger

fun BigInteger.lazy(): LazyMath = LazyBigInteger(this)
fun LazyMath.toBigInteger(): BigInteger = calculate(LazyBigIntegerVisitor)

@GenesisOperation
private class LazyBigInteger(override val value: BigInteger) : LazyMathGenesisImpl<BigInteger>() {
    override fun <T> calculate(visitor: LazyMathVisitor<T>): T = visitor.from(value)
}

private object LazyBigIntegerVisitor : LazyMathVisitor<BigInteger> {
    override fun from(v: Int): BigInteger = v.toBigInteger()

    override fun from(v: Long): BigInteger = v.toBigInteger()

    override fun from(v: Short): BigInteger = BigInteger(v.toString())

    override fun from(v: Byte): BigInteger = BigInteger(v.toString())

    override fun from(v: Float): BigInteger = BigInteger(v.toString())

    override fun from(v: Double): BigInteger = BigInteger(v.toString())

    override fun from(v: BigInteger): BigInteger = v

    override fun from(v: BigDecimal): BigInteger = v.toBigInteger()

    override fun plus(left: BigInteger, right: BigInteger): BigInteger = left + right

    override fun minus(left: BigInteger, right: BigInteger): BigInteger = left - right

    override fun times(left: BigInteger, right: BigInteger): BigInteger = left * right

    override fun div(left: BigInteger, right: BigInteger): BigInteger = left / right

    override fun negate(value: BigInteger): BigInteger = -value

    override fun mod(value: BigInteger, rem: BigInteger): BigInteger = value % rem

    override fun power(base: BigInteger, factor: BigInteger): BigInteger = when (base) {
        BI_ZERO -> when (factor) {
            BI_ZERO -> pow00
            else -> BI_ZERO
        }
        BI_ONE -> BI_ONE
        BI_TWO -> BI_ONE shl factor.intValueExact()
        else -> when (factor) {
            BI_ZERO -> BI_ONE
            BI_ONE -> base
            BI_TWO -> base * base
            BI_THREE -> base * base * base
            else -> base.pow(factor)
        }
    }

    override fun sqrt(value: BigInteger): BigInteger = value.sqrt()
}

val BI_ZERO = BigInteger.ZERO
val BI_ONE = BigInteger.ONE
val BI_TWO = BigInteger.valueOf(2)
val BI_THREE = BigInteger.valueOf(3)

fun BigInteger.sqrt(): BigInteger {
    var a = BI_ONE
    var b = shr(5) + BigInteger.valueOf(8)
    while (b >= a) {
        val mid = (a + b) shl 1
        if (mid * mid > this)
            b = mid - BI_ONE
        else
            a = mid + BI_ONE
    }
    return a - BI_ONE
}

fun BigInteger.pow(_exponent: BigInteger): BigInteger {
    var base = this
    var exponent = _exponent
    var result = BigInteger.ONE
    while (exponent.signum() > 0) {
        if (exponent.testBit(0)) result *= base
        base *= base
        exponent = exponent.shiftRight(1)
    }
    return result
}