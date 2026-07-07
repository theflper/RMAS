package com.example.lab4.data.repository

import com.example.lab4.data.Exam
import com.example.lab4.data.PassedExam
import com.example.lab4.data.Student
import com.example.lab4.data.local.dao.ExamDao
import com.example.lab4.data.local.dao.StudentDao
import com.example.lab4.data.local.dao.StudentExamDao
import com.example.lab4.data.local.entity.ExamEntity
import com.example.lab4.data.local.entity.StudentEntity
import com.example.lab4.data.local.entity.StudentExamCrossRef
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class StudentRepository(
    private val studentDao: StudentDao,
    private val examDao: ExamDao,
    private val studentExamDao: StudentExamDao
) {
    val exams: Flow<List<Exam>> =
        examDao.getAllExams().map { examEntities ->
            examEntities.map { examEntity ->
                Exam(
                    id = examEntity.id,
                    name = examEntity.name
                )
            }
        }

    val students: Flow<List<Student>> =
        combine(
            studentDao.getAllStudents(),
            studentExamDao.getAllPassedExamRows()
        ) { studentEntities, passedExamRows ->
            studentEntities.map { studentEntity ->
                val passedExams = passedExamRows
                    .filter { row ->
                        row.studentId == studentEntity.id
                    }
                    .map { row ->
                        PassedExam(
                            exam = Exam(
                                id = row.examId,
                                name = row.examName
                            ),
                            grade = row.grade
                        )
                    }

                Student(
                    id = studentEntity.id,
                    studentNumber = studentEntity.studentNumber,
                    firstName = studentEntity.firstName,
                    lastName = studentEntity.lastName,
                    yearOfAdmission = studentEntity.yearOfAdmission,
                    passedExams = passedExams
                )
            }
        }

    suspend fun seedInitialDataIfNeeded() {
        val studentCount = studentDao.getStudentCount()
        val examCount = examDao.getExamCount()

        if (studentCount == 0 && examCount == 0) {

            //TODO:Uncomment when insertExam function is implemented
            val mathId = examDao.insertExam(
                ExamEntity(name = "Matematika")
            ).toInt()

            val programmingId = examDao.insertExam(
                ExamEntity(name = "Programiranje")
            ).toInt()

            examDao.insertExam(
                ExamEntity(name = "Baze podataka")
            )

            val markoId = studentDao.insertStudent(
                StudentEntity(
                    studentNumber = "2023001",
                    firstName = "Marko",
                    lastName = "Markovic",
                    yearOfAdmission = 2023
                )
            ).toInt()

            studentDao.insertStudent(
                StudentEntity(
                    studentNumber = "2023002",
                    firstName = "Ana",
                    lastName = "Jovanovic",
                    yearOfAdmission = 2023
                )
            )
            //TODO:Uncomment when insertExam function is implemented

            studentExamDao.insertPassedExam(
                StudentExamCrossRef(
                    studentId = markoId,
                    examId = mathId,
                    grade = 9
                )
            )

            studentExamDao.insertPassedExam(
                StudentExamCrossRef(
                    studentId = markoId,
                    examId = programmingId,
                    grade = 10
                )
            )
        }
    }

    suspend fun addStudent(
        studentNumber: String,
        firstName: String,
        lastName: String,
        yearOfAdmission: Int
    ) {
        studentDao.insertStudent(
            StudentEntity(
                studentNumber = studentNumber,
                firstName = firstName,
                lastName = lastName,
                yearOfAdmission = yearOfAdmission
            )
        )
    }

    //TODO:Implement addExam function
    suspend fun addExam(name: String) {
        examDao.insertExam(
            ExamEntity(name = name)
        )
    }
    suspend fun addPassedExam(
        studentId: Int,
        examId: Int,
        grade: Int
    ) {
        studentExamDao.insertPassedExam(
            StudentExamCrossRef(
                studentId = studentId,
                examId = examId,
                grade = grade
            )
        )
    }
}