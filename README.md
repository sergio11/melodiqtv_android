
# MelodiqTV 🎶✨: Unleash the Power of Music on Your TV

<img width="auto" height="200px" align="left" src="doc/main_logo.png" />

Welcome to **MelodiqTV**, your ultimate music experience on Android TV. MelodiqTV brings the power of music and video clips to your TV screen, offering a vast and diverse collection of music genres from around the world. Whether you're into the latest hits, timeless classics, or discovering new sounds, MelodiqTV has something for everyone.

MelodiqTV 🎶📺 is a cutting-edge music and video streaming platform designed for Android TV, offering users a personalized and immersive entertainment experience. With MelodiqTV, users can explore a vast library of music and music videos, organized by genres and categories, while easily creating and managing multiple profiles to keep track of their favorites ⭐. The app is built using Firebase 🔥 for real-time data synchronization and secure user authentication, ensuring a seamless and reliable experience. Developed with a robust architecture, it follows Clean Architecture principles and the MVI pattern 🏛️, making the codebase maintainable and scalable. The UI is crafted with Jetpack Compose for TV 📺, providing a modern, flexible, and reusable interface that enhances the overall user experience on large screens.

**Built using Jetpack Compose for TV**, MelodiqTV seamlessly integrates into your Smart TV environment. Utilizing [**🍮 Fudge**](https://github.com/sergio11/fudge_tv_compose_library), a powerful UI Kit for TV apps, MelodiqTV ensures smooth navigation and an engaging user experience. Our platform transforms your living room into a musical haven, where you can explore, enjoy, and share your favorite tunes. 🎶✨

A heartfelt thank you to the creators of the [JetFit repository](https://github.com/TheChance101/tv-samples/tree/JetFit/JetFit) for providing such an invaluable starting point for Jetpack Compose for TV. Your work has been incredibly inspiring and instrumental in shaping the development of this project.

Happy listening with MelodiqTV! 🎉📺

<p align="center">
  <img src="https://img.shields.io/badge/Android%20Studio-3DDC84.svg?style=for-the-badge&logo=android-studio&logoColor=white" />
  <img src="https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white" />
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" />
  <img src="https://img.shields.io/badge/firebase-ffca28?style=for-the-badge&logo=firebase&logoColor=black" />
  <img src="https://img.shields.io/badge/Material%20UI-007FFF?style=for-the-badge&logo=mui&logoColor=white" />
</p>

Slides are built using the template from [Previewed](https://previewed.app/template/AFC0B4CB). I extend my gratitude to them for their remarkable work and contribution.

## Overview 🌐

**MelodiqTV** is not just a music streaming service—it’s your ultimate music companion designed to elevate your listening experience. Here’s what makes MelodiqTV stand out:

### Personalized Profiles 👥
With MelodiqTV, every family member can create their own profile, allowing them to save favorite songs and receive personalized music recommendations. This feature ensures everyone can enjoy a tailored listening experience based on their unique tastes.

### Advanced Music Search 🔍
Our advanced search functionality allows you to find songs based on various criteria, including:
- **Genre** 🎵: Explore your favorite music styles.
- **Mood** 😊: Discover tracks that match your vibe.
- **Language** 🌍: Choose songs in your preferred language.
- **Release Date** 📅: Find the latest hits or timeless classics.

### Customizable Preferences ⚙️
MelodiqTV offers various customization options to enhance your experience:
- **Default Video Quality** 📺: Set your preferred resolution for streaming music videos.
- **App Language** 🌐: Select the language for the app interface.
- **Playback Options** 🎧: Choose your preferred playback settings for a personalized experience.

With these features, MelodiqTV ensures a seamless, user-friendly experience tailored to your music preferences. Whether you're exploring new genres, saving favorites, or adjusting settings, MelodiqTV has got you covered. 🎤📺

## Technologies Used 🛠️

- **Kotlin**: The preferred language for developing Android applications, offering modern syntax and powerful features to enhance productivity. 🚀

- **Firebase Platform**:
  - **Firestore**: 🔥 A NoSQL cloud database providing real-time data synchronization and offline support, ensuring fast and reliable data retrieval. 📊✨
  - **Firebase Auth**: 🔐 Simplifies user authentication with secure sign-in and user management capabilities. Supports various authentication methods. 🛡️📱
  - **Firebase Storage**: ☁️ Stores user-generated content like profile images and media files with built-in security and seamless integration. 📸🎥

- **Coroutines**: 🌀 Simplifies asynchronous programming and manages background tasks efficiently, enhancing app responsiveness. ⏱️

- **Clean Architecture**: 🏗️ Promotes a well-structured and scalable app design by separating concerns into distinct layers, enhancing maintainability and testability. 🔍

- **MVI (Model-View-Intent)**: 📈 Implements a unidirectional data flow pattern, ensuring a clear separation between UI components and business logic. 🔄

- **Jetpack Compose for TV**: 📺 Utilizes Jetpack Compose to build modern, responsive UIs tailored for TV screens, optimizing the interface for large displays. 🎨

- **Jetpack Compose Navigation**: 🗺️ Facilitates in-app navigation and screen transitions with a clear API, supporting deep linking and complex navigation flows effortlessly. 🚦

- **Material Design 3**: 🎨 Applies the latest Material Design guidelines to create a visually appealing and intuitive user interface. 🖌️

- **🍮 Fudge**: [Fudge](https://github.com/sergio11/fudge_tv_compose_library) is a Jetpack Compose UI Kit for TV apps, providing pre-built components and tools to craft engaging experiences on the big screen. 🎬🚀

- **Jetpack DataStore**: 💾 A modern data storage solution for key-value pairs and typed objects, ensuring reliable data handling in your app. 🔐

- **Media3 for Media Playback**:
  - **Media3 ExoPlayer**: 🎥 Part of the Media3 library, ExoPlayer supports various media formats and advanced features for high-quality playback. 📻🍿
  - **Media3 UI**: 🎨 Provides UI components for integrating media playback controls into your app’s interface. 🕹️

- **Dagger Hilt**: 🧩 A dependency injection library simplifying the management of dependencies and enhancing modularity in your app. 🔧💡

- **Mapper Pattern**: 🔄 Facilitates conversion between different data models, ensuring data consistency across application components. 📐

## Architecture Overview 🏛️

Our application is designed with a robust architecture for maintainability, testability, and flexibility. The architecture leverages several design patterns and principles:

### **Clean Architecture** 🏗️
Clean Architecture focuses on separating concerns into distinct layers:
- **Presentation Layer**: Handles UI and user interactions using Jetpack Compose for modern interfaces.
- **Domain Layer**: Contains business logic and use cases, independent of external frameworks.
- **Data Layer**: Manages data sources and repositories, abstracting data retrieval and storage.

### **Data Sources** 📦
Data sources fetch and manage data from various origins, including:
- **Remote Data Sources**: Interact with cloud services or web APIs (e.g., Firebase Firestore).
- **Local Data Sources**: Handle local data storage (e.g., Jetpack DataStore).

### **Repository Pattern** 🗃️
The repository pattern provides a unified interface for data access, decoupling data retrieval from the rest of the application for easier testing and maintenance.

### **Use Cases** 🧩
In the Domain Layer, Use Cases represent specific actions, encapsulating business logic and interacting with repositories to retrieve or modify data.

### **Inversion of Control (IoC)** 🔄
IoC inverts control flow, allowing dependencies to be injected rather than hardcoded, promoting modularity and reducing boilerplate code.

### **SOLID Principles** 📏
We apply SOLID principles to ensure our codebase remains clean and maintainable:
- **Single Responsibility Principle (SRP)**: Each class has one responsibility.
- **Open/Closed Principle (OCP)**: Classes are open for extension but closed for modification.
- **Liskov Substitution Principle (LSP)**: Subtypes must be substitutable for their base types.
- **Interface Segregation Principle (ISP)**: Clients should not depend on interfaces they do not use.
- **Dependency Inversion Principle (DIP)**: High-level modules depend on abstractions.

### **MVI (Model-View-Intent)** 📈
MVI manages state and interactions, ensuring a predictable unidirectional data flow.

This architecture ensures that MelodiqTV is well-structured, easy to maintain, and scalable, adhering to best practices and design principles.

## App Screenshots 📸

Dive into **Melodiq** and explore its vibrant and intuitive design with these screenshots showcasing the heart of our app!

### Onboarding 🏠

Kickstart your musical adventure with our Onboarding screens. If you’re new to **Melodiq** and haven’t logged in yet, you’ll be greeted by our welcoming landing page. Here, you can get a sneak peek of what’s in store 📜 and easily navigate to login 🔒 or create a new account 🆕 to start your sonic journey.

### Sign In 🔑

Ready to join the **Melodiq** community? Log in with your email and password to access all the musical features awaiting you. If you’re not yet a member, don’t worry—our sign-up process is just a tap away! 🌟✉️

### Sign Up ✨

Welcome to **Melodiq**! 🎉 Setting up your account is a breeze and opens the door to a world of musical delights. Ready to create something amazing? 🎶

Simply enter your details: your name 📝, email 📧, and a secure password 🔒. Choose a password that’s memorable yet secure—your musical adventure is our priority!

Once you’ve filled in the fields, hit "Register" ✅ and you’re all set! 🎊 You’re now part of the **Melodiq** family, ready to explore and enjoy exclusive features. 🚀🌟


### Managing Your Profiles 👤

Step into the **Profiles** section where customization and ease meet to enhance your musical journey. Here’s how you can make **Melodiq** truly yours:

- **Profile Selection**: Choose which profile you’d like to use from the **Profile Selection** screen. It’s your personal space where you can keep track of favorite songs and tailored recommendations.

- **Creating Profiles**: Add up to four profiles to cater to family members or different users. Customize each profile with its own alias and avatar, making music a fun, personalized experience for everyone!

- **Editing Profiles**: Update your profile’s alias and avatar or change your security PIN with ease. Keep your account secure and reflect your personality in every way you choose.

- **Deleting Profiles**: If a profile is no longer needed, delete it and remove all associated favorites and data. This keeps your app clean and organized, focusing on the songs and features that matter most to you.

The **Profiles** section is all about flexibility and personalization, ensuring your musical journey is as unique as you are.

### Exploring the Home Screen 📱

Welcome to the vibrant **Home Screen**, the hub of your musical adventure. Here’s what awaits you:

- **Featured Songs Carousel** 🎠: Swipe through our rotating selection of standout songs. This dynamic showcase highlights popular and trending tracks to inspire your next musical creation.

- **Categories Row** 📊: Browse through a variety of music categories, from genres to playlists. Presented in a sleek horizontal list, it’s easy to find music based on your mood or current musical goals.

- **Personalized Music Recommendations** ⭐: Discover songs tailored to your taste and preferences. Based on your listening history and likes, this section offers suggestions that are perfect for your musical journey.

The Home Screen is designed to make your exploration of music delightful and engaging. Whether you’re checking out featured tracks, exploring categories, or receiving personalized suggestions, everything is organized to enhance your musical experience.

## Exploring Songs and More 🎶✨

Step into the **Songs** section, your ultimate musical playground where your sonic adventures come to life! 🎉 This hub is designed to help you explore and enjoy a variety of songs that cater to your preferences and musical goals.

In this section, you’ll find a diverse array of categories and types to explore:

- **Song Types** 🌱🎵: Discover a wide range of songs tailored to different styles:
  - **Pop** 🌟: Catchy melodies and vibrant rhythms that are in vogue.
  - **Rock** 🎸: Energetic songs that make you move.
  - **Classical** 🎻: Timeless compositions that delight the ears.
  - **Jazz** 🎷: Sophisticated rhythms that transport you to another level.

- **Categories** 📚: Browse through various song categories to find exactly what you want to listen to:
  - **Hits** 🎤: The most popular songs of the moment.
  - **New Arrivals** 📅: The latest additions to our catalog.
  - **Playlists** 📋: Thematic selections for different moods and occasions.

- **Special Features** ✨: Take advantage of our advanced filtering and sorting options 🔍. Customize your search based on genre, song type, or playlists to find the perfect track for any occasion.

Once you find a song that excites your taste buds, check out the **detailed view** 📋. Here you’ll get all the essential information, including the artist, album, release year, and any interesting facts, so you can enjoy it to the fullest.

The **Songs** section is crafted to be your personal musical hub, where every melody is an adventure waiting to be discovered. Whether you’re exploring new genres or searching for your next favorite track, this section has everything you need to make your musical experience enjoyable and rewarding. 🌟🚀



## License ⚖️

This project is licensed under the MIT License, an open-source software license that allows developers to freely use, copy, modify, and distribute the software. 🛠️ This includes use in both personal and commercial projects, with the only requirement being that the original copyright notice is retained. 📄

Please note the following limitations:

- The software is provided "as is", without any warranties, express or implied. 🚫🛡️
- If you distribute the software, whether in original or modified form, you must include the original copyright notice and license. 📑
- The license allows for commercial use, but you cannot claim ownership over the software itself. 🏷️

The goal of this license is to maximize freedom for developers while maintaining recognition for the original creators.

```
MIT License

Copyright (c) 2024 Dream software - Sergio Sánchez 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
