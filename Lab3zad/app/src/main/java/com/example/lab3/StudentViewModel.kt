package com.example.lab3

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.lab3.data.Exam
import com.example.lab3.data.PassedExam
import com.example.lab3.data.Student
import com.example.lab3.data.Address

class StudentViewModel : ViewModel() {

    var students = mutableStateOf(
        listOf(
            Student(
                id = 1,
                studentNumber = "10443",
                firstName = "Marko",
                lastName = "Markovic",
                yearOfAdmission = 2023,
                passedExams = listOf(
                    PassedExam(
                        exam = Exam(1, "Matematika"),
                        grade = 9
                    ),
                    PassedExam(
                        exam = Exam(2, "Programiranje"),
                        grade = 10
                    )
                ),
                faculty="ELFAK NIS",
                address=Address("Nis",street="Bulevar Nemanjica","84/13")
            ),
            Student(
                id = 2,
                studentNumber = "10458",
                firstName = "Ana",
                lastName = "Jovanovic",
                yearOfAdmission = 2023,
                passedExams = emptyList(),
                faculty="ETF BG",
                address=Address("Beograd",street="Dragice Pravice",number="bb")
            )
        )
    )
        private set

    var exams = mutableStateOf(
        listOf(
            Exam(1, "Matematika"),
            Exam(2, "Programiranje"),
            Exam(3, "Baze podataka")
        )
    )
        private set
    var hobys = mutableStateOf(
        value= listOf(
            "Kafa",
            "Zurke",
            "Teretana"
        )
    )
    fun setHoby(hoby:String)
    {
        selectedStudent.value?.hoby=hoby;
    }
    fun addHoby(hoby:String)
    {
        hobys.value=hobys.value+hoby//dodavanje u listu
    }
    var selectedStudent = mutableStateOf<Student?>(null)
        private set

    private var nextStudentId = 3
    private var nextExamId = 4

    fun addStudent(
        studentNumber: String,
        firstName: String,
        lastName: String,
        yearOfAdmission: Int,
        faculty: String,
        city: String,
        street:String,
        number:String
    ) {
        val newStudent = Student(
            id = nextStudentId,
            studentNumber = studentNumber,
            firstName = firstName,
            lastName = lastName,
            yearOfAdmission = yearOfAdmission,
            faculty=faculty,
            address=Address(city,street,number)
        )

        nextStudentId++

        students.value = students.value + newStudent
    }

    fun selectStudent(student: Student) {
        selectedStudent.value = student
    }
    //TODO: Add AddExams function
    fun addExam(name: String) {
        val newExam = Exam(
            id = nextExamId,
            name = name
        )
        nextExamId++
        exams.value = exams.value + newExam
    }
    //ovo je jako bitno!!!
    fun addPassedExamToSelectedStudent(
        exam: Exam,
        grade: Int
    ) {
        val currentStudent = selectedStudent.value ?: return

        val updatedStudent = currentStudent.copy(
            passedExams = currentStudent.passedExams + PassedExam(
                exam = exam,
                grade = grade
            )
        )

        students.value = students.value.map { student ->
            if (student.id == updatedStudent.id) {
                updatedStudent
            } else {
                student
            }
        }

        selectedStudent.value = updatedStudent
    }
}