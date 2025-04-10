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
import app.stickynoteskotlin.Db_Helper.Companion.key_row_id
import app.stickynoteskotlin.ui.theme.StickyNotesKotlinTheme
import app.stickynoteskotlin.ui.theme.editScreen

class UpdateNote() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val b: Bundle? = intent.extras
        val desc = b?.getString("description")
        val row_id = b?.getLong("id")

        setContent {
            StickyNotesKotlinTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    editScreen(desc, row_id)
                }
            }

        }
    }
}

fun updateNote(
    context: Context,
    note_text: String?,
    row_id: Long?) {

    val db = Db_Helper(context).writableDatabase
    val cv = ContentValues()
    var dia = AlertDialog.Builder(context)

    cv.put(key_note, note_text)

    if (note_text!!.isEmpty()) {
        dia.setTitle("Borked")
        dia.setMessage("nothing to add")
        dia.setPositiveButton("Needs more text", { l: DialogInterface, i: Int ->
            context.startActivity(Intent(context, MainActivity::class.java))
        })
    }
    else {
        if (db.update(DB_TABLE, cv, "$key_row_id = $row_id", null) == 0) {
            dia.setTitle("Borked")
            dia.setMessage("your edit did not work")
            dia.setPositiveButton("Try again", { l: DialogInterface, i: Int ->
                context.startActivity(Intent(context, MainActivity::class.java))
            })
        } else {
            dia.setTitle("Edit applied")
            dia.setMessage("and it cannot be undone or rolled back")
            dia.setPositiveButton("Okie Dokie", { l: DialogInterface, i: Int ->
                context.startActivity(Intent(context, MainActivity::class.java))
            })

        }
    }
    dia.create()
    dia.show()
    db.close()
}
