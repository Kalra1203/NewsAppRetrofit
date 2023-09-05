package com.example.newsapp.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
){
    object BreakingNews: Screens("breakingNews", "Breaking News", Icons.Filled.Home, Icons.Default.Home)
    object SavedNews: Screens("savedNews", "Saved News", Icons.Filled.Favorite, Icons.Default.Favorite)
}
