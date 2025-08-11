package app.stickynoteskotlin.ui.theme

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.stickynoteskotlin.MainActivity
import app.stickynoteskotlin.R
import app.stickynoteskotlin.updateNote
import com.google.android.material.bottomappbar.BottomAppBar

@Composable
fun editScreen(desc: String?, row_id: Long?, font_name: String, font_size: Int){

    val context = LocalContext.current
    val note_text = remember {
        mutableStateOf(TextFieldValue())
            .apply { value = TextFieldValue(desc.toString()) }}
    val myFont = remember(font_name) {
        if (font_name == "Note_this.ttf") {
            FontFamily(Font(R.font.note_this))
        } else {
            FontFamily(Font(R.font.indieflower))
        }
    }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 0.dp)
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            TextField(
                value = note_text.value,
                onValueChange = { note_text.value = it },
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxWidth()
                    .align(Alignment.Start),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Black,
                    unfocusedContainerColor = Color.Black
                ),
                textStyle = TextStyle(
                    background = Color.Black,
                    color = Color.White,
                    fontSize = font_size!!.sp,
                    fontFamily = myFont
                )
            )
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Button(onClick = {
                updateNote(
                    context,
                    note_text.value.text,
                    row_id
                )
            })
            { Text(text = "update", color = Color.White) }
        }

        //adding an extra button without buggering about in xml, okay I am sold. But I am not joining the cult!
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            Button(onClick = {
                val i = Intent(context, MainActivity::class.java)
                context.startActivity(i)
            })
            { Text(text = "No, I'm good, go back", color = Color.White) }
        }
}

