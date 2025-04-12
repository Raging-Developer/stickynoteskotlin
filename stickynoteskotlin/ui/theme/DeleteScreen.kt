package app.stickynoteskotlin.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.stickynoteskotlin.MainActivity.Companion.font_name
import app.stickynoteskotlin.MainActivity.Companion.font_size
import app.stickynoteskotlin.R
import app.stickynoteskotlin.stickyNoteView

@Composable
fun deleteScreen(note: String?){
    var isClicked = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val note_text : String = note.toString()
    var myFont = FontFamily(Font(R.font.indieflower))
    if (font_name == "Note_this.ttf") {
        myFont = FontFamily(Font(R.font.note_this))
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(all = 30.dp).background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "One to delete, maybe",
            color = Color.Green,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            enabled = false,
            value = note_text,
            onValueChange = { note_text },
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = font_size!!.sp,
                fontFamily = myFont))

        Spacer(modifier = Modifier.height(15.dp))
    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter) {
        Button(onClick = { isClicked.value = true})
        { Text(text = "Do not delete, go back", color = Color.White) }
        if (isClicked.value) {
            stickyNoteView(context, ComposeView(context))
        }
    }
}
