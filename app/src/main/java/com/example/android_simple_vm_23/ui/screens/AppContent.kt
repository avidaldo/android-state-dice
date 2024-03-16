package com.example.android_simple_vm_23.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_simple_vm_23.R
import kotlin.random.Random


@Composable
fun BotonMuestraCosaSelectorStateFull() {
    var num by rememberSaveable { mutableIntStateOf(0) }
    var imagenFlag by rememberSaveable { mutableStateOf(true) }

    BotonMuestraCosaSelectorStateless(
        num,
        { num = Random.nextInt(from = 1, until = 7) },
        (imagenFlag),
        { imagenFlag = ! imagenFlag },)

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BotonMuestraCosaSelectorStateless(
    num: Int,
    changeNum: () -> Unit,
    imagenFlag: Boolean,
    changeImageFlag: () -> Unit,
) {

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Tirador de Dados") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                actions = {
                    IconToggleButton(checked = imagenFlag,
                        onCheckedChange = {
                            changeImageFlag()
                        }) {
                        Icon(imageVector = Icons.Default.Star, contentDescription = "")
                    }
                })
        },
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { changeNum() }) {
                Text(text = "Lanzar Dado")
            }
            if (imagenFlag) {
                Image(painterResource(getImageFromNum(num)), "$num")
            } else {
                Text(text = "$num", fontSize = 40.sp)
            }
        }
    }
}


fun getImageFromNum(num: Int) =
    when (num) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.empty_dice

    }