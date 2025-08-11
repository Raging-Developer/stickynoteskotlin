package app.stickynoteskotlin

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


//@OptIn(ExperimentalFoundationApi::class)
@Composable
fun stickyNoteView(
        note_list: List<StickyNotes>,
        font_size: Int,
        font_name: String,
        actions: StickyNoteActions
) {

    val myFont = remember(font_name) {
        if (font_name == "Note_this.ttf") {
            FontFamily(Font(R.font.note_this))
        } else {
            FontFamily(Font(R.font.indieflower))
        }
    }

    Scaffold(
        modifier = Modifier.padding(8.dp).padding(top = 60.dp),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    actions.addNoteScreen()
                },
                containerColor = Color.DarkGray
            )
            {
                Text(
                    text = "Add new note", color = Color.White,
                    modifier = Modifier.padding(all = 4.dp)
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues) // Apply padding from Scaffold
                .background(Color.Black)
        ) {
            items(note_list) { note ->
                Row(
                    Modifier.padding(all = 10.dp)
                        .fillMaxWidth()
                        .background(Color.Black)
                        .combinedClickable(
                            onClick = {
                                actions.updateNoteScreen(
                                    note.row_id,
                                    note.title,
                                    note.note
                                )
                            },
                            onLongClick = {
                                actions.deleteNoteScreen(note.row_id, note.note)
                            }
                        )
                ) {
                    Column {
                        Text(
                            text = note.title,
                            fontFamily = myFont,
                            fontSize = font_size!!.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(4.dp),
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = note.note,
                            fontFamily = myFont,
                            fontSize = font_size!!.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.fillMaxWidth().padding(4.dp),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}







