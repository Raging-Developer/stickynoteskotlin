package app.stickynoteskotlin

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import kotlin.text.toInt

//Screw datastore and room libraries, this is a working API
class Prefs: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager
            .beginTransaction()
            .replace(android.R.id.content, Frag_man())
            .commit()
    }
}

class Frag_man: PreferenceFragmentCompat(){
    override fun onCreatePreferences(
        savedInstanceState: Bundle?,
        rootKey: String?
    ) {
        addPreferencesFromResource(R.xml.prefs)
    }
}

fun get_font_size(context: Context): Int?{
    var size_prefs = PreferenceManager.getDefaultSharedPreferences(context)
    var font_size = size_prefs.getString("fong", "24")

    val result = font_size?.toInt()

    return result
}

fun get_font_name(context: Context): String{
    var name_prefs = PreferenceManager.getDefaultSharedPreferences(context)
    var font_name = name_prefs.getString("fonz", "Note_this.ttf")

    return font_name.toString()
}
