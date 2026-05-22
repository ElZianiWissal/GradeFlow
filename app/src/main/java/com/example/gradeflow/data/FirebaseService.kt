package com.example.gradeflow.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

object FirebaseService {

    val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    val database: FirebaseDatabase by lazy {
        FirebaseDatabase.getInstance()
    }
}