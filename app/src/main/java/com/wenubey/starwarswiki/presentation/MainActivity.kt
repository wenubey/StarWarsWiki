package com.wenubey.starwarswiki.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.wenubey.starwarswiki.presentation.ui.theme.StarWarsWikiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarWarsWikiTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(text = "HELLO")
                }
            }
        }
    }
}

