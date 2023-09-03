package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var valueList = intArrayOf()
    var operationList = charArrayOf()

    fun InputNilai(view: View) {
        when((view as Button).text.toString()){
            "C"-> {
                findViewById<TextView>(R.id.panel).text = "Halo"
            }
            "-"-> {
//                todo: tambahkan fungsi pengurangan
            }
            "x"-> {
//                 todo: tambahkan fungsi perkalian
            }
            "/"-> {
//                todo: tambahkan fungsi pembagian
            }
            else->{
                findViewById<TextView>(R.id.panel).text =
                    findViewById<TextView>(R.id.panel).text.toString()+(view as Button).text.toString()
            }
        }
    }

    fun calculate(view: View) {}
}

