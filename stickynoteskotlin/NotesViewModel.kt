package app.stickynoteskotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private val _notesList = MutableStateFlow<List<StickyNotes>>(emptyList())
    val notes: StateFlow<List<StickyNotes>> = _notesList.asStateFlow()

    private val _fontSize = MutableStateFlow(20)
    val fontSize: StateFlow<Int> = _fontSize.asStateFlow()

    private val _fontName = MutableStateFlow("Note_this.ttf")
    val fontName: StateFlow<String> = _fontName.asStateFlow()

    init {
        loadFontSettings()
        loadNotes()
    }

    fun loadNotes() {
        val context = getApplication<Application>().applicationContext
        val notes = ArrayList<StickyNotes>()
        load_cursor(context, notes)
        _notesList.value = notes
    }

    fun loadFontSettings() {
        val context = getApplication<Application>().applicationContext
        _fontSize.value = get_font_size(context) ?: 20
        _fontName.value = get_font_name(context)
    }
}
