package com.example.lab2.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.lab2.data.Student

class StudentViewModel : ViewModel() {
    //osnovni podatci
    var students by mutableStateOf(
        listOf(
            Student("10458", "Petar", "Petrovic", avgGrade = 9.24, yearOfAdmission = 2023),
            Student("10568", "Jelena", "Jovanovic", avgGrade = 8.67, yearOfAdmission = 2024)
        )
    )
        private set // Spoljne klase ne mogu direktno da menjaju listu, već moraju preko funkcije ispod

    var selectedStudent by mutableStateOf<Student?>(null)
    //dodaj studenta u listu
    fun addStudent(student: Student) {
        students = students + student
    }
    //nabavi selektovanog studenta
    fun selectStudent(student: Student) {
        selectedStudent = student
    }
}