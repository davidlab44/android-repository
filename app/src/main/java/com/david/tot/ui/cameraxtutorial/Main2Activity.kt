package com.david.tot.ui.cameraxtutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.david.tot.ui.cameraxtutorial.ui.MainScreen
import com.david.tot.ui.cameraxtutorial.ui.theme.JetpackComposeCameraXTutorialTheme
//import de.yanneckreiss.cameraxtutorial.ui.MainScreen
//import de.yanneckreiss.cameraxtutorial.ui.theme.JetpackComposeCameraXTutorialTheme

class Main2Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetpackComposeCameraXTutorialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}
