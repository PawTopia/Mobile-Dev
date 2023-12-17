package com.example.pawtopia.screen.welcome

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pawtopia.ui.theme.*
import com.example.pawtopia.data.model.OnBoardingPage
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(
    navigateToAuth: () -> Unit,
    onNavUp: () -> Unit,
    viewModel: WelcomeScreenViewModel = hiltViewModel()
) {

    val pagerState = rememberPagerState(pageCount = {
        3
    })
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third,
    )

    val couroutineScope = rememberCoroutineScope()
    BackHandler {
        couroutineScope.launch {
            if (pagerState.currentPage >= 1) {
                pagerState.scrollToPage(pagerState.currentPage - 1)
            } else {
                onNavUp()
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(10f),
        ) { position ->
            PagerScreen(onBoardingPage = pages[position])
        }
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color: Color
                val width: Dp

                if (pagerState.currentPage == iteration) {
                    width = 40.dp
                    color = primaryColor
                } else {
                    width = 10.dp
                    color = Color.LightGray
                }
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .height(10.dp)
                        .width(width)
                        .clip(CircleShape)
                        .background(color)
                )
            }
        }
        OnboardingButton(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(60.dp)
                .padding(bottom = 10.dp),
            pagerState = pagerState
        ) {
            if (pagerState.currentPage != 2) {
                couroutineScope.launch {
                    pagerState.scrollToPage(pagerState.currentPage + 1)
                }
            } else {
                viewModel.saveOnboardingState(true)
                navigateToAuth()
            }
        }
    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = "onBoarding Image",
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.25f),
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = onBoardingPage.title,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp)
                .padding(top = 10.dp),
            text = onBoardingPage.description,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(30),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Black
        )
    ) {
        Text(text = if (pagerState.currentPage == 2) "Get Started" else "Next", fontSize = 20.sp)
    }
}
