package com.example.pract_7_8.domain.usecase.preferencesUseCases

import com.example.pract_7_8.domain.repository.UserPreferencesRepository

class SetCompletedColorUseCase(
    private val repository: UserPreferencesRepository
) {
    suspend operator fun invoke(isGreen: Boolean) = repository.setCompletedColorIsGreen(isGreen)
}