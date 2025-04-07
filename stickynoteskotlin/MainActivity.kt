package app.stickynoteskotlin


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import app.stickynoteskotlin.ui.theme.StickyNotesKotlinTheme
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {

    companion object {
        var font_size: Int? = 20
        var font_name: String = ""
    }

    override fun onResume() {
        super.onResume()
        font_size = get_font_size(this)
        font_name = get_font_name(this)
        call_readDataFromDatabase()
    }

    override fun onPause(){
        super.onPause()
        font_size = get_font_size(this)
        font_name = get_font_name(this)
        call_readDataFromDatabase()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        font_size = get_font_size(this)
        font_name = get_font_name(this)

        setContentView(R.layout.mainactivity)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar

        call_readDataFromDatabase()

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
                            val compose_view = ComposeView(this)
                            stickyNoteView(LocalContext.current, compose_view)
                        }
                    }
                }
            }
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
                a.putExtra("text", "A very simple note taking app.\n"
                        + "After adding, short click to edit,\n"
                        + "long click to get delete option.\n")
                startActivity(a)
            }
            R.id.action_settings -> {
                val i = Intent(this, Prefs::class.java)
                startActivity(i)
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
        return false
    }
}
