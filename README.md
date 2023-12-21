<img src="https://github.com/PawTopia/Mobile-Dev/assets/115199962/136c74f7-d841-447f-abe0-83237297efa5" height="90%" /> 

Pawtopia
==================
This is the repository for Pawtopia Mobile Development App for Bangkit Academy H2 Capstone Project

**Pawtopia** is a Android app built with Kotlin and Jetpack Compose. It follows Android design and development best practices.

# Features

**Pawtopia** is a Android app to help users understand and treat their pet diseases. This app uses AI to help estimate the disease that the pet is experiencing based on the symptoms reported by the user. This app will also provide veterinarian and recommendations for the nearest pet clinic that users can visit for further treatment.

# Project Information
* Target Android SDK Version       = 34
* Min Android SDK Version          = 24
* Gradle JDK Version               = 17
* Android Gradle Plugin Version    = 8.1.3
* Kotlin Version                   = 1.9.20
* Compose BOM Version              = 2023.10.01

# Getting Started
1. Clone the repository 
   
   `git clone https://github.com/PawTopia/Mobile-Dev.git`

   or Download the zip file then Extract the zip file
3. Open the whole project in Android Studio.
4. Sync & Run app configuration

# Architecture

The **Pawtopia** app follows the [official architecture guidance](https://developer.android.com/topic/architecture) or what is commonly called Clean Architecture with MVVM Design Pattern

<img src="https://github.com/PawTopia/Mobile-Dev/assets/115199962/52ab5fe8-f434-4dc4-af96-20d569b7075d" width="600px" /> 

![image](https://github.com/PawTopia/Mobile-Dev/assets/115199962/a8197b27-1d50-4a3d-befa-cbe6e0150f1d)

The goals for the app architecture are:
*   Follow the [official architecture guidance](https://developer.android.com/jetpack/guide) as closely as possible.
*   Easy for developers to understand.

# Dependency
* SplashScreen API
* DataStore Preferences
* Dagger Hilt Dependency Injection
* Retrofit
* Firebase Auth
