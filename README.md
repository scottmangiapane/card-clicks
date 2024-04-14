## Card Clicks

Card Clicks is a fun and challenging card game in which players find as many clicks as they can before the timer runs out. A click is a group of three cards where each trait (color, shape, fill, or number) is all different or all the same for each card in the click.

## Download

https://github.com/scottmangiapane/card-clicks/releases

You can download the latest "app-release.apk" from the link above and sideload it to your Android device.

## Screenshots

<img src="screenshots/1_main.png" width="200">
<img src="screenshots/2_game.png" width="200">
<img src="screenshots/3_help.png" width="200">

## Build Instructions

* Create a new project in Android Studio  
  Package name: com.scottmangiapane.cardclicks
* Replace the contents of `/app/source/main/` with this repo
* Add dependencies to `/app/build.gradle`

## Build Requirements

* Android Studio
* Android Software Development Kit
* Java Development Kit

## Dependencies

In the project's `/app/build.gradle` file, add the following dependency.
```groovy
dependencies {
    compile 'com.android.support:appcompat-v7:24.1.1'
}
```
