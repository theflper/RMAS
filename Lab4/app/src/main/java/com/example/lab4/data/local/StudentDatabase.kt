package com.example.lab4.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lab4.data.local.dao.ExamDao
import com.example.lab4.data.local.dao.StudentDao
import com.example.lab4.data.local.dao.StudentExamDao
import com.example.lab4.data.local.entity.ExamEntity
import com.example.lab4.data.local.entity.StudentEntity
import com.example.lab4.data.local.entity.StudentExamCrossRef

@Database(
    entities = [
        StudentEntity::class,
        ExamEntity::class,
        StudentExamCrossRef::class
    ],
    //TODO:Update version when the database should be regenerated
    version = 1,
    exportSchema = true
)
abstract class StudentDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao
    abstract fun examDao(): ExamDao
    abstract fun studentExamDao(): StudentExamDao

    companion object {
        @Volatile
        private var INSTANCE: StudentDatabase? = null
        fun getDatabase(context: Context): StudentDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StudentDatabase::class.java,
                    "student_database"
                )
                    .fallbackToDestructiveMigration(true)
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}