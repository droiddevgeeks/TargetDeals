# Android Case Study

You have been given control over an Android project that was originally a proof-of-concept project.
The original developer of the project has since moved on to a new team, and Target has asked you to
turn the project into an application that the company could release to the public.

The goal of the app is to display a list of deals currently offered by Target, and to provide
information on those deals. As a POC, the app has a few deals hardcoded in and the code is
pretty rough. It is your job to turn this app into something useful!

**Requirements:**
1. Fix up the deals list to match the mockups shown in mockups/DealsList.png.
  Fonts, specific colors, and specific margins are not important, but you should aim to to get close.

2. Present a new view that displays deal details when a deal is clicked on the list screen.
  Use the mockups shown in mockups/DealsDetails.png. Again, specific sizing and other design details
  are not important.

3. The deals are currently hardcoded. Use the API at https://api.target.com/mobile_case_study_deals/v1
  to grab the real deals to display in the app.

4. Update PaymentDialogFragment to perform credit card number validation:
     - See Validators.kt under the `data` package for instructions on performing the validation,
       including  help on getting fake credit card numbers. You do *not* need to enter real credit
       card data.

       You are welcome to make any changes to `Validators.kt` as you see fit within the guidelines
       noted in the documentation in that file.
     - When a valid number has been entered, the "submit" button should be enabled. When the number
       is invalid, the "submit" button should be disabled.
     - You do *not* need to make any changes to the dialog layout, but you are welcome to do so if
       you wish.

5. ***Optional:*** Do something interesting! Add something to the app you think can really make it
  fun to use or showcases a particular skill you have. Maybe you love creating animations and want
  to show that off, or you are really excited about a new library and you just want to experiment
  with it. You will not be evaluated on whether or not you add extra functionality, but it
  can be a good way to start a discussion and highlight something you are passionate about.

Some guidelines:
- Feel free to use any open source software you wish. Be prepared to answer questions about *why*
  you chose any libraries that you add to the project.
- Treat this app as something you control technically. The way you chose to architect your
  solution will be a key aspect of  the case study review.
- Do your best to follow modern Android conventions and write clean, performant, and extensible code.
  Imagine that this app will continue to grow after you are done- how would another developer
  add additional screens or other functionality?
- While writing tests is not a requirement, be prepared to discuss how you would approach testing
  this project during the review. Having tests is one way to kickstart that discussion and showcase
  your approach.
- Be sure the app looks great on a variety of screen sizes!

### Application Demo
<img src="https://github.com/droiddevgeeks/TargetDeals/blob/master/mockups/DealsDemo.gif" width="30%" height="40%" />



### Libraries
* [Android Support Library][support-lib]
* [Android Architecture Components][arch]
* [Android Data Binding][data-binding]
* [Hilt][Hilt] for dependency injection
* [Retrofit][retrofit] for REST api communication
* [mockito][mockito] for mocking in tests


[Gson](https://code.google.com/p/google-gson/) is another popular choice and being a smaller library than Jackson, you might prefer it to avoid 65k methods limitation. Also, if you are using  

<a name="networklibs"></a>
**Networking, caching.** There are a couple of battle-proven solutions for performing requests to backend servers, which you should use rather than implementing your own client. We recommend basing your stack around [OkHttp](http://square.github.io/okhttp/) for efficient HTTP requests and using [Retrofit](http://square.github.io/retrofit/) to provide a typesafe layer. 


**RxJava** is a library for Reactive Programming, in other words, handling asynchronous events. It is a powerful paradigm, but it also has a steep learning curve. We recommend taking some caution before using this library to architect the entire application. We have written some blog posts on it: [[1]](http://blog.futurice.com/tech-pick-of-the-week-rx-for-net-and-rxjava-for-android), [[2]](http://blog.futurice.com/top-7-tips-for-rxjava-on-android), [[3]](https://gist.github.com/staltz/868e7e9bc2a7b8c1f754), [[4]](http://blog.futurice.com/android-development-has-its-own-swift). For a reference app, our open source app [Freesound Android](https://github.com/futurice/freesound-android) makes extensive use of RxJava 2.

If you have no previous experience with Rx, start by applying it only for responses from app's backend APIs. Alternatively, start by applying it for simple UI event handling, like click events or typing events on a search field. If you are confident in your Rx skills and want to apply it to the whole architecture, then write documentation on all the tricky parts. Keep in mind that another programmer unfamiliar to RxJava might have a very hard time maintaining the project. Do your best to help them understand your code and also Rx.

Use [RxAndroid](https://github.com/ReactiveX/RxAndroid) for Android threading support and [RxBinding](https://github.com/JakeWharton/RxBinding) to easily create Observables from existing Android components.

A collection of samples using the [Architecture Components](https://developer.android.com/arch):

- [Lifecycle-aware components](https://developer.android.com/topic/libraries/architecture/lifecycle)
- [ViewModels](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)


### Test Frameworks

**Use [JUnit](https://developer.android.com/training/testing/unit-testing/local-unit-tests.html) for unit testing** Plain, Android dependency-free unit testing on the JVM is best done using [Junit](https://junit.org). 

#### Local Unit Tests
##### ViewModel Tests
Each ViewModel is tested using local unit tests with mock Repository
implementations.
##### Usecase Tests
Each usecase is tested using local unit tests with mockito & junit
##### Repository Tests
Each Repository is tested using local unit tests with mockito & junit

[Medium]https://medium.com/mindorks/effective-livedata-testing-13d17b555d9b

https://medium.com/mindorks/unit-testing-for-viewmodel-19f4d76b20d4

https://medium.com/mindorks/unit-testing-viewmodel-part-2-4a1fa93d656d
