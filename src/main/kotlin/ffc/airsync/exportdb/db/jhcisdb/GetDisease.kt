package ffc.airsync.exportdb.db.jhcisdb

import ffc.entity.Lang
import ffc.entity.healthcare.Disease
import ffc.entity.util.generateTempId
import org.jdbi.v3.core.mapper.RowMapper
import org.jdbi.v3.core.statement.StatementContext
import org.jdbi.v3.sqlobject.config.RegisterRowMapper
import org.jdbi.v3.sqlobject.statement.SqlQuery
import java.sql.ResultSet
import java.util.regex.Pattern

interface QueryDisease {
    @SqlQuery(
        """
SELECT  cdisease.diseasecode,
        cdisease.mapdisease,
        cdisease.diseasename,
        cdisease.diseasenamethai,
        cdisease.code504,
        cdisease.code506,
        cdisease.codechronic,
        cdisease.codeoccupa
FROM
        cdisease
    """
    )
    @RegisterRowMapper(GetDiseaseMapper::class)
    fun get(): List<Disease>
}

class GetDiseaseMapper : RowMapper<Disease> {
    override fun map(rs: ResultSet?, ctx: StatementContext?): Disease {
        if (rs == null) throw ClassNotFoundException()

        val icd10 = rs.getString("diseasecode")
        val nameEn = rs.getString("diseasename")
        val nameTh = rs.getString("diseasenamethai")
        val chronicCode = rs.getString("codechronic")

        val disease = Disease(
            id = generateTempId(),
            name = nameEn,
            icd10 = icd10,
            isChronic = (chronicCode != null),
            isNCD = ncdsFilter(icd10)
        ).apply {
            translation[Lang.th] = nameTh
        }

        return disease
    }

    fun ncdsFilter(icd10: String): Boolean {

        val ncdFilterList = arrayListOf<String>().apply {
            add("""^e10\.\d$""")
            add("""^e11\.\d$""")
            add("""^i10$""")
        }

        ncdFilterList.forEach {
            val pattern = Pattern.compile(it, Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(icd10)
            if (matcher.find()) return true
        }
        return false
    }
}