# Movie DB Android MVP Project


## Summary

Android project following MVP architecture heavily based on https://github.com/saulmm/Material-Movies


## Features

* MVP architecture with separate Java modules for model and domain.
* Asynchronous communication between modules implemented with [RxJava][1] and [RxAndroid][2].
* Dependency injection implemented with [Dagger][3].
* Rest API from [The Movie Db][4].


## Usage

1. Checkout and import into *Android Studio*.
2. Get a *The Movie Db* API key from https://www.themoviedb.org/documentation/api
3. Add a file named `private.properties` to the project root directory.
4. Add the API key to the `private.properties` file as follows:


    API_KEY = <YOUR API KEY HERE>


## Bibliography

* [A useful stack on android][5] by [Saul MM][6] for MVP architecture and UI
* [EffectiveAndroidUI][7] by [Pedro Vicente Gómez Sánchez][8] for dependency injection


## License

    Copyright 2015 Miguel Barrios Benito

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[1]: https://github.com/ReactiveX/RxJava
[2]: https://github.com/ReactiveX/RxAndroid
[3]: http://square.github.io/dagger/
[4]: https://www.themoviedb.org/
[5]: http://saulmm.github.io/2015/02/02/A%20useful%20stack%20on%20android%20%231,%20architecture/
[6]: https://github.com/saulmm
[7]: https://github.com/pedrovgs/EffectiveAndroidUI
[8]: https://github.com/pedrovgs
