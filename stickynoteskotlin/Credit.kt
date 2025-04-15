package app.stickynoteskotlin

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class Credit(): Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.credtilayout)

        val credit_text : TextView = findViewById(R.id.credit_text)
        val b: Bundle? = intent.extras
        val title = b?.getString("title")
        val text = b?.getString("text")

        setTitle(title)
        credit_text.text = text
    }
}