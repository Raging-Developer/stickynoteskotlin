package app.stickynoteskotlin


import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Typeface
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
import app.stickynoteskotlin.MainActivity.Companion.font_size
import app.stickynoteskotlin.ui.theme.StickyNotesKotlinTheme
import app.stickynoteskotlin.ui.theme.addScreen
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddNewNote(): AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val b: Bundle? = intent.extras
        font_size = b?.getInt("font_size")
        val font_name = b?.getString("font_name")
        val fonts = Typeface.createFromAsset(assets, "fonts/$font_name")

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

fun addNewNote(
    context: Context,
    note_text: String){

    var dia = AlertDialog.Builder(context)
    val db = Db_Helper(context).writableDatabase
    val date_format = DateTimeFormatter.ofPattern(" dd/MM/yyyy HH:mm:ss")
    val current = LocalDateTime.now().format(date_format)
    var title = "$current"


    if (note_text!!.isEmpty()) {
        dia.setMessage("Nothing to add")
        dia.setPositiveButton("Try it with text next time", { l: DialogInterface, i: Int -> })
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
