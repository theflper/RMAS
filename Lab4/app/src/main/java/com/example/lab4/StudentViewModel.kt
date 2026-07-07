package com.example.lab4

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab4.data.Exam
import com.example.lab4.data.Student
import com.example.lab4.data.repository.StudentRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StudentViewModel(
    private val repository: StudentRepository
) : ViewModel() {

    val students = repository.students.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val exams = repository.exams.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    var selectedStudentId = mutableStateOf<Int?>(null)
        private set

    //TODO:Uncomment init function when the database is ready
    init {
        viewModelScope.launch {
            repository.seedInitialDataIfNeeded()
        }
    }

    fun selectStudent(student: Student) {
        selectedStudentId.value = student.id
    }

    fun addStudent(
        studentNumber: String,
        firstName: String,
        lastName: String,
        yearOfAdmission: Int
    ) {
        viewModelScope.launch {
            repository.addStudent(
                studentNumber = studentNumber,
                firstName = firstName,
                lastName = lastName,
                yearOfAdmission = yearOfAdmission
            )
        }
    }

    //TODO:Implement addExam function
    fun addExam(name: String) {
        viewModelScope.launch {
            repository.addExam(name = name)
        }
    }
    fun addPassedExamToSelectedStudent(
        exam: Exam,
        grade: Int
    ) {
        val studentId = selectedStudentId.value ?: return

        viewModelScope.launch {
            repository.addPassedExam(
                studentId = studentId,
                examId = exam.id,
                grade = grade
            )
        }
    }
}