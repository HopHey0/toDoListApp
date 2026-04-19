package com.example.pract_7_8.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun TodoCreateDialog(
    showDialog: Boolean,
    todoDialogHeader: String,
    todoDialogBody: String,
    onConfirmRequest: (String, String) -> Unit,
    onHeaderChange: (String) -> Unit,
    onBodyChange: (String) -> Unit,
    onDialogDismiss: () -> Unit
) {
    if (!showDialog) return

    Dialog(
        onDismissRequest = { onDialogDismiss() }
    ) {
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
                    value = todoDialogHeader,
                    onValueChange = { onHeaderChange(it) },
                    label = { Text("Название задачи", color = Color.Gray) },
                    modifier = Modifier.weight(1f)
                )

                OutlinedTextField(
                    value = todoDialogBody,
                    onValueChange = { onBodyChange(it) },
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
                            onConfirmRequest(todoDialogHeader, todoDialogBody)
                            onDialogDismiss()
                        },
                        enabled = todoDialogHeader.isNotBlank() && todoDialogBody.isNotBlank()
                    ) {
                        Text("Сохранить")
                    }

                    TextButton(
                        modifier = Modifier.weight(3f),
                        onClick = { onDialogDismiss() }
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

