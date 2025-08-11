package app.stickynoteskotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import app.stickynoteskotlin.ui.theme.StickyNotesKotlinTheme
import kotlin.getValue

interface StickyNoteActions{
    fun addNoteScreen()
    fun updateNoteScreen(noteId: Long, noteTitle: String, noteText: String)
    fun deleteNoteScreen(noteId: Long, noteText: String)
}

class MainActivity : AppCompatActivity(), StickyNoteActions {
    private val viewModel: NotesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.mainactivity)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar

        call_readDataFromDatabase()

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                startActivity(Intent(this@MainActivity, MainActivity::class.java))
            }
        })
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    private fun call_readDataFromDatabase() {
        findViewById<ComposeView>(R.id.compose_view)
            .setContent {
                StickyNotesKotlinTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Scaffold {
                            // Observe data from ViewModel
                            val notes by viewModel.notes.collectAsState()
                            val fontSize by viewModel.fontSize.collectAsState()
                            val fontName by viewModel.fontName.collectAsState()

                            // Pass the observed state to your Composable
                            stickyNoteView(notes, fontSize, fontName, this)
                        }
                    }
                }
            }
    }

    override fun addNoteScreen() {
            val i = Intent(this, AddNewNote::class.java)
            startActivity(i)
    }

    override fun updateNoteScreen(noteId: Long,
                                  noteTitle: String,
                                  noteText: String)
    {
        val i = Intent(this, UpdateNote::class.java)
        i.putExtra("description", noteText)
        i.putExtra("id", noteId)
        startActivity(i)
    }

    override fun deleteNoteScreen(noteId: Long, noteText: String) {
        val i = Intent(this, DeleteNote::class.java)
        i.putExtra("note", noteText)
        i.putExtra("id", noteId)
        startActivity(i)
    }

    public override fun onResume() {
        super.onResume()
        viewModel.loadFontSettings()
        viewModel.loadNotes()
        call_readDataFromDatabase()
    }

    override fun onPause(){
        super.onPause()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.aboutmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        item.itemId
        when (item.itemId) {
            R.id.aboutBox -> {
                val a = Intent(this, About::class.java)
                a.putExtra("title", "A note taking app")
                a.putExtra("text", " A very simple note taking app.\n"
                        + " After adding, short click to edit,\n"
                        + " long click to get delete option.\n")
                startActivity(a)
                return true
            }
            R.id.action_settings -> {
                val i = Intent(this, Prefs::class.java)
                startActivity(i)
                return true
            }
            R.id.credit -> {
                val i = Intent(this, Credit::class.java)
                i.putExtra("title", "Sticky paper the note taker")
                i.putExtra("text", " Made by Chris Harte. Because he could\n"
                + " The fonts are Note_this and IndieFlower taken from fontsquirrel.\n")
                startActivity(i)
                return true
            }
            else -> {
               return super.onOptionsItemSelected(item)
            }
        }
        return false
    }
}




