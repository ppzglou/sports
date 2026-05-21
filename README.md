# Sports

A modern Android application built with Kotlin, Jetpack Compose, Clean Architecture, Room, Retrofit,
Hilt, and Coroutines.

## Features

- Sports list grouped by category
- Expand / collapse sports events
- Favorite events support
- Live countdown timer for events
- Offline persistence with Room
- Modern Material 3 UI
- Dark / Light theme support
- Unit tested layers

---

# Architecture

The project follows Clean Architecture principles.

```text
app
│
├── domain
│   ├── models
│   ├── repository
│   └── use cases
│
├── data
│   ├── remote
│   ├── local
│   ├── mappers
│   └── repository implementation
│
└── presentation
    ├── screens
    ├── view models
    ├── ui state
    ├── components
    └── theme
```

---

# Tech Stack

## UI

- Jetpack Compose
- Material 3
- Navigation Compose

## Architecture

- Clean Architecture
- MVI

## Async

- Kotlin Coroutines
- Flow

## Dependency Injection

- Hilt

## Local Storage

- Room
- DataStore

## Networking

- Retrofit
- OkHttp
- Gson

## Testing

- JUnit
- MockK
- Coroutine Test

---

# Screens

## Dashboard

- Displays sports grouped with events
- Expand / collapse functionality
- Favorite support
- Live event countdowns

## Favorites

- Shows only favorite events
- Empty state handling

---

# Testing

The project contains unit tests for:

- DTO → Domain mappers
- Domain → Entity mappers
- DAO queries
- Use Cases
- Presentation mappers
- Utility extensions

Run tests:

```bash
./gradlew test
```

---

# Build & Run

## Clone

```bash
git clone https://github.com/ppzglou/sports.git
```

## Open Project

Open the project in Android Studio and sync Gradle.

## Run

```bash
./gradlew installDebug
```

---

# Project Structure

```text
data/
domain/
presentation/
app/
```

---

# Future Improvements

- UI tests with Compose Testing
- Paging support
- Better timer abstraction for testability
- Animations for expand/collapse
- Tablet support
- Pull to refresh

---

# Author

Symeon Papazoglou

GitHub:
https://github.com/ppzglou