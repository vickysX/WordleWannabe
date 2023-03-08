package com.example.wordlewannabe.data

import java.io.File

object WordsData {
    val words = File("assets/solutions.txt").readLines().toSet()
}