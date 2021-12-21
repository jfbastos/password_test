package com.example.passwordtestingiesb.utils

object ValidationsUtils {

    fun isContainDigit(str: String): Boolean {
        val charSequence = "0123456789"
        charSequence.forEach {
            if(str.contains(it)) return true
        }
        return false
    }

    fun isContainSpecialCharacter(str: String): Boolean {
        val charSequence = "!@#$%¨&*()_+´[~],.;/`{^}<>:?/*-+.,£¢¬ªº°"
        charSequence.forEach {
            if(str.contains(it)) return true
        }
        return false
    }


}