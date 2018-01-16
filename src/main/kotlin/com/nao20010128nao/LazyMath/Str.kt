package com.nao20010128nao.LazyMath

fun String.lazy(): LazyMath = toBigDecimal().lazy()

