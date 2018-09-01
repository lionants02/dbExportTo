package ffc.airsync.exportdb.db.jhcisdb

import ffc.entity.Lang
import ffc.entity.Link
import ffc.entity.System
import ffc.entity.healthcare.CommunityServiceType
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
    fun get(): List<CommunityServiceType>
}

class GetHomeHealthMapper : RowMapper<CommunityServiceType> {
    override fun map(rs: ResultSet?, ctx: StatementContext?): CommunityServiceType {
        if (rs == null) throw ClassNotFoundException()

        val homeHealthType = CommunityServiceType(
            rs.getString("code"),
            rs.getString("mean")
        ).apply {

            translation[Lang.th] = rs.getString("mean")
            link = Link(System.JHICS).apply {
                keys["code"] = rs.getString("code")
            }
        }

        return homeHealthType
    }
}