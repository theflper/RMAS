package com.example.lab3.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab3.screens.addexam.AddExamScreen
import com.example.lab3.screens.addstudent.AddStudentScreen
import com.example.lab3.screens.addstudentpassedexam.AddStudentPassedExamScreen
import com.example.lab3.screens.studentdetails.StudentDetailsScreen
import com.example.lab3.screens.studentlist.StudentListScreen
import com.example.lab3.StudentViewModel

@Composable
fun StudentNavHost(
    studentViewModel: StudentViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.STUDENT_LIST
    ) {
        composable(Routes.STUDENT_LIST) {
            StudentListScreen(
                students = studentViewModel.students.value,
                onAddStudentClick = {
                    navController.navigate(Routes.ADD_STUDENT)
                },
                // Izmenjen Toast u pravu navigaciju ka ekranu za dodavanje ispita
                onAddExamClick = {
                    navController.navigate(Routes.ADD_EXAM)
                },
                onStudentNameClick = { student ->
                    studentViewModel.selectStudent(student)
                    navController.navigate(Routes.STUDENT_DETAILS)
                }
            )
        }

        composable(Routes.ADD_STUDENT) {
            AddStudentScreen(
                onSaveStudent = { studentNumber, firstName, lastName, yearOfAdmission,faculty,city,street,number ->
                    studentViewModel.addStudent(
                        studentNumber = studentNumber,
                        firstName = firstName,
                        lastName = lastName,
                        yearOfAdmission = yearOfAdmission,
                        faculty=faculty,
                        city=city,
                        street=street,
                        number=number
                    )
                    navController.popBackStack()
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.STUDENT_DETAILS) {
            StudentDetailsScreen(
                student = studentViewModel.selectedStudent.value,
                onAddPassedExamClick = {
                    navController.navigate(Routes.ADD_STUDENT_PASSED_EXAM)
                },
                onBackClick = {
                    navController.popBackStack(
                        route = Routes.STUDENT_LIST,//gde se vracamo
                        inclusive = false//ako je ovo true unistava se i taj ekran
                        //ako je false kao ovde tad se zapravo vracamo na taj ekran
                    )
                }
            )
        }

        composable(Routes.ADD_STUDENT_PASSED_EXAM) {
            // 1. Uzimamo selektovanog studenta iz ViewModel-a
            val currentStudent = studentViewModel.selectedStudent.value

            // 2. Iz liste položenih ispita izvlačimo samo 'exam' objekte.
            // Ako je student null, prosleđujemo praznu listu.
            val alreadyPassed = currentStudent?.passedExams?.map { it.exam } ?: emptyList()

            AddStudentPassedExamScreen(
                exams = studentViewModel.exams.value,
                alreadyPassedExams = alreadyPassed, // <-- Prosleđena filtrirana lista
                onSavePassedExam = { exam, grade ->
                    studentViewModel.addPassedExamToSelectedStudent(
                        exam = exam,
                        grade = grade
                    )
                    navController.popBackStack()
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // Dodata čista ruta za AddExamScreen
        composable(Routes.ADD_EXAM) {
            AddExamScreen(
                // Prosleđujemo listu ispita iz ViewModel-a
                exams = studentViewModel.exams.value,
                // Pozivamo funkciju za dodavanje novog ispita
                onSaveExam = { examName ->
                    studentViewModel.addExam(examName)
                    // Napomena: Ne stavljamo popBackStack() ovde ako želiš da korisnik
                    // ostane na ekranu i vidi kako se ispit dodao u "Added exams" listu ispod!
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}