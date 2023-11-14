package com.example.newsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.common.Screens
import com.example.newsapp.presentation.detailed_screen.DetailedItem
import com.example.newsapp.presentation.detailed_screen.DetailedScreen
import com.example.newsapp.presentation.saved_headlines.SavedHeadlinesScreen
import com.example.newsapp.presentation.saved_headlines.SavedHeadlinesViewModelRoom
import com.example.newsapp.presentation.search_headlines.SearchHeadlineViewModel
import com.example.newsapp.presentation.search_headlines.SearchHeadlinesScreen
import com.example.newsapp.presentation.top_headlines.TopHeadlineViewModel
import com.example.newsapp.presentation.top_headlines.TopHeadlinesScreen
import com.example.newsapp.presentation.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint


@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModelRoom by viewModels<SavedHeadlinesViewModelRoom>()
        val viewModel by viewModels<TopHeadlineViewModel>()
        val searchHeadlineViewModel by viewModels<SearchHeadlineViewModel>()

        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val screens = listOf(
                        Screens.BreakingNews, Screens.SearchNews, Screens.SavedNews
                    )

                    var selectedItemIndex by rememberSaveable {
                        mutableStateOf(0)
                    }
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text(text = "Kalra News App") },
                                colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.background)
                            )
                        }, bottomBar = {
                            NavigationBar(modifier = Modifier.wrapContentHeight()) {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination
                                screens.forEachIndexed { index, screen ->
                                    NavigationBarItem(
                                        selected = selectedItemIndex == index,
                                        onClick = {
                                            selectedItemIndex = index
                                            navController.navigate(screen.route) {
//                                                // Pop up to the start destination of the graph to
//                                                // avoid building up a large stack of destinations
//                                                // on the back stack as users select items
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
//                                                // Avoid multiple copies of the same destination when
//                                                // reselecting the same item
                                                launchSingleTop = true
//                                                // Restore state when reselecting a previously selected item
                                                restoreState = true
                                            }
                                        },
                                        icon = {
                                            Icon(
                                                imageVector = if (selectedItemIndex == index) screen.selectedIcon else screen.unselectedIcon,
                                                contentDescription = screen.title
                                            )
                                        },
                                        label = {
                                            Text(text = screen.title)
                                        },
                                        alwaysShowLabel = false,
                                    )

                                }

                            }

                        }) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = Screens.BreakingNews.route,
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable(Screens.BreakingNews.route) {
                                TopHeadlinesScreen(
                                    navController = navController, viewModel, viewModelRoom
                                )

                            }
                            composable(Screens.SavedNews.route) {
                                SavedHeadlinesScreen(
                                    topHeadlinesViewModelRoom = viewModelRoom,
                                    navController = navController
                                )
                            }
                            composable("detailedScreen") {
                                val detailedScreen =
                                    navController.previousBackStackEntry?.savedStateHandle?.get<DetailedScreen>(
                                        "headline"
                                    )
                                detailedScreen?.let { it1 -> DetailedItem(detailedScreen = it1) }
                            }
                            composable(Screens.SearchNews.route) {
                                SearchHeadlinesScreen(searchHeadlineViewModel)
                            }


                        }

                    }


                }
            }
        }
    }
}




