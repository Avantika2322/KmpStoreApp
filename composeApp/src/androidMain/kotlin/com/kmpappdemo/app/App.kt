package com.kmpappdemo.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kmpappdemo.app.navigation.BaseNavGraph
import com.kmpappdemo.app.navigation.DetailsNavGraph
import com.kmpappdemo.app.navigation.SearchNavGraph

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = SearchNavGraph.Dest.Root
            ){
                listOf<BaseNavGraph>(
                    SearchNavGraph,
                    DetailsNavGraph
                ).forEach {
                    it.build(
                        modifier = Modifier.fillMaxSize(),
                        navController = navController,
                        navGraphBuilder = this
                    )
                }
            }
        }
    }
}