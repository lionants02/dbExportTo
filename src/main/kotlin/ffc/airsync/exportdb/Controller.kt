package ffc.airsync.exportdb

import ffc.airsync.exportdb.db.JhcisDbDao
import ffc.airsync.utils.printDebug
import ffc.entity.gson.toJson

class Controller {
    fun process() {
        val jhcisDbDao = JhcisDbDao("127.0.0.1", "3333", "jhcisdb", "root", "123456")

        val diseaseList = jhcisDbDao.getDisease()
        val homeHealthType = jhcisDbDao.getHomeHealthType()

        printDebug(homeHealthType.toJson())
    }
}