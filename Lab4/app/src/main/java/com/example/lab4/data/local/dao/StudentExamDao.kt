package com.example.lab4.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lab4.data.local.entity.StudentExamCrossRef
import kotlinx.coroutines.flow.Flow

data class PassedExamDbRow(
    val studentId: Int,
    val examId: Int,
    val examName: String,
    val grade: Int
)

@Dao
interface StudentExamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPassedExam(crossRef: StudentExamCrossRef)

    @Query(
        """
        SELECT 
            student_exam_cross_ref.studentId AS studentId,
            exams.id AS examId,
            exams.name AS examName,
            student_exam_cross_ref.grade AS grade
        FROM student_exam_cross_ref
        INNER JOIN exams ON student_exam_cross_ref.examId = exams.id
        ORDER BY student_exam_cross_ref.studentId ASC
        """
    )
    fun getAllPassedExamRows(): Flow<List<PassedExamDbRow>>
}