package app.stickynoteskotlin

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Db_Helper (context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){

    companion object {
        const val DB_NAME = "StickyNotesKotlin"
        const val DB_TABLE = "K_notes"
        const val DB_VERSION = 1
        const val key_row_id = "id"
        const val key_title = "title"
        const val key_note = "note"

    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS $DB_TABLE " +
                "($key_row_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$key_title TEXT NOT NULL," +
                "$key_note TEXT NOT NULL)")
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $DB_TABLE")
        onCreate(db)
    }
}