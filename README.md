# 1. Describe the approach you’ve taken (Architecture, frameworks or libraries used) and explain why you’ve selected it for the sample Android application.

**Jetpack Compose, Navigation, Media3, Room, Work Manager and other Jetpack Components**

Use Google's recommended tools and follow their recommended approach for building modern Android
applications.

**Ktor and Coil**

Popular libraries for making HTTP requests and loading images, respectively, and support modern
Android development building blocks, e.g. coroutines and Jetpack Compose.

**Android Test Orchestrator**

To ensure each instrumented test truly run in isolation and never share state across tests.

# 2. List down any functionalities or technical details that you wanted to add if you had additional time.

- UI/UX. Better permission request flow. Give user options for setting reminders. Polish UI.
- Synchronizing and caching server data locally. Better if can use Firebase Firestore to lessen implementation efforts.
- Move UI state to ViewModels.
- Hilt for dependency injection.
- More unit and UI tests. Maybe some integration and end-to-end tests. JaCoCo reports.
- Figure out navigation transition animation bug/issue.
- Improve loading UI, especially in the match detail screen with highlight video.
- App profiling.