package app.stickynoteskotlin.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun deleteScreen(note: String?, note_title: String?){
    val note_text : String = note.toString()
    val title : String = note_title.toString()

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
            value = title,
            onValueChange = { title })
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = note_text,
            onValueChange = { note_text })
        Spacer(modifier = Modifier.height(15.dp))
    }
}