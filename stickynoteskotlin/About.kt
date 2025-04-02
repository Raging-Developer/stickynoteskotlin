package app.stickynoteskotlin

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class About(): Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aboutlayout)

        val about_text : TextView = findViewById(R.id.about_text)
        val b: Bundle? = intent.extras
        val title = b?.getString("title")
        val text = b?.getString("text")

        setTitle(title)
        about_text.text = text
    }
}