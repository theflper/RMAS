package com.example.retrofitexample.users.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.retrofitexample.model.User

@Composable
fun UsersList(
    users: List<User>,
    onUpdateUser: (Int) -> Unit,
    onDeleteUser: (Int) -> Unit
) {
    if (users.isEmpty()) {
        Text(
            text = "No users found.",
            style = MaterialTheme.typography.bodyLarge
        )
        return
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = users,
            key = { user -> user.id }
        ) { user ->
            UserItem(
                user = user,
                onUpdateUser = {
                    onUpdateUser(user.id)
                },
                onDeleteUser = {
                    onDeleteUser(user.id)
                }
            )
        }
    }
}