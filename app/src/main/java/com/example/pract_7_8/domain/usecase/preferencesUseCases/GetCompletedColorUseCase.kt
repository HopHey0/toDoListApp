package com.example.pract_7_8.domain.usecase.preferencesUseCases

import com.example.pract_7_8.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow

class GetCompletedColorUseCase(
    private val repository: UserPreferencesRepository
) {
    operator fun invoke(): Flow<Boolean> = repository.isCompletedGreen
}