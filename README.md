#  Currencies Rates

A Revolut assignment for Android engineering position provided by Mohamed Nabil.

## Features

### Non-technical features

- Display rates every 1 second.
- List all currencies you get from the endpoint (one per row).
- Each row has an input where a user can enter any amount of money. 
- When a user taps on currency row it should slide to the top and its input becomes the first responder.
- When the user changing the amount the app must simultaneously update the corresponding value for other currencies.

### Technical features

- Written completely in Kotlin.
- Built with Clean Architecture.
- Used Android Jetpack MVVM.
- Used RxJava2.
- Used Dagger2.
- Provide complete unit tests.
- Provide Espresso tests.
- Used Material design.
- Multi-modular project structure.

## Running the app

##### Using android studio

Just import the project and hit `run` button.

##### Using command line 

```
$ adb shell am start -n 'com.mohamedmenasy.revoluttask/com.mohamedmenasy.revoluttask.core.navigation.RouteActivity'
```

## Running the tests

##### Unit Tests

###### To run the core tests

```bash
./gradlew testDebugUnitTest --tests com.mohamedmenasy.core.*
```

###### To run the features tests 

```bash
./gradlew testDebugUnitTest --tests com.mohamedmenasy.revoluttask.*
```

##### UI tests 

###### To run RatesFragmentTest Test:

```
$ adb shell am instrument -w -r -e debug false -e class 'com.mohamedmenasy.revoluttask.features.rates.RatesFragmentTest' com.mohamedmenasy.revoluttask.features.rates.test/androidx.test.runner.AndroidJUnitRunner
```

## Todo

1. Using [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) instead of RxJava2.
2. Using [RxBinding](https://github.com/JakeWharton/RxBinding) for fully reactive design.
3. Using [AdapterDelegates](https://github.com/sockeqwe/AdapterDelegates).
4. Explore [RxData](https://github.com/revolut-mobile/RxData) by Revolut Mobile.
5. Enhance the project structure modules.
6. Add more Espresso test cases.

## Author

- **Mohamed Nabil** - *Initial work* - [linkedin](<https://www.linkedin.com/in/mohamedmenasy/>)