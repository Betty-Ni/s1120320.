package com.example.s1120320

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import com.example.s1120320.ui.theme.S1120320Theme

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures

import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.foundation.gestures.detectTapGestures



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            S1120320Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

                    GameScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GameScreen(modifier: Modifier = Modifier) {
    // 定義顏色列表
    val colors = listOf(
        Color(0xff95fe95),
        Color(0xfffdca0f),
        Color(0xfffea4a4),
        Color(0xffa5dfed)
    )

    // 使用 `remember` 保存當前背景顏色的索引
    val currentColorIndex = remember { mutableStateOf(0) }

    // 取得當前顏色
    val currentColor = colors[currentColorIndex.value]

    val activity = (LocalContext.current as? Activity)

    // 使用 Column 來顯示內容
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(currentColor)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    change.consume() // 消耗手勢事件
                    // 根據滑動方向切換顏色索引
                    val threshold = 50f // 設定滑動門檻 (例如 50 像素)
                    if (dragAmount > 0) { // 右滑
                        currentColorIndex.value = (currentColorIndex.value + 1) % colors.size
                    } else if (dragAmount < 0) { // 左滑
                        currentColorIndex.value =
                            (currentColorIndex.value - 1 + colors.size) % colors.size
                    }
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 顯示期末上機考的文字
        Text(
            text = "2024期末上機考(資管二A倪培睿)",
            fontSize = 18.sp,
            color = Color.Black
        )

        // 顯示圖片 (假設 class_a 是你放在資源中的圖片)
        Image(
            painter = painterResource(id = R.drawable.class_a),
            contentDescription = "class_a",
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // 顯示遊戲持續時間
        Text(
            text = "遊戲持續時間: 0 秒",
            fontSize = 20.sp,
            color = Color.Black
        )

        // 顯示玩家的成績
        Text(
            text = "您的成績: 0 分",
            fontSize = 20.sp,
            color = Color.Black
        )

        // 結束 App 的按鈕
        Button(
            onClick = {
                activity?.finish()
            }
        ) {
            Text(text = "結束App")
        }
    }
}
