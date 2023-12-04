package com.example.mad.ui

import androidx.annotation.StringRes
import com.example.mad.R

/**
 * You can consider a sealed class as an enum++
 * Sealed classes can also contain state - making it very useful for different network response
 * @author Pim Meijer
 */
sealed class PetsScreens(val route: String, @StringRes val labelResourceId: Int) {
    object CatsScreen : PetsScreens("cats", R.string.cats)
    object DogsScreen : PetsScreens("dogs", R.string.dogs)
}
