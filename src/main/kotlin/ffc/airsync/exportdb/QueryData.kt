package ffc.airsync.exportdb

import ffc.entity.healthcare.Disease

interface QueryData {
    fun getDisease(): List<Disease>
}