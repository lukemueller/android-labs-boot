#Pivotal Labs Android Boot

##Task:
Create an Android Application that retrieves, parses, and displays the beverages available on the LCBO Api (or any other searchable list API you choose). The application should have a page allowing the user to enter a search term or select a category, a page that displays the search/category results, and a details screen.

The behind the scenes specifics are up to you, but I would like you to be very careful with dependencies that you decide to bring in though. This is an exercise to learn the way the Android ecosystem works, and some libraries can hide that important information away. 

There is a list of things that you may want to look into at the bottom of this document. There is also a few small sections that you can read to get a quick start with Robolectric testing, an intro to dependency injection with Dagger, and important Android terms with links/definitions to help you through.

Once completed, if you would like feedback I am more than willing to go through your application with you and give the highlights and places for improvement in the application. If you can put the source up on Github, and send me a link I will go over the code and then we can set up time to sit down and talk about how you came to the certain decisions that you did. 

It is also important to remember that you do not have to follow the suggestions that I put forward through the notes at the bottom, having differing opinions will allow us both to grow and give good talking points and learning opportunities. So if you feel strongly about something, go with your gut and we’ll most likely talk through pros/cons.

There are a few musts for this application:
- Code should be fully tested
- All network should be non-blocking and done off the UI thread
- All images should be lazy-loaded into their views
	
##Setting up the repo and Android Studio:

1. [Install Android Studio](https://developer.android.com/sdk/index.html)
1. Run the SDK Manager and download relevant api levels
1. Clone Android Labs Boot repo
1. Test repo with :
    ```
        ./gradlew clean test
    ```
1. Import the build.gradle file into Android Studio
1. Make sure that your build variant is set to 'Unit Tests'
1. Test, Code, Build!

So that’s it for setup, go forth and explore!

##Android Knowledge:
	
The Android framework is something that will take some reading and time to get the hang of. It is sometimes hard to work with, but once you understand how it all works then there is a lot of built in functionality that will help you create a streamlined app. 
Google has a great site that you can bring you from fresh to Android master, but since it’s a lot of reading, I have highlighted some of the places that you should be helpful to you as you go through this Bootcamp:

* [Manifest](http://developer.android.com/guide/topics/manifest/manifest-intro.html) (Presents application information to the system)
* [Resources](http://developer.android.com/guide/topics/resources/overview.html) (Externalised values for Strings, Assets, etc.) 
* [Intent](http://developer.android.com/guide/components/intents-filters.html) (Messaging object used to communicate with other app components)
* [Activity](http://developer.android.com/guide/components/activities.html) (High level UI application element with a lifecycle handled by the system)
* [Fragment](http://developer.android.com/guide/components/fragments.html) (UI element that has it’s own lifecycle handled by its owner Activity)
* [User Interface](http://developer.android.com/guide/topics/ui/index.html) (Layouts, Views, Navigation, Standards, etc.)
* [Threading](http://developer.android.com/guide/components/processes-and-threads.html) (Threads, Asynctasks, Handlers, Executors, etc.)
* [Services](http://developer.android.com/guide/components/services.html) (Component that runs parallel in the background with no UI component)
* [Loaders](http://developer.android.com/guide/components/loaders.html) (Help asynchronously load data to your UI) 
* [Networking](http://developer.android.com/reference/java/net/HttpURLConnection.html) (HttpUrlConnection is the standard, but there are other options)
* [Content Provider](http://developer.android.com/guide/topics/providers/content-providers.html) (Manages access to data across your app, and can export data)
* [Back Stack](http://developer.android.com/guide/components/tasks-and-back-stack.html) (How the system handles pressing back)

Simple Google searches can also be valuable when looking for answers, help, or examples of the things that are troubling you. An important thing to remember when doing this is that you can run into trouble when you are taking code pieces from the internet. Always make sure that you fully understand what and why the piece is doing what it’s doing before implementing the same pattern. There are lots of ways to do many things in Android, and you want to make sure you know why you chose the one you did.

##Testing and Dependency Injection:

###[Robolectric](http://robolectric.org/)
> At Labs, we use Robolectric to test Android applications because it allows us to fully test the Android ecosystem without the need to run on device. This does provide us a layer of complexity that isn’t always easy to figure out (the Bootcamp repo has Robolectric 3.0rc2, if you’ve used 2.4 or below in the past, here is an article about what has changed).

###[Fest/AssertJ](https://github.com/square/assertj-android)
> Fest is an assertion library that has assertions built specifically for Android. This allows us to write our tests in a much more readable fashion. It also is designed to give more detailed error messages than you would get from normal assertion libraries.

###[Dagger](http://square.github.io/dagger/)
> Dagger is currently the standard for Android dependency injection. It’s fairly simple to use, but does require a little bit of setup. The Bootcamp should have an initial flow setup for the example, plus the documentation is very useful to read through.

###[Mockito](http://mockito.org/)
> Mockito is a mocking library for Java. It allows us to mock out components in tests that we don’t need to allow for simpler, cleaner unit tests.

It sometimes becomes a little difficult in Android to keep your tests to be Unit Test-like as possible. This stems from having to fire up the Android ecosystem and an Activity to tests a lot of things. Dependency Injection paired with separation of responsibilities can get us fairly close. If you look at the example test that begins in the Bootcamp repo, you will see all of these libraries being used together in order to keep everything simple, clean, and readable. 
