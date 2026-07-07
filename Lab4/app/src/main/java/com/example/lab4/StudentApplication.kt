package com.example.lab4

import android.app.Application
import com.example.lab4.data.local.StudentDatabase
import com.example.lab4.data.repository.StudentRepository

class StudentApplication : Application() {

    private val database by lazy {
        StudentDatabase.getDatabase(this)
    }

    val repository by lazy {
        StudentRepository(
            studentDao = database.studentDao(),
            examDao = database.examDao(),
            studentExamDao = database.studentExamDao()
        )
    }
}