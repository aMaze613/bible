package com.m8.exercicis.bible.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.m8.exercicis.bible.Verse
import com.m8.exercicis.bible.db.VersesContract.COLUMN_NAME_BOOK
import com.m8.exercicis.bible.db.VersesContract.COLUMN_NAME_CHAPTER
import com.m8.exercicis.bible.db.VersesContract.COLUMN_NAME_TEXT
import com.m8.exercicis.bible.db.VersesContract.COLUMN_NAME_VERSE
import com.m8.exercicis.bible.db.VersesContract.SQL_CREATE_ENTRIES
import com.m8.exercicis.bible.db.VersesContract.SQL_DELETE_ENTRIES
import com.m8.exercicis.bible.db.VersesContract.TABLE_NAME

class VersesDBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        // If you change the database schema, you must increment the database version
        const val DATABASE_VERSION = 6
        const val DATABASE_NAME = "verses.db"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    fun insertVerse(verse: Verse) {
        val values = ContentValues()
        values.put(COLUMN_NAME_BOOK, verse.book)
        values.put(COLUMN_NAME_CHAPTER, verse.chapter)
        values.put(COLUMN_NAME_VERSE, verse.verse)
        values.put(COLUMN_NAME_TEXT, verse.text)

        this.writableDatabase.insert(TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun getVerses(): MutableList<Verse> {
        val db = this.writableDatabase
        val verses: MutableList<Verse> = mutableListOf()
        val query =
            "SELECT id, $COLUMN_NAME_BOOK, $COLUMN_NAME_CHAPTER, $COLUMN_NAME_VERSE, $COLUMN_NAME_TEXT FROM $TABLE_NAME"
        val cursor: Cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()) {
            verses.add(
                Verse(
                    cursor.getInt(cursor.getColumnIndex("id")).toLong(),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME_BOOK)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_CHAPTER)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_VERSE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TEXT))
                )
            )
        }
        cursor.close()
        return verses
    }

    fun deleteAllVerses() {
        this.writableDatabase.execSQL("DELETE FROM verses")
    }

    fun deleteVerse(id: Long) {
        this.writableDatabase.execSQL("DELETE FROM verses WHERE id=$id")
    }
}