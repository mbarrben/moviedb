[![Build](https://github.com/mbarrben/moviedb/actions/workflows/build.yaml/badge.svg)](https://github.com/mbarrben/moviedb/actions/workflows/build.yaml)


# Movie DB Android Jetpack Compose Project


## Summary

Android playground project following Unidirectional Data Flow architecture


## Features

* Unidirectional Data Flow architecture.
* [Jetpack Compose](https://developer.android.com/jetpack/compose) for the UI.
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependency injection.
* Asynchrony implemented with [coroutines](https://developer.android.com/kotlin/coroutines).
* Navigation implemented with [Jetpack Navigation](https://developer.android.com/jetpack/compose/navigation).
* Rest API from [The Movie Db](https://www.themoviedb.org/).


## Usage

1. Checkout and import into *Android Studio Arctic Fox*.
2. Get a *The Movie Db* API key from https://www.themoviedb.org/documentation/api
3. Add a file named `private.properties` to the project root directory.
4. Add the API key to the `private.properties` file as follows:


```
API_KEY = <YOUR API KEY HERE>
```


## License

    Copyright 2021 Miguel Barrios Benito

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
