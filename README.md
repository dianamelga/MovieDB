# MovieDB
A challenge from Parser for Android

Support: Android 9 (API level 28) and above

### Libraries:
* [Coroutines](https://developer.android.com/kotlin/coroutines?gclid=Cj0KCQiA1NebBhDDARIsAANiDD2B00lMELY_y9xZpM0VLC097B1EmglbXfav9r2jO2uckqGVBP-cwoIaAnXvEALw_wcB&gclsrc=aw.ds) // Threading
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) // Lifecycle
* [Youtube API](https://developers.google.com/youtube/android/player?hl=es) // Video player
* [Retrofit](https://square.github.io/retrofit/) // Networking
* [Lottie](https://github.com/airbnb/lottie-android) // Animations
* [Glide](https://github.com/bumptech/glide) //Image handler


### The architecture overview:

<img width="852" alt="image" src="https://user-images.githubusercontent.com/24572020/202464407-a8fbc899-c60d-4612-9690-8fa6d7a4621a.png">

### Screenshots
<img width="440" alt="Screen Shot 2022-11-17 at 10 59 34" src="https://user-images.githubusercontent.com/24572020/202466311-d2ab2042-fb7a-4b41-a1c1-68ba2964c88c.png">
<img width="446" alt="Screen Shot 2022-11-17 at 10 59 54" src="https://user-images.githubusercontent.com/24572020/202466325-4a8aad9d-0260-4487-b0e5-19011ccd701b.png">
<img width="448" alt="Screen Shot 2022-11-17 at 11 00 06" src="https://user-images.githubusercontent.com/24572020/202466332-bda051d4-625f-4906-b2f2-8d4a2758af47.png">
<img width="456" alt="Screen Shot 2022-11-17 at 11 00 53" src="https://user-images.githubusercontent.com/24572020/202466336-48f578a3-ceb2-44f5-8c0e-e023fa4b6c63.png">

### Maintainability

 ***Ease of amending or adding a feature***
High. Side effects are restrained and since every part of the architecture has a well defined purpose, adding a feature is only a matter of creating a new isolated use case and plug it where needed.


### ToDos

 - Write Functional/Instrumental/UI Tests

