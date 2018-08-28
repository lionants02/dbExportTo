package ffc.airsync.utils

val debug = System.getenv("FFC_DEBUG")

fun printDebug(infoDebug: String) {
    if (debug == null)
        println(infoDebug)
}