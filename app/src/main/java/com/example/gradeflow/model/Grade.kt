package com.example.gradeflow.model

data class Grade(
    val id: String = "",
    val subject: String = "",
    val score: Double = 0.0,
    val coefficient: Double = 1.0,
    val comment: String = "",
    val createdAt: Long = System.currentTimeMillis()
)