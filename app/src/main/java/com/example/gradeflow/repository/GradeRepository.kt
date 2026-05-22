package com.example.gradeflow.repository

import com.example.gradeflow.data.FirebaseService
import com.example.gradeflow.model.Grade
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class GradeRepository {

    private val database = FirebaseService.database.reference
    private val auth = FirebaseService.auth

    private fun userGradesRef() =
        database.child("users")
            .child(auth.currentUser?.uid ?: throw Exception("Utilisateur non connecté"))
            .child("grades")

    suspend fun addGrade(grade: Grade): Result<Unit> {
        return try {
            val id = userGradesRef().push().key
                ?: return Result.failure(Exception("Impossible de générer un ID"))

            val newGrade = grade.copy(id = id)
            userGradesRef().child(id).setValue(newGrade).await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateGrade(grade: Grade): Result<Unit> {
        return try {
            userGradesRef().child(grade.id).setValue(grade).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteGrade(id: String): Result<Unit> {
        return try {
            userGradesRef().child(id).removeValue().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun observeGrades() = callbackFlow<List<Grade>> {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val grades = snapshot.children.mapNotNull {
                    it.getValue(Grade::class.java)
                }
                trySend(grades)
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(emptyList())
            }
        }

        userGradesRef().addValueEventListener(listener)

        awaitClose {
            userGradesRef().removeEventListener(listener)
        }
    }
}