package com.example.iv_test_hazirah.traits

interface SuperClassSleuth {
    fun getBaseClass(): Class<*> = Class.forName(javaClass.name.split("$")[0])
}