package com.nao20010128nao.LazyMath

import java.math.BigDecimal
import java.math.BigInteger

interface LazyMath {
    operator fun plus(right: LazyMath): LazyMath
    operator fun minus(right: LazyMath): LazyMath
    operator fun times(right: LazyMath): LazyMath
    operator fun div(right: LazyMath): LazyMath
    operator fun unaryMinus(): LazyMath
    operator fun unaryPlus(): LazyMath = this
    operator fun rem(rem: LazyMath): LazyMath
    fun power(factor: LazyMath): LazyMath
    fun sqrt(): LazyMath

    fun <T> calculate(visitor: LazyMathVisitor<T>): T
}

interface LazyMathVisitor<T> {
    fun from(v: Int): T
    fun from(v: Long): T
    fun from(v: Short): T
    fun from(v: Byte): T
    fun from(v: Float): T
    fun from(v: Double): T
    fun from(v: BigInteger): T
    fun from(v: BigDecimal): T

    fun plus(left: T, right: T): T
    fun minus(left: T, right: T): T
    fun times(left: T, right: T): T
    fun div(left: T, right: T): T
    fun negate(value: T): T
    fun plus(value: T): T = value
    fun mod(value: T, rem: T): T
    fun power(base: T, factor: T): T
    fun sqrt(value: T): T
}

