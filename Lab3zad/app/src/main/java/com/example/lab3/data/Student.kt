package com.example.lab3.data


data class Student(
    val id: Int,
    val studentNumber: String,
    val firstName: String,
    val lastName: String,
    val yearOfAdmission: Int,
    val passedExams: List<PassedExam> = emptyList(),
    val faculty:String="",//dodamo fakultet
    //dodata default vrednost da ne bi menjao parametre u konstruktoru u svim fajlovima
    val address: Address?=Address(),
    val hoby: String = "no hoby"
){
    val averageGrade: Double
        get() = if (passedExams.isEmpty()) {
            0.0
        } else {
            passedExams.map { it.grade }.average()
        }

}