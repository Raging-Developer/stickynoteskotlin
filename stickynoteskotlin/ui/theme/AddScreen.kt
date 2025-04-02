package app.stickynoteskotlin.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.stickynoteskotlin.addingTheNewNote

@Composable
fun addScreen() {
    var context = LocalContext.current
    val title = remember {
        mutableStateOf(TextFieldValue())
    }
    val note_text = remember {
        mutableStateOf(TextFieldValue())
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(all = 30.dp).background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "SQLite Database, maybe",
            color = Color.Green,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = title.value,
            onValueChange = { title.value = it },
            placeholder = { Text(text = "This will be time and date") },//this is time and date
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = note_text.value,
            onValueChange = { note_text.value = it },
            placeholder = { Text(text = "Enter a note") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(15.dp))
    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter) {
        Button(onClick = {
            addingTheNewNote(
                context,
                title.value.text,
                note_text.value.text
            )
        }) { Text(text = "Add", color = Color.White) }
    }
}

