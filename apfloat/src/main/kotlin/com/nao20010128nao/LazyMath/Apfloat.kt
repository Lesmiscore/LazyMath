package com.nao20010128nao.LazyMath

import org.apfloat.Apfloat
import org.apfloat.ApfloatMath
import java.math.BigDecimal
import java.math.BigInteger

fun Apfloat.lazy(): LazyMath = "$this".lazy()
fun LazyMath.toApfloat(): Apfloat = calculate(LazyApfloatVisitor)


private object LazyApfloatVisitor : LazyMathVisitor<Apfloat> {
    override fun from(v: Int): Apfloat = Apfloat(v.toLong())

    override fun from(v: Long): Apfloat = Apfloat(v)

    override fun from(v: Short): Apfloat = Apfloat(v.toLong())

    override fun from(v: Byte): Apfloat = Apfloat(v.toLong())

    override fun from(v: Float): Apfloat = Apfloat(v)

    override fun from(v: Double): Apfloat = Apfloat(v)

    override fun from(v: BigInteger): Apfloat = Apfloat(v)

    override fun from(v: BigDecimal): Apfloat = Apfloat(v)

    override fun plus(left: Apfloat, right: Apfloat): Apfloat = left + right

    override fun minus(left: Apfloat, right: Apfloat): Apfloat = left - right

    override fun times(left: Apfloat, right: Apfloat): Apfloat = left * right

    override fun div(left: Apfloat, right: Apfloat): Apfloat = left / right

    override fun negate(value: Apfloat): Apfloat = value.negate()

    override fun mod(value: Apfloat, rem: Apfloat): Apfloat = value.mod(rem)
    
    override fun power(base: Apfloat, factor: Apfloat): Apfloat = when (base) {
        AF_ZERO -> when (factor) {
            AF_ZERO -> pow00
            else -> AF_ZERO
        }
        AF_ONE -> AF_ONE
        else -> when (factor) {
            AF_ZERO -> AF_ONE
            AF_ONE -> base
            AF_TWO -> base * base
            AF_THREE -> base * base * base
            else -> ApfloatMath.pow(base,factor)
        }
    }

    override fun sqrt(value: Apfloat): Apfloat = ApfloatMath.sqrt(value)
}

val AF_ZERO = Apfloat.ZERO
val AF_ONE = Apfloat.ONE
val AF_TWO = Apfloat(2)
val AF_THREE = Apfloat(3)

internal val pow00: Nothing get() = throw ArithmeticException("0^0 == undefined")

operator fun Apfloat.plus(a:Apfloat):Apfloat=add(a)
operator fun Apfloat.minus(a: Apfloat):Apfloat=subtract(a)
operator fun Apfloat.times(a:Apfloat):Apfloat=multiply(a)
operator fun Apfloat.div(a: Apfloat):Apfloat=divide(a)

