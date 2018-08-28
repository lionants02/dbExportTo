package ffc.airsync.exportdb.db.jhcisdb

import org.jdbi.v3.core.mapper.RowMapper
import org.jdbi.v3.core.statement.StatementContext
import org.jdbi.v3.sqlobject.config.RegisterRowMapper
import org.jdbi.v3.sqlobject.statement.SqlQuery
import java.sql.ResultSet

interface QueryHomeHealthType {
    @SqlQuery(
        """
SELECT
	chomehealthtype.homehealthcode as code,
	chomehealthtype.homehealthmeaning as mean,
	chomehealthtype.homehealthmap as map
FROM
	chomehealthtype
    """
    )
    @RegisterRowMapper(GetHomeHealthMapper::class)
    fun get(): List<HashMap<String, String?>>
}

class GetHomeHealthMapper : RowMapper<HashMap<String, String?>> {
    override fun map(rs: ResultSet?, ctx: StatementContext?): HashMap<String, String?> {
        if (rs == null) throw ClassNotFoundException()

        val homeHealthType = HashMap<String, String?>()

        homeHealthType["code"] = rs.getString("code")
        homeHealthType["mean"] = rs.getString("mean")
        homeHealthType["map"] = rs.getString("map")


        return homeHealthType
    }
}