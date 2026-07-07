package com.example.lab4.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab4.StudentViewModel
import com.example.lab4.screens.addexam.AddExamScreen
import com.example.lab4.screens.addstudent.AddStudentScreen
import com.example.lab4.screens.addstudentpassedexam.AddStudentPassedExamScreen
import com.example.lab4.screens.studentdetails.StudentDetailsScreen
import com.example.lab4.screens.studentlist.StudentListScreen

@Composable
fun StudentNavHost(
    studentViewModel: StudentViewModel
) {
    val navController = rememberNavController()

    val students by studentViewModel.students.collectAsState()
    val exams by studentViewModel.exams.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Routes.STUDENT_LIST
    ) {
        composable(Routes.STUDENT_LIST) {
            StudentListScreen(
                students = students,
                onAddStudentClick = {
                    navController.navigate(Routes.ADD_STUDENT)
                },
                onAddExamClick = {
                    navController.navigate(Routes.ADD_EXAM)
                },
                onStudentCardClick = { student ->
                    studentViewModel.selectStudent(student)
                    navController.navigate(Routes.STUDENT_DETAILS)
                }
            )
        }

        composable(Routes.ADD_STUDENT) {
            AddStudentScreen(
                onSaveStudent = { studentNumber, firstName, lastName, yearOfAdmission ->
                    studentViewModel.addStudent(
                        studentNumber = studentNumber,
                        firstName = firstName,
                        lastName = lastName,
                        yearOfAdmission = yearOfAdmission
                    )
                    navController.popBackStack()
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.ADD_EXAM) {
            AddExamScreen(
                exams = exams,
                onSaveExam = { examName ->
                    //TODO: add exam properly
                    studentViewModel.addExam(name = examName)
                    navController.popBackStack()
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.STUDENT_DETAILS) {
            val selectedStudentId = studentViewModel.selectedStudentId.value

            val selectedStudent = students.find { student ->
                student.id == selectedStudentId
            }

            StudentDetailsScreen(
                student = selectedStudent,
                onAddPassedExamClick = {
                    navController.navigate(Routes.ADD_STUDENT_PASSED_EXAM)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.ADD_STUDENT_PASSED_EXAM) {
            AddStudentPassedExamScreen(
                exams = exams,
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
    }
}