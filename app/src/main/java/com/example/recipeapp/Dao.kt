package com.example.recipeapp

import androidx.room.Query

interface Dao {
    @get:Query("Select * from recipe")
    var all:List<Recipe?>?
}