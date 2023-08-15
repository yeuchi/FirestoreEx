package com.ctyeung.firestoreex

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ctyeung.firestoreex.ui.theme.FireStoreExTheme
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Firebase.firestore
        db.collection("MyCollection").get().addOnSuccessListener { result ->

            var map = HashMap<String, String>()
            for (doc in result) {
                for (key in doc.data.keys) {
                    map.set(key, doc.data[key].toString())
                }
            }

            setContent {
                FireStoreExTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Column() {
                            TopAppBar(
                                title = {Text(text = "FireStoreEx")},
                                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.LightGray)
                            )

                            for (key in map.keys) {
                                Text(text = "key:${key} value:${map[key]}")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FireStoreExTheme {
        Greeting("Android")
    }
}