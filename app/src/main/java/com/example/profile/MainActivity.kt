package com.example.profile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.profile.ui.theme.ProfileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfileTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ProfileConstraintScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileConstraintScreen(modifier: Modifier = Modifier) {

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (header, avatar, name, bio, contactLabel, emailIcon, emailText, phoneIcon, phoneText) = createRefs()
        val topGuideline = createGuidelineFromTop(0.22f)

        Box(
            modifier = Modifier
                .constrainAs(header) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(topGuideline)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .background(
                    Brush.verticalGradient(listOf(Color(0xFF8FAE8B), Color(0xFFBFC3C6)))
                )
        )

        Box(
            modifier = Modifier
                .size(104.dp)
                .clip(CircleShape)
                .background(Color(0xFF2F2F2F))
                .constrainAs(avatar) {
                    top.linkTo(topGuideline, margin = (-52).dp)
                    bottom.linkTo(topGuideline, margin = 52.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "!",
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = "CS501 Student",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(name) {
                top.linkTo(avatar.bottom, margin = 10.dp)
                start.linkTo(avatar.start)
            }
        )

        Text(
            text = "Student at Boston University | Android SDK Developer | Kotlin Professional",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.constrainAs(bio) {
                top.linkTo(name.bottom, margin = 6.dp)
                start.linkTo(name.start)
                end.linkTo(parent.end, margin = 16.dp)
                width = Dimension.preferredWrapContent
            }
        )

        val lowerContentBarrier = createBottomBarrier(bio, avatar)

        Text(
            text = "CONTACT",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.constrainAs(contactLabel) {
                top.linkTo(lowerContentBarrier, margin = 24.dp)
                start.linkTo(parent.start)
            }
        )

        androidx.compose.material3.Icon(
            imageVector = Icons.Default.Email,
            contentDescription = "Email",
            modifier = Modifier
                .size(20.dp)
                .constrainAs(emailIcon) {
                    top.linkTo(contactLabel.bottom, margin = 12.dp)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = "cs501@bu.edu",
            modifier = Modifier.constrainAs(emailText) {
                centerVerticallyTo(emailIcon)
                start.linkTo(emailIcon.end, margin = 8.dp)
            }
        )

        androidx.compose.material3.Icon(
            imageVector = Icons.Default.Phone,
            contentDescription = "Phone",
            modifier = Modifier
                .size(20.dp)
                .constrainAs(phoneIcon) {
                    top.linkTo(emailIcon.bottom, margin = 10.dp)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = "(123)-456-7890",
            modifier = Modifier.constrainAs(phoneText) {
                centerVerticallyTo(phoneIcon)
                start.linkTo(phoneIcon.end, margin = 8.dp)
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileConstraintPreview() {
    ProfileTheme {
        ProfileConstraintScreen()
    }
}