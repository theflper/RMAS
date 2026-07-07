package com.example.retrofitexample.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitexample.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UsersViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UsersUiState())
    val uiState: StateFlow<UsersUiState> = _uiState.asStateFlow()

    init {
        loadUsers()
    }

    fun loadUsers() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null,
                successMessage = null
            )

            try {
                val users = userRepository.getUsers()

                _uiState.value = UsersUiState(
                    isLoading = false,
                    users = users
                )
            } catch (exception: Exception) {
                _uiState.value = UsersUiState(
                    isLoading = false,
                    errorMessage = exception.message ?: "Failed to load users"
                )
            }
        }
    }

    fun searchUsers(query: String) {
        if (query.isBlank()) {
            loadUsers()
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null,
                successMessage = null
            )

            try {
                val users = userRepository.searchUsers(query)

                _uiState.value = UsersUiState(
                    isLoading = false,
                    users = users
                )
            } catch (exception: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = exception.message ?: "Failed to search users"
                )
            }
        }
    }

    fun createDemoUser() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null,
                successMessage = null
            )

            try {
                val createdUser = userRepository.createUser(
                    firstName = "Nikola",
                    lastName = "Demo",
                    age = 30,
                    email = "nikola.demo@example.com"
                )
                // REŠENJE ZA JEDINSTVEN ID:
                // Nalazimo najveći trenutni ID u našoj listi i dodajemo mu 1
                val maxId = _uiState.value.users.maxOfOrNull { it.id } ?: 0
                val uniqueId = maxId + 1

                // Pravimo kopiju korisnika sa servera, ali mu menjamo ID da bude jedinstven
                val finalUser = createdUser.copy(id = uniqueId)
                //REŠENJE: Uzmemo trenutnu listu i dodamo novog korisnika na kraj
                val currentUsers = _uiState.value.users
                val updatedUsers = currentUsers + finalUser
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    users = updatedUsers, // Prosledimo ažuriranu listu na ekran
                    successMessage = "Created user: ${createdUser.fullName}"
                )
            } catch (exception: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = exception.message ?: "Failed to create user"
                )
            }
        }
    }

    fun updateUser(id: Int) {
        viewModelScope.launch {
            val currentUser = _uiState.value.users.find { it.id == id }
            if (currentUser == null) return@launch // Ako ga nema, prekini

            val newAge = currentUser.age + 1
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null,
                successMessage = null
            )
            try {
                // Šaljemo nove godine na server (ostala polja ostaju null jer menjamo samo godine)
                val updatedUser = userRepository.updateUser(id = id, age = newAge)
                // Ažuriramo listu lokalno za prikaz na ekranu
                val updatedList = _uiState.value.users.map { existingUser ->
                    if (existingUser.id == id) updatedUser else existingUser
                }
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    users = updatedList,
                    successMessage = "Updated user: ${updatedUser.fullName}"
                )
            } catch (exception: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = exception.message ?: "Failed to update user"
                )
            }
        }
    }

    fun deleteUser(id: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null,
                successMessage = null
            )

            try {
                val deletedUser = userRepository.deleteUser(id)
                // REŠENJE: Uzimamo trenutnu listu iz stanja i izbacujemo obrisanog korisnika
                val currentUsers = _uiState.value.users
                val updatedUsers = currentUsers.filter { user -> user.id != id }
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    users = updatedUsers,//posto je javni API gde se podaci ne mogu brisati
                    successMessage = "Deleted user: ${deletedUser.fullName}"
                )
            } catch (exception: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = exception.message ?: "Failed to delete user"
                )
            }
        }
    }

    fun clearMessages() {
        _uiState.value = _uiState.value.copy(
            errorMessage = null,
            successMessage = null
        )
    }
}