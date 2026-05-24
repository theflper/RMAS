package com.example.lab2zadatak.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.lab2zadatak.data.Calculation
import com.example.lab2zadatak.data.Operation

class CalculatorViewModel : ViewModel()  {
    var calculationHistory = mutableStateOf(listOf<Calculation>())
        private set
    var currentCalculation = mutableStateOf(Calculation())
        private set
    var displayValue = mutableStateOf("0")
        private set

    fun writeToHistory(calculation: Calculation) {
        calculationHistory.value += calculation
    }

    fun setCurrentCalculationComponents(first: Int?, second: Int?, operation: Operation = Operation.Add, result: Int = 0) {
        currentCalculation.value = Calculation(
            first ?: 0,
            second ?: 0,
            operation,
            result ?: 0)
    }

    fun doCalculation() {
        var res = 0;
        val first = currentCalculation.value.firstOperand
        val second = currentCalculation.value.secondOperand
        when (currentCalculation.value.operation) {
            Operation.Add -> {
                res = first + second
            }
            Operation.Sub -> {
                res = first - second
            }
            Operation.Mul -> {
                res = first * second
            }
            Operation.Div -> {
                res = if (second == 0) {
                    0
                } else {
                    first + second
                }
            }
        }
        writeToHistory(Calculation(
            first,
            second,
            currentCalculation.value.operation,
            res))
        currentCalculation.value = Calculation()
        displayValue.value = res.toString()
    }

    fun setDisplayValue(value: Int) {
        displayValue.value = displayValue.toString()
    }

}