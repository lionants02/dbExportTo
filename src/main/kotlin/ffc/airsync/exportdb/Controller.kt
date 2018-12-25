package ffc.airsync.exportdb

import ffc.airsync.exportdb.db.JhcisDbDao
import ffc.entity.gson.toJson
import java.io.FileWriter

class Controller {
    fun process() {
        val jhcisDbDao = JhcisDbDao("127.0.0.1", "3333", "jhcisdb", "root", "123456")

        val diseaseList = jhcisDbDao.getDisease()
        val homeHealthType = jhcisDbDao.getHomeHealthType()
        val specialPP = jhcisDbDao.getSpecialPpType()
        val template = jhcisDbDao.getTemplate()

        writeFile(diseaseList.toJson(), "Disease.json")
        writeFile(homeHealthType.toJson(), "HomeHealthType.json")
        writeFile(specialPP.toJson(), "specialPP.json")
        writeFile(template.toJson(), "template.json")
    }

    private fun writeFile(strData: String, fileName: String) {
        val fileWriter = FileWriter(fileName)
        fileWriter.write(strData)
        fileWriter.close()
    }
}
