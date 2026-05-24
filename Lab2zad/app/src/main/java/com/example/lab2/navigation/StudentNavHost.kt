package com.example.lab2.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab2.data.Student
import com.example.lab2.screens.addstudent.AddStudentScreen
import com.example.lab2.screens.studentlist.StudentListScreen
import androidx.lifecycle.viewmodel.compose.viewModel // <--- DODAJ OVAJ UVOZ
import com.example.lab2.screens.studentDetails.StudentDetailsScreen
import com.example.lab2.viewModel.StudentViewModel

@Composable
fun StudentNavHost(studentViewModel: StudentViewModel = viewModel()
// <--- Inicijalizacija ViewModel-a u funkciji
) {
    val navController = rememberNavController()
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = Routes.STUDENT_LIST
    ) {
        //vezujemo STUDENT_LIST za StudentListScreen koji je unutar composable
        composable(Routes.STUDENT_LIST) {
            StudentListScreen(
                students = studentViewModel.students,
                onAddClick = {
                    navController.navigate(Routes.ADD_STUDENT)
                },
                onStudentClick = { student ->
                    studentViewModel.selectStudent(student) // Upisuje u ViewModel da je on selektovan
                    //Toast.makeText(context, selectedStudent.toString(), Toast.LENGTH_LONG).show()
                    //TODO:Navigate to student details
                    //kazemo na klik ovog dugmeta odi na student details
                    navController.navigate(Routes.STUDENT_DETAILS)
                }
            )
        }
        composable(Routes.STUDENT_DETAILS)
        {
            // Uzimamo selektovanog studenta iz ViewModel-a
            val student = studentViewModel.selectedStudent
            //mora ovako jer je selectedStudent nullable
            if (student != null) {
                StudentDetailsScreen(
                    student = student,
                    onCancel = {
                        navController.popBackStack()
                    }
                )
            } else {
                // Ako je iz nekog razloga null, samo vrati korisnika nazad
                navController.popBackStack()
            }
        }
        composable(Routes.ADD_STUDENT) {
            AddStudentScreen(
                onSaveStudent = { student ->
                    studentViewModel.addStudent(student) // Dodaje u ViewModel
                    navController.popBackStack()
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }
    }
}