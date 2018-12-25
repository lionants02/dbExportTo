package ffc.airsync.exportdb

import ffc.entity.Template
import ffc.entity.healthcare.CommunityService.ServiceType
import ffc.entity.healthcare.Disease
import ffc.entity.healthcare.SpecialPP.PPType

interface QueryData {
    fun getDisease(): List<Disease>
    fun getHomeHealthType(): List<ServiceType>
    fun getSpecialPpType(): List<PPType>
    fun getTemplate(): List<Template>
}
