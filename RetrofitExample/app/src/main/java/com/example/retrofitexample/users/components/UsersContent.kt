package com.example.retrofitexample.users.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.retrofitexample.users.UsersUiState

@Composable
fun UsersContent(
    uiState: UsersUiState,
    onSearch: (String) -> Unit,
    onRefresh: () -> Unit,
    onCreateUser: () -> Unit,
    onUpdateUser: (Int) -> Unit,
    onDeleteUser: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SearchSection(
            searchQuery = searchQuery,
            onSearchQueryChange = { newValue ->
                searchQuery = newValue
            },
            onSearch = {
                onSearch(searchQuery)
            },
            onRefresh = {
                // POPRAVKA: Kada osvežavamo listu, čistimo i tekstualno polje!
                searchQuery = ""
                onRefresh()
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        DemoActionsSection(
            // Ovde se okida kreiranje ("Nikole")
            onCreateUser = onCreateUser
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (uiState.isLoading) {
            LoadingSection()
        } else {
            // LazyColumn sa korisnicima
            UsersList(
                users = uiState.users,
                onUpdateUser = onUpdateUser,
                onDeleteUser = onDeleteUser
            )
        }
    }
}