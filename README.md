# KmpStoreApp

KmpStoreApp is a Kotlin Multiplatform Mobile (KMP) application built using:

* **Jetpack Compose** for Android UI
* **SwiftUI** for iOS UI
* **Shared business logic** using Kotlin Multiplatform
* **Clean Architecture**
* **Koin Dependency Injection**
* **Ktor Networking**
* **Coroutines + StateFlow**

The project demonstrates how to share:

* Networking
* Repository layer
* UseCases
* ViewModels
* UI State management

while keeping native UI implementations for Android and iOS.

---

# 📱 Platforms

| Platform | UI Framework    |
| -------- | --------------- |
| Android  | Jetpack Compose |
| iOS      | SwiftUI         |

---

# 🏗️ Project Architecture

The project follows **Clean Architecture** with modularization.

```text
shared
│
├── core-network
│
├── features
│   ├── search
│   │   ├── data
│   │   ├── domain
│   │   └── presentation
│   │
│   └── details
│       ├── data
│       ├── domain
│       └── presentation
│
└── shared
```

---

# ✨ Features

* Product Search
* Product Details
* Shared ViewModels
* Shared Networking
* Debounced Search
* Image Loading
* Native Navigation
* StateFlow-based UI updates

---

# 🧰 Tech Stack

## Shared Module

* Kotlin Multiplatform
* Kotlin Coroutines
* StateFlow
* Ktor Client
* Koin
* kotlinx.serialization

## Android

* Jetpack Compose
* Navigation Compose

## iOS

* SwiftUI
* NavigationStack

---

# 📂 Project Structure

## `/composeApp`

Contains Android Compose UI implementation.

### Important folders

| Folder        | Description                     |
| ------------- | ------------------------------- |
| `commonMain`  | Shared Compose code             |
| `androidMain` | Android-specific implementation |

---

## `/iosApp`

Contains SwiftUI application code.

This module includes:

* SwiftUI screens
* NavigationStack setup
* ObservableObject wrappers for KMP ViewModels

---

## `/shared`

Contains all shared business logic.

### Includes

* API layer
* Repository layer
* Domain layer
* Shared ViewModels
* Shared UI state

---

# 🔄 State Management

The project uses:

* `StateFlow` in shared ViewModels
* SwiftUI `ObservableObject` wrappers on iOS
* `collectAsStateWithLifecycle()` on Android

---

# 📦 Dependency Injection

Koin is used for dependency injection across all shared modules.

---

# 🚀 Running Android App

## Using Android Studio

Run the Android configuration directly.

## Using Terminal

### macOS/Linux

```bash
./gradlew :composeApp:assembleDebug
```

### Windows

```bash
.\gradlew.bat :composeApp:assembleDebug
```

---

# 🍎 Running iOS App

1. Open:

```text
iosApp/iosApp.xcodeproj
```

or

```text
iosApp/iosApp.xcworkspace
```

2. Build and run from Xcode.

---

# 🔧 Build Shared XCFramework

```bash
./gradlew :shared:assembleSharedDebugXCFramework
```

Generated framework:

```text
shared/build/XCFrameworks/debug/Shared.xcframework
```

---

# 📸 Screens

* Search Screen
* Product Details Screen
* Shared Loading/Error State Handling

---

# 📚 Learning Goals

This project demonstrates:

* Kotlin Multiplatform architecture
* Sharing business logic between Android & iOS
* SwiftUI + KMP integration
* StateFlow observation in SwiftUI
* Clean Architecture in KMP
* Modular KMP setup

---

# 📄 License

This project is for learning and demonstration purposes.
