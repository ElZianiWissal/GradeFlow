package com.example.gradeflow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gradeflow.model.Grade
import com.example.gradeflow.repository.GradeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GradeViewModel : ViewModel() {

    private val repository = GradeRepository()

    private val _grades =
        MutableStateFlow<List<Grade>>(emptyList())

    val grades: StateFlow<List<Grade>> = _grades

    private val _error =
        MutableStateFlow<String?>(null)

    val error: StateFlow<String?> = _error

    val average: Double
        get() {

            val totalCoef =
                _grades.value.sumOf {
                    it.coefficient
                }

            if (totalCoef == 0.0) {
                return 0.0
            }

            return _grades.value.sumOf {
                it.score * it.coefficient
            } / totalCoef
        }


    fun observeGrades() {

        viewModelScope.launch {

            repository.observeGrades()
                .collect { gradeList ->

                    _grades.value = gradeList
                }
        }
    }

    fun addGrade(
        grade: Grade,
        onSuccess: () -> Unit
    ) {

        viewModelScope.launch {

            val result =
                repository.addGrade(grade)

            result.onSuccess {

                onSuccess()

            }.onFailure {

                _error.value = it.message
            }
        }
    }

    fun updateGrade(
        grade: Grade,
        onSuccess: () -> Unit
    ) {

        viewModelScope.launch {

            val result =
                repository.updateGrade(grade)

            result.onSuccess {

                onSuccess()

            }.onFailure {

                _error.value = it.message
            }
        }
    }

    fun deleteGrade(id: String) {

        viewModelScope.launch {

            repository.deleteGrade(id)
        }
    }
}