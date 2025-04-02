package app.stickynoteskotlin

import android.content.Context
import android.content.Intent
import android.database.Cursor
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.stickynoteskotlin.Db_Helper.Companion.DB_TABLE
import app.stickynoteskotlin.Db_Helper.Companion.key_note
import app.stickynoteskotlin.Db_Helper.Companion.key_row_id
import app.stickynoteskotlin.Db_Helper.Companion.key_title

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun stickyNoteView(context: Context, composeView: ComposeView) {
    var note_list: ArrayList<StickyNotes> = ArrayList()
    val db = Db_Helper(context).readableDatabase
    val c: Cursor? = db.query(DB_TABLE, null, null, null, null, null, null)
    var rowid = c!!.getColumnIndex(key_row_id)
    var title = c!!.getColumnIndex(key_title)
    var note_text = c.getColumnIndex(key_note)

    composeView.apply{
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
    }

    c.moveToFirst()
    while (!c.isAfterLast) {
        val crowid = c.getLong(rowid)
        val ctitle = c.getString(title)
        val cnote = c.getString(note_text)
        val sticky_note = StickyNotes(crowid, ctitle, cnote)//if these are null you have borked the database
        note_list.add(sticky_note)
        c.moveToNext()
    }
    c.close()

    Column(modifier = Modifier.fillMaxSize().padding(top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        // This is the replacement for recycler view, this is all I wanted.
        LazyColumn(modifier = Modifier.background(Color.Black)) {
            itemsIndexed(note_list) { index, item ->
                Card(
                    modifier = Modifier.padding(8.dp)
                        .background(Color.Black)
                        .combinedClickable(onClick = {
                            val i = Intent(context, UpdateNote::class.java)
                            i.putExtra("description", note_list[index].note)
                            i.putExtra("id", note_list[index].row_id)
                            context.startActivity(i)},

                            onLongClick = {
                                val i = Intent(context, DeleteNote::class.java)
                                i.putExtra("note", note_list[index].note)
                                i.putExtra("title", note_list[index].title)
                                i.putExtra("id", note_list[index].row_id)
                                context.startActivity(i)
                            })
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp).fillMaxWidth().background(Color.Black)
                            .padding(top = 60.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Note text :" + note_list[index].note,
                            modifier = Modifier.padding(4.dp),
                            color = Color.White, textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = "This will be time and date : " + note_list[index].title,
                            modifier = Modifier.padding(4.dp),
                            color = Color.White, textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd){
        FloatingActionButton(onClick = {
            val i = Intent(context, AddNewNote::class.java)
            context.startActivity(i)},
            modifier = Modifier.padding(all = 2.dp),
            containerColor = Color.DarkGray,
        ) { Text(text = "Add new course", color = Color.White) }
    }
}

