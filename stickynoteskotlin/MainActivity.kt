package app.stickynoteskotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import app.stickynoteskotlin.ui.theme.StickyNotesKotlinTheme
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.mainactivity)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar

        findViewById<ComposeView>(R.id.compose_view)
            .setContent {
            StickyNotesKotlinTheme {
                Scaffold {
                    val compose_view = ComposeView(this)
                    stickyNoteView(LocalContext.current, compose_view)
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
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
        return false
    }
}




