package com.m8.exercicis.bible

class Verse(
    val book: String,
    val chapter: Int,
    val verse: Int,
    val text: String
) {
    override fun toString(): String {
        return "\"$text\" - $book $chapter:$verse"
    }
}