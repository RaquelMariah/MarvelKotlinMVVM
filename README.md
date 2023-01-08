# Marvel Kotlin MVVM
An native android application developed in kotlin language and using MVVM pattern and dependency injection, coroutines and JUnit unit tests

For the development of this application, I used:

+ [Marvel API](https://developer.marvel.com)
+ [Kotlin](https://kotlinlang.org)
+ [Coroutines](https://developer.android.com/kotlin/coroutines?gclid=CjwKCAiAo5qABhBdEiwAOtGmbvyr0ukd-jlKwfu5vfgBcU1I0YcPDpfNwO8D2GWWAvE2FWGMrgigoRoCLAsQAvD_BwE&gclsrc=aw.ds)
+ [Retrofit](https://square.github.io/retrofit/)
+ [Room](https://developer.android.com/training/data-storage/room)
+ [DAO](https://developer.android.com/reference/android/arch/persistence/room/Dao)
+ [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
+ [LiveData](https://developer.android.com/topic/libraries/architecture/livedata?hl=pt-br)
+ [Dependency Injection](https://developer.android.com/training/dependency-injection/hilt-android?gclid=EAIaIQobChMIzfPAuu349wIVAkFIAB0TVgWXEAAYASABEgJnHPD_BwE&gclsrc=aw.ds)
+ [MVVM Architecture](https://developer.android.com/jetpack/guide?gclid=EAIaIQobChMI3NHD4ez49wIVAsORCh3M2gjYEAAYASAAEgLX0PD_BwE&gclsrc=aw.ds)

## MVVM Architecture

Model–view–viewmodel (MVVM) is a software architectural pattern that facilitates the separation of the development of the graphical user interface (the view) – be it via a markup language or GUI code – from the development of the business logic or back-end logic (the model) so that the view is not dependent on any specific model platform. 

In MVVM, the tight coupling between the layers of each component is ***reduced***, and there is no reference linked directly to the parent, it is only accessed by ***observers*** 

***Model*** refers either to a domain model, which represents real state content (an object-oriented approach), or to the data access layer, which represents content (a data-centric approach)

***View*** As in the model–view–controller (MVC) and model–view–presenter (MVP) patterns, the view is the structure, layout, and appearance of what a user sees on the screen. It displays a representation of the model and receives the user's interaction with the view (mouse clicks, keyboard input, screen tap gestures, etc.), and it forwards the handling of these to the view model via the data binding (properties, event callbacks, etc.) that is defined to link the view and view model.

***ViewModel*** The view model is an abstraction of the view exposing public properties and commands. Instead of the controller of the MVC pattern, or the presenter of the MVP pattern, MVVM has a binder, which automates communication between the view and its bound properties in the view model. The view model has been described as a state of the data in the model.
The main difference between the view model and the Presenter in the MVP pattern is that the presenter has a reference to a view, whereas the view model does not. Instead, a view directly binds to properties on the view model to send and receive updates. To function efficiently, this requires a binding technology or generating boilerplate code to do the binding.

 <p align="center">
  <img width="800" alt="flow" src="https://user-images.githubusercontent.com/45218570/211177481-9798ee7b-4c08-4086-97c7-47b7a99bc6dc.png">
  </p>
  
## Kotlin Flow

In coroutines, a flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value. For example, you can use a flow to receive live updates from a database.

Flows are built on top of coroutines and can provide multiple values. A flow is conceptually a stream of data that can be computed asynchronously. The emitted values must be of the same type. For example, a Flow<Int> is a flow that emits integer values.

A flow is very similar to an Iterator that produces a sequence of values, but it uses suspend functions to produce and consume values asynchronously. This means, for example, that the flow can safely make a network request to produce the next value without blocking the main thread.

There are three entities involved in streams of data:

A ***producer*** produces data that is added to the stream. Thanks to coroutines, flows can also produce data asynchronously.

(Optional) ***intermediaries*** can modify each value emitted into the stream or the stream itself.

A ***consumer*** consumes the values from the stream.

  <p align="center">
  <img width="800" alt="flow" src="https://user-images.githubusercontent.com/45218570/170884040-f8822e0e-87dd-4461-9231-d66eac6e83f0.png">
  </p>
  

## Dependency Injection (DI)

Dependency injection (DI) is a technique widely used in programming and well suited to Android development. By following the principles of DI, you lay the groundwork for good app architecture.

Implementing dependency injection provides you with the following advantages:

+ Reusability of code
+ Ease of refactoring
+ Ease of testing

Using dependency injection it is possible to reuse previously implemented codes and functionalities without the need for the class to receive all instances

  <p align="center">
  <img width="800" alt="flow"  src="https://user-images.githubusercontent.com/45218570/211177345-86bb34e5-a756-4fa9-a37e-80c492ca4b4d.png">
  </p>
  
  ## Live Data

  LiveData is a component from Android Jetpack. It's an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.
  
  <p align="center">
  <img width="600" alt="flow"  src="https://user-images.githubusercontent.com/45218570/211177621-193b7432-0a39-42c7-8191-3b4bc1d851db.png">
  </p>
  
  ## ViewModel

  ViewModel is a component from Android Jetpack. The ViewModel class is a business logic or screen level state holder. It exposes state to the UI and encapsulates related business logic. Its principal advantage is that it caches state and persists it through configuration changes. This means that your UI doesn’t have to fetch data again when navigating between activities, or following configuration changes, such as when rotating the screen.
  
  <p align="center">
  <img width="600" alt="flow"  src="https://user-images.githubusercontent.com/45218570/211177753-16e52657-b57b-4ac9-bc7b-68a906890ea6.png">
  </p>
  
  
   ## DAO

  Data Access Object is the system layer (packages, classes, and methods) that abstracts all database access separately from the application's business logic. It is in DAO that we implement the methods of CRUD (Create – Read – Update – Delete). As well as it is through DAO that operations on the database are performed.
  
  <p align="center">
  <img width="600" alt="flow"  src="https://user-images.githubusercontent.com/45218570/211177812-ea5fd725-93d8-477f-b885-98782ed84fba.jpg">
  </p>
  
  
   ## Room

  Room is a component from Android Jetpack.The most common use case is to cache important pieces of data so that when the device cannot access the network, the user
  can still access that content offline. The Room persistence library provides an abstraction layer on top of SQLite to allow fluent database access, leveraging the
  full power of SQLite. Room mainly offers these benefits:

+ Verification of SQL queries during compilation.
+ Convenience annotations that minimize repetitive and error-prone boilerplate code.
+ Simplified database migration paths.

  <p align="center">
  <img width="600" alt="flow"  src="https://user-images.githubusercontent.com/45218570/211177980-c248bdc3-498e-4802-8252-ffbc028ec9b4.png">
  </p>
  
     ## Retrofit

  Retrofit is an API developed by Square following the REST pattern, providing a simple implementation pattern for data transmission between application and server,
  which makes use of JSON. The purpose of this article is to present the HTTP connection Retrofit API for Android

  <p align="center">
  <img width="600" alt="flow"  src="https://user-images.githubusercontent.com/45218570/211178064-8845cf1f-9398-4545-a205-08e180bfc85c.png">
  </p>
  
  ## Coroutines

  coroutines help to manage long-running tasks that might otherwise block the main thread and cause your app to become unresponsive. Over 50% of professional
  developers who use coroutines have reported seeing increased productivity. This topic describes how you can use Kotlin coroutines to address these problems, enabling
  you to write cleaner and more concise app code.

  
