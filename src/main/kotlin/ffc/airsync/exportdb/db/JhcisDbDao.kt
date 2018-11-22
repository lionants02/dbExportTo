package ffc.airsync.exportdb.db

import ffc.airsync.exportdb.QueryData
import ffc.airsync.exportdb.db.jhcisdb.QueryDisease
import ffc.airsync.exportdb.db.jhcisdb.QueryHomeHealthType
import ffc.entity.healthcare.CommunityService.ServiceType
import ffc.entity.healthcare.Disease
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.jdbi.v3.sqlobject.SqlObjectPlugin
import org.jdbi.v3.sqlobject.kotlin.KotlinSqlObjectPlugin

class JhcisDbDao(
    val dbHost: String,
    val dbPort: String,
    val dbName: String,
    val dbUsername: String,
    val dbPassword: String
) : QueryData {

    override fun getDisease(): List<Disease> {
        return createJdbi().extension<QueryDisease, List<Disease>> { get() }
    }

    override fun getHomeHealthType(): List<ServiceType> {
        return createJdbi().extension<QueryHomeHealthType, List<ServiceType>> { get() }
    }

    private fun createJdbi(): Jdbi {
        Class.forName("com.mysql.jdbc.Driver")

        val ds = com.mysql.jdbc.jdbc2.optional.MysqlDataSource()
        ds.setURL("jdbc:mysql://$dbHost:$dbPort/$dbName?autoReconnect=true&useSSL=false")
        ds.databaseName = dbName
        ds.user = dbUsername
        ds.setPassword(dbPassword)
        ds.port = dbPort.toInt()

        val jdbi = Jdbi.create(ds)
        jdbi.installPlugin(KotlinPlugin())
        jdbi.installPlugin(SqlObjectPlugin())
        jdbi.installPlugin(KotlinSqlObjectPlugin())
        return jdbi
    }
}
