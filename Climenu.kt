package org.isomorphisms.climenu

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prompt = intent.getStringExtra("prompt") ?: "Choose an action"
        val choices = intent.getStringArrayExtra("choices")
            ?.toList()
            ?.takeIf { it.isNotEmpty() }
            ?: listOf("Run", "Settings", "Quit")

        setContent {
            ClimenuTheme {
                Climenu(prompt = prompt, choices = choices)
            }
        }
    }
}

@Composable
fun Climenu(
    prompt: String,
    choices: List<String>,
) {
    var selected_index by remember { mutableIntStateOf(-1) }

    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            item {
                Text(
                    text = prompt,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 20.dp),
                )
                HorizontalDivider()
            }

            itemsIndexed(choices) { index, choice ->
                ListItem(
                    headlineContent = { Text(choice) },
                    leadingContent = {
                        RadioButton(
                            selected = selected_index == index,
                            onClick = null,
                        )
                    },
                    modifier = Modifier.clickable { selected_index = index },
                )
            }

            if (selected_index >= 0) {
                item {
                    HorizontalDivider()
                    Text(
                        text = "Selected: ${choices[selected_index]}",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(24.dp),
                    )
                }
            }
        }
    }
}

@Composable
private fun ClimenuTheme(content: @Composable () -> Unit) {
    val dark = isSystemInDarkTheme()
    val context = LocalContext.current
    val colors = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && dark -> dynamicDarkColorScheme(context)
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> dynamicLightColorScheme(context)
        dark -> darkColorScheme()
        else -> lightColorScheme()
    }

    MaterialTheme(colorScheme = colors, content = content)
}
