package ffc.airsync.exportdb

internal class Main constructor(args: Array<String>) {

    fun run() {
        Controller().process()
    }
}

fun main(args: Array<String>) {
    Main(args).run()
}