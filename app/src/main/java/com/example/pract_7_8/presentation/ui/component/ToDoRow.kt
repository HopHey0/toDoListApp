package com.example.pract_7_8.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pract_7_8.R
import com.example.pract_7_8.domain.model.TodoItem
import com.example.pract_7_8.presentation.viewmodel.TodolistViewModel

@Composable
fun ToDoRow(
    item: TodoItem,
    onClick: () -> Unit,
    onChecked: () -> Unit,
    todolistViewModel: TodolistViewModel
){
    Row (
        modifier = Modifier.fillMaxWidth()
            .clip(shape = RoundedCornerShape(15.dp))
            .clickable(onClick = onClick)
            .background(Color.LightGray)
            .testTag("ClickableTodosOnListScreen${item.id}"),
        verticalAlignment = Alignment.CenterVertically
    ){
        Checkbox(
            modifier = Modifier.padding(horizontal = 25.dp)
                .testTag("RowCheckBox${item.id}")
                .weight(1f),
            checked = item.isCompleted,
            onCheckedChange = {onChecked()}
        )
        Column (
            modifier = Modifier.weight(3f)
                .padding(vertical = 5.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = item.title,
                fontSize = 28.sp,
                textDecoration = if (item.isCompleted) TextDecoration.LineThrough else TextDecoration.None
            )
            Text(
                text = item.description,
                fontSize = 14.sp,
                color = Color.Gray,
                textDecoration = if (item.isCompleted) TextDecoration.LineThrough else TextDecoration.None

            )
        }
        IconButton(
            modifier = Modifier.padding(25.dp),

            onClick = { todolistViewModel.deleteTodo(item.id) }) {
            Icon(
                painter = painterResource(R.drawable.delete_todo),
                contentDescription = "Создать запись"
            )
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ToDoRowPreview(){
//    Pract_7_8Theme {
//        ToDoRow(TodoItem(1, "Sample", "Example", true), {}, {})
//    }
//}