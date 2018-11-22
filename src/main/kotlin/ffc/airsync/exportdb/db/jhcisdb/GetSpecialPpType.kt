package ffc.airsync.exportdb.db.jhcisdb

import ffc.entity.healthcare.SpecialPP.PPType
import org.jdbi.v3.core.mapper.RowMapper
import org.jdbi.v3.core.statement.StatementContext
import org.jdbi.v3.sqlobject.config.RegisterRowMapper
import org.jdbi.v3.sqlobject.statement.SqlQuery
import java.sql.ResultSet

interface QuerySpecialPP {
    @SqlQuery(
        """
SELECT
	cspecialpp.ppcode,
	cspecialpp.ppname
FROM
	cspecialpp
    """
    )
    @RegisterRowMapper(SpecialPpMapper::class)
    fun get(): List<PPType>
}

internal class SpecialPpMapper : RowMapper<PPType> {
    override fun map(rs: ResultSet, ctx: StatementContext?): PPType {
        return PPType(
            id = rs.getString("ppcode"),
            name = rs.getString("ppname")
        )
    }
}
