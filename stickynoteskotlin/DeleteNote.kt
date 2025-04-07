package app.stickynoteskotlin


import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import app.stickynoteskotlin.Db_Helper.Companion.DB_TABLE
import app.stickynoteskotlin.Db_Helper.Companion.key_row_id
import app.stickynoteskotlin.ui.theme.StickyNotesKotlinTheme
import app.stickynoteskotlin.ui.theme.deleteScreen

class DeleteNote() : AppCompatActivity(){
    var row_id: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val b: Bundle? = intent.extras
        val note_text = b?.getString("note")
        row_id = b?.getLong("id")

        setContentView(R.layout.mainactivity)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar

        findViewById<ComposeView>(R.id.compose_view)
            .setContent {
                StickyNotesKotlinTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        deleteScreen(note_text)
                    }
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.context_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        item.itemId
        when (item.itemId) {
            R.id.to_delete -> {
                deleteNote(this, row_id)
                return true
            }
            else -> { super.onOptionsItemSelected(item) }
        }
        return false
    }

    fun deleteNote(context: Context, rowid: Long?) {
        var dia = AlertDialog.Builder(context)
        val db = Db_Helper(context).writableDatabase

        //not sure the logic in this conditional is correct, even though it works.
        if (db.delete(DB_TABLE, "$key_row_id = $rowid", null) == 0) {
            dia.setTitle("Borked")
            dia.setMessage("failed to delete")
            dia.setPositiveButton("Try again", { l: DialogInterface, i: Int ->
                context.startActivity(Intent(context, MainActivity::class.java))
            })
        } else {
            dia.setTitle("Success")
            dia.setMessage("Course has been deleted")
            dia.setPositiveButton("Okie Dokie", { l: DialogInterface, i: Int ->
                context.startActivity(Intent(context, MainActivity::class.java))
            })
        }
        dia.create()
        dia.show()
        db.close()
    }
}
