package com.example.pract_7_8.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.pract_7_8.domain.model.TodoItem

@Composable
fun TodoCreateDialog(
    showDialog: MutableState<Boolean>,
    onDismissRequest: () -> Unit,
    onConfirmRequest: (TodoItem) -> Unit
) {
    if (!showDialog.value) return

    val title = rememberSaveable { mutableStateOf("") }
    val description = rememberSaveable { mutableStateOf("") }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.padding(15.dp)
            ) {
                OutlinedTextField(
                    value = title.value,
                    onValueChange = { title.value = it },
                    label = { Text("Название задачи", color = Color.Gray) },
                    modifier = Modifier.weight(1f)
                )

                OutlinedTextField(
                    value = description.value,
                    onValueChange = { description.value = it },
                    label = { Text("Описание", color = Color.Gray) },
                    modifier = Modifier.weight(3f)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.weight(3f),
                        onClick = {
                            onConfirmRequest(
                                TodoItem(
                                    id = -1,
                                    title = title.value,
                                    description = description.value,
                                    isCompleted = false
                                )
                            )
                            showDialog.value = false
                        },
                        enabled = title.value.isNotBlank() && description.value.isNotBlank()
                    ) {
                        Text("Сохранить")
                    }

                    TextButton(
                        modifier = Modifier.weight(3f),
                        onClick = { showDialog.value = false }
                    ) {
                        Text("Отменить")
                    }
                }
            }
        }
    }
}


//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun TodoCreateDialogPreview(){
//    Pract_7_8Theme {
//        TodoCreateDialog(
//            true,
//            { },
//            { }
//        )
//    }
//}

