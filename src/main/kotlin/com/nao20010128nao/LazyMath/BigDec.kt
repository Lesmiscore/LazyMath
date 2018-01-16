package com.nao20010128nao.LazyMath

import org.nevec.rjm.BigDecimalMath
import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext


fun BigDecimal.lazy(): LazyMath = LazyBigDecimal(this)
fun LazyMath.toBigDecimal(): BigDecimal = calculate(LazyBigDecimalVisitor)

@GenesisOperation
private class LazyBigDecimal(override val value: BigDecimal) : LazyMathGenesisImpl<BigDecimal>() {
    override fun <T> calculate(visitor: LazyMathVisitor<T>): T = visitor.from(value)
}

private object LazyBigDecimalVisitor : LazyMathVisitor<BigDecimal> {
    override fun from(v: Int): BigDecimal = v.toBigDecimal()

    override fun from(v: Long): BigDecimal = v.toBigDecimal()

    override fun from(v: Short): BigDecimal = BigDecimal(v.toString())

    override fun from(v: Byte): BigDecimal = BigDecimal(v.toString())

    override fun from(v: Float): BigDecimal = BigDecimal(v.toString())

    override fun from(v: Double): BigDecimal = BigDecimal(v.toString())

    override fun from(v: BigInteger): BigDecimal = v.toBigDecimal()

    override fun from(v: BigDecimal): BigDecimal = v

    override fun plus(left: BigDecimal, right: BigDecimal): BigDecimal = left + right

    override fun minus(left: BigDecimal, right: BigDecimal): BigDecimal = left - right

    override fun times(left: BigDecimal, right: BigDecimal): BigDecimal = left * right

    override fun div(left: BigDecimal, right: BigDecimal): BigDecimal = left / right

    override fun negate(value: BigDecimal): BigDecimal = -value

    override fun mod(value: BigDecimal, rem: BigDecimal): BigDecimal = value % rem

    override fun power(base: BigDecimal, factor: BigDecimal): BigDecimal = when (base) {
        BD_ZERO -> when (factor) {
            BD_ZERO -> pow00
            else -> BD_ZERO
        }
        BD_ONE -> BD_ONE
        else -> when (factor) {
            BD_ZERO -> BD_ONE
            BD_ONE -> base
            BD_TWO -> base * base
            BD_THREE -> base * base * base
            else -> base.pow(factor)
        }
    }

    override fun sqrt(value: BigDecimal): BigDecimal = value.sqrt(value.precision())
}

val BD_ZERO = BigDecimal.ZERO
val BD_ONE = BigDecimal.ONE
val BD_TWO = BigDecimal.valueOf(2)
val BD_THREE = BigDecimal.valueOf(3)

fun BigDecimal.sqrt(scale: Int): BigDecimal {
    var x = BigDecimal(Math.sqrt(toDouble()), MathContext.DECIMAL64)
    if (scale < 17) return x

    var tempScale = 16
    while (tempScale < scale) {
        //x = x - (x * x - a) / (2 * x);
        x -= (((x * x) - this).divide(x * BD_TWO, scale, BigDecimal.ROUND_HALF_EVEN))
        tempScale *= 2
    }
    return x
}

fun BigDecimal.pow(exponent: BigDecimal): BigDecimal = BigDecimalMath.pow(this, exponent)
