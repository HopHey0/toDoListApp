package com.example.pract_7_8.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    val isCompletedGreen: Flow<Boolean>
    suspend fun setCompletedColorIsGreen(isGreen: Boolean)
}