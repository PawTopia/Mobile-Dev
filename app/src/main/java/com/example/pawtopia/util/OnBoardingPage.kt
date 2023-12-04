package com.example.pawtopia.util

import androidx.annotation.DrawableRes
import com.example.pawtopia.R

sealed class OnBoardingPage(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.drawable.onboarding1,
        title = "Selamat Datang",
        description = "Solusi Pet Consultation Terdekat dan Terbaik."
    )

    object Second : OnBoardingPage(
        image = R.drawable.onboarding2,
        title = "Cari Klinik Terdekat",
        description = "Rekomendasi Klinik Terpercaya dan Terdekat untuk Hewan Peliharaan Anda."
    )

    object Third : OnBoardingPage(
        image = R.drawable.onboarding3,
        title = "Konsultasi dengan Dokter",
        description = "Dapatkan Dukungan Medis untuk Hewan Peliharaan Anda Kapan Saja, Di Mana Saja."
    )
}