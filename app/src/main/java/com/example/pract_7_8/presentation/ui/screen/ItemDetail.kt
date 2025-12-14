package com.example.pract_7_8.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pract_7_8.R
import com.example.pract_7_8.domain.model.TodoItem
import com.example.pract_7_8.presentation.viewmodel.TodolistViewModel
import com.example.pract_7_8.ui.theme.Pract_7_8Theme

@Composable
fun ItemDetail(
    viewModel: TodolistViewModel,
    itemId: Int,
    onBackClick: () -> Unit,
    modifier: Modifier
) {
    val todos by viewModel.todos.observeAsState(emptyList())
    val item = todos.find { it.id == itemId } ?: TodoItem(-1, "Ошибка", " ", false)
    Column (
        modifier = Modifier.testTag("detailScreen")
    ){
        TopBarDetailScreen( onBackClick )
        Column (
            modifier = modifier.padding(horizontal = 25.dp)
        ) {
            Text(
                text = item.title,
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.padding(vertical = 2.5.dp))
            Text(
                text = item.description,
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.padding(vertical = 5.dp))
            Text(
                text = if (item.isCompleted)
                    stringResource(R.string.todoItemDone_true)
                else
                    stringResource(R.string.todoItemDone_false),
                color = if (item.isCompleted)
                    Color.Green
                else
                    Color.Red,
                fontSize = 22.sp
            )
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ItemDetailPreview(){
    Pract_7_8Theme {
        ItemDetail(
            viewModel(),
            -1,
            { },
            Modifier
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarDetailScreen(
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = {  },
        navigationIcon = {
            IconButton(
                modifier = Modifier.testTag("arrowBackButtonInDetailScreen"),
                onClick = onBackClick) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = "Назад"
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewMyTopAppBar() {
    Pract_7_8Theme {
        TopBarDetailScreen(
            onBackClick = {  }
        )
    }
}