package app.stickynoteskotlin

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import app.stickynoteskotlin.Db_Helper.Companion.DB_TABLE
import app.stickynoteskotlin.Db_Helper.Companion.key_note
import app.stickynoteskotlin.Db_Helper.Companion.key_title
import app.stickynoteskotlin.ui.theme.StickyNotesKotlinTheme
import app.stickynoteskotlin.ui.theme.addScreen

class AddNewNote(): AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            StickyNotesKotlinTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    addScreen()
                }
            }
        }
    }
}

fun addingTheNewNote(
    context: Context,
    title: String,
    note_text: String){

    var dia = AlertDialog.Builder(context)
    val db = Db_Helper(context).writableDatabase

    if (note_text!!.isEmpty()) {
        dia.setMessage("Enter a note, yer git")
        dia.setPositiveButton("Try again", { l: DialogInterface, i: Int -> })
        dia.create()
        dia.show()
    } else {
        val cv = ContentValues()
        cv.put(key_note, note_text)
        cv.put(key_title, title)

        if (db.insert(DB_TABLE, null, cv).toInt() == -1) {
            dia.setTitle("Borked")
            dia.setMessage("Cock up alert")
            dia.setPositiveButton("Try again", { l: DialogInterface, i: Int -> })
            dia.create()
            dia.show()
        } else {
            dia.setTitle("Success")
            dia.setMessage("Course has been added")
            dia.setPositiveButton("Okie Dokie", { l: DialogInterface, i: Int ->
                context.startActivity(Intent(context, MainActivity::class.java)) })
            dia.create()
            dia.show()
        }
    }
    db.close()
}