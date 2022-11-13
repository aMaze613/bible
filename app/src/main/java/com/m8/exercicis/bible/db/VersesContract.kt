package com.m8.exercicis.bible.db

object VersesContract {
    const val TABLE_NAME = "verses"
    const val COLUMN_NAME_BOOK = "book"
    const val COLUMN_NAME_CHAPTER = "chapter"
    const val COLUMN_NAME_VERSE = "verse"
    const val COLUMN_NAME_TEXT = "text"

    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE $TABLE_NAME (" +
                "id INTEGER PRIMARY KEY," +
                "$COLUMN_NAME_BOOK TEXT," +
                "$COLUMN_NAME_CHAPTER TINYINT," +
                "$COLUMN_NAME_VERSE TINYINT," +
                "$COLUMN_NAME_TEXT TEXT)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"
}