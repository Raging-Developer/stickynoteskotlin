package app.stickynoteskotlin.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.stickynoteskotlin.MainActivity.Companion.font_name
import app.stickynoteskotlin.MainActivity.Companion.font_size
import app.stickynoteskotlin.R
import app.stickynoteskotlin.updateCourse

@Composable
fun editScreen(desc: String?, row_id: Long?){
    val context = LocalContext.current
    val note_text = remember {
        mutableStateOf(TextFieldValue()).apply { value = TextFieldValue(desc.toString()) }
    }
    var myFont = FontFamily(Font(R.font.indieflower))

    if (font_name == "Note_this.ttf") {
        myFont = FontFamily(Font(R.font.note_this))
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(all = 30.dp).background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        TextField(
            value = note_text.value,
            onValueChange = { note_text.value = it },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = font_size!!.sp, fontFamily = myFont)
        )
    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter) {
        Button(onClick = {
            updateCourse(
                context,
                note_text.value.text,
                row_id
            )
        }) { Text(text = "update", color = Color.White) }
    }
}
