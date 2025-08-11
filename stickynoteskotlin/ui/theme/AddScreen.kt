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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import app.stickynoteskotlin.MainActivity.Companion.font_name
//import app.stickynoteskotlin.MainActivity.Companion.font_size
import app.stickynoteskotlin.R
import app.stickynoteskotlin.addNewNote

@Composable
fun addScreen(font_name: String, font_size: Int) {
    var context = LocalContext.current
    var title = remember {mutableStateOf(TextFieldValue())}
    val note_text = remember {mutableStateOf(TextFieldValue())}
    val myFont = remember(font_name) {
        if (font_name == "Note_this.ttf") {
            FontFamily(Font(R.font.note_this))
        } else {
            FontFamily(Font(R.font.indieflower))
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(all = 30.dp).background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        Text(
            text = "SQLite Database, maybe",
            color = Color.Green,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            enabled = false,
            value = title.value,
            onValueChange = { title.value = it },
            placeholder = { Text(text = "Title: (This will be time and date)",
                color = Color.Black) },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black),
            textStyle = TextStyle(color = Color.White, fontSize = 15.sp),
            singleLine = false)

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = note_text.value,
            onValueChange = { note_text.value = it },
            placeholder = { Text(text = "Enter a note here",
                color = Color.White,
                fontFamily = myFont,
                fontSize = font_size!!.sp) },
            modifier = Modifier.fillMaxWidth()
                .background(Color.Black),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Black,
                unfocusedContainerColor = Color.Black),
            textStyle = TextStyle(
                color = Color.White,
                fontSize = font_size!!.sp,
                fontFamily = myFont),
            singleLine = false)

        Spacer(modifier = Modifier.height(15.dp))
    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter) {
        Button(onClick = {
            addNewNote(context,
                note_text.value.text)})
        { Text(text = "Add", color = Color.White) }
    }
}



