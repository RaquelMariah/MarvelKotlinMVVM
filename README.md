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
+ [LiveData (Flow)](https://developer.android.com/topic/libraries/architecture/livedata?hl=pt-br)
+ [Dependency Injection](https://developer.android.com/training/dependency-injection/hilt-android?gclid=EAIaIQobChMIzfPAuu349wIVAkFIAB0TVgWXEAAYASABEgJnHPD_BwE&gclsrc=aw.ds)
+ [MVVM Architecture](https://developer.android.com/jetpack/guide?gclid=EAIaIQobChMI3NHD4ez49wIVAsORCh3M2gjYEAAYASAAEgLX0PD_BwE&gclsrc=aw.ds)

## MVVM Architecture

Model–view–viewmodel (MVVM) is a software architectural pattern that facilitates the separation of the development of the graphical user interface (the view) – be it via a markup language or GUI code – from the development of the business logic or back-end logic (the model) so that the view is not dependent on any specific model platform. 

In MVVM, the tight coupling between the layers of each component is ***reduced***, and there is no reference linked directly to the parent, it is only accessed by ***observers*** 

***Model*** refers either to a domain model, which represents real state content (an object-oriented approach), or to the data access layer, which represents content (a data-centric approach)

***View*** As in the model–view–controller (MVC) and model–view–presenter (MVP) patterns, the view is the structure, layout, and appearance of what a user sees on the screen. It displays a representation of the model and receives the user's interaction with the view (mouse clicks, keyboard input, screen tap gestures, etc.), and it forwards the handling of these to the view model via the data binding (properties, event callbacks, etc.) that is defined to link the view and view model.

***ViewModel*** The view model is an abstraction of the view exposing public properties and commands. Instead of the controller of the MVC pattern, or the presenter of the MVP pattern, MVVM has a binder, which automates communication between the view and its bound properties in the view model. The view model has been described as a state of the data in the model.
The main difference between the view model and the Presenter in the MVP pattern is that the presenter has a reference to a view, whereas the view model does not. Instead, a view directly binds to properties on the view model to send and receive updates. To function efficiently, this requires a binding technology or generating boilerplate code to do the binding.


![MVVMPattern](https://user-images.githubusercontent.com/45218570/170117546-13d9313d-4ef1-4086-a193-a373789c3ed1.png)

## Kotlin Flow

In coroutines, a flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value. For example, you can use a flow to receive live updates from a database.

Flows are built on top of coroutines and can provide multiple values. A flow is conceptually a stream of data that can be computed asynchronously. The emitted values must be of the same type. For example, a Flow<Int> is a flow that emits integer values.

A flow is very similar to an Iterator that produces a sequence of values, but it uses suspend functions to produce and consume values asynchronously. This means, for example, that the flow can safely make a network request to produce the next value without blocking the main thread.

There are three entities involved in streams of data:

A ***producer*** produces data that is added to the stream. Thanks to coroutines, flows can also produce data asynchronously.
(Optional) ***intermediaries*** can modify each value emitted into the stream or the stream itself.
A ***consumer*** consumes the values from the stream.
  
  <img width="454" alt="flow" src="https://user-images.githubusercontent.com/45218570/170884040-f8822e0e-87dd-4461-9231-d66eac6e83f0.png">
