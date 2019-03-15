# Movie DB Android MVP Project [![Build Status](https://travis-ci.org/mbarrben/moviedb.svg?branch=master)](https://travis-ci.org/mbarrben/moviedb)


## Summary

Android project following MVVM architecture


## Features

* MVVM architecture with vertical features separated by packages:
** [DataBinding][1]
** [ViewModel][2]
** [LiveData][3]
* Asynchrony implemented with [coroutines][4].
* Dependency injection implemented with [Katana][5].
* Navigation implemented with Android architecture [navigation components][6]
* Rest API from [The Movie Db][7].


## Usage

1. Checkout and import into *Android Studio*.
2. Get a *The Movie Db* API key from https://www.themoviedb.org/documentation/api
3. Add a file named `private.properties` to the project root directory.
4. Add the API key to the `private.properties` file as follows:


```
API_KEY = <YOUR API KEY HERE>
```


## License

    Copyright 2019 Miguel Barrios Benito

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[1]: https://developer.android.com/topic/libraries/data-binding
[2]: https://developer.android.com/topic/libraries/architecture/viewmodel
[3]: https://developer.android.com/topic/libraries/architecture/livedata
[4]: https://kotlinlang.org/docs/reference/coroutines-overview.html
[5]: https://github.com/rewe-digital/katana
[6]: https://developer.android.com/guide/navigation/
[7]: https://www.themoviedb.org/
