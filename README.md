**Mynt Weather App**

Weather applications provide weather information of the user using his current location. User need to register himself in the weather application. Once user  successfully registered, user need to login using his valid credentials. Once user successfully logged-in he lands to home page. In home page app have two tabs, first tab shows the user current weather and the second tab shows the weather history.

**The application also provide some other features such as:**

1. Get Current temperature in Celsius
2. Show the time of Sunrise and Sunset
3. Current location (City & Country)
4. Get Current weather Details
5. Save current weather detail whenever user opens the application
6. Based on the sunrise and sunset time application will show the different images like the sun or moon images

**Project details:**

Architecture: MVVM

Android Studio Version: Android Studio Hedgehog | 2023.1.1

Programming Language: Kotlin(1.8.0 and above)

Device Supported: Android (Above API 23 - "Marshmallow", Android 6.0)

**NOTE:**

Need to use your own OpenWeather API Key at below mentioned path

App build.gradle -> API_KEY

**NOTE:**

Need to update the database pass code for database encryption at below mentioned path

App build.gradle -> DB_PASS

**Project Structure:**

- Inside main folder we have java->com->aditya->weather
- Under the database folder we have covered database dao, and entities.
- Under the di folder we have covered dependency injection for the room db and retrofit.
- Under the model folder we have weather responses classes.
- Under the network folder we have a network api call endpoint.
- Under the ui folder we have all activity, fragments and adapters.
- Under the utils folder we have covered all commonly used util classes.
- Under the viewmodel folder we have a viewmodel for login and weather.

**Security:**

- Room Database is protected using SQl Cipher
- Shared preference is protected using SecuredSharedPreference
- Have enabled progard and minify

**Unit Test:**

- Test cases for Repository, Utils class, API call and ViewModel. Have above 95% test coverage
  
![test_cov](https://github.com/adityaper/MyntWaetherAppAssignment/assets/131134693/5c18f06b-d9f8-4fbb-b51b-063f025585ab)

**Code Repository Location**

- Please refer to the above git location for commit history.
  
[https://github.com/adityaper/MyntWaetherAppAssignment](https://github.com/adityaper/MyntWaetherAppAssignment)

**App Screens:**

**Splash Screen**

![Splash Screen](https://github.com/adityaper/MyntWaetherAppAssignment/assets/131134693/6d58f463-8446-4888-be91-e6fb0f17e6d9)


**Login Screen**

![Login Screen](https://github.com/adityaper/MyntWaetherAppAssignment/assets/131134693/1c0215af-85e9-46c0-a0ec-5ffc04aa6bfe)


**SignUp screen**

![SignUp Screen](https://github.com/adityaper/MyntWaetherAppAssignment/assets/131134693/ce16c5fa-b5c7-433b-ba0b-2fb4538fb61b)


**Home Screen(Current Weather)**

![Home Screen(Current Weather)](https://github.com/adityaper/MyntWaetherAppAssignment/assets/131134693/d8c857a1-fad1-4d59-b9d5-6e49f2c1f1a0)


**Home Screen(List of History)**

![Home Screen(List of History)](https://github.com/adityaper/MyntWaetherAppAssignment/assets/131134693/a9cb037d-7ee0-445f-b0a7-fe3d73bed2eb)


**Permission Dialog**

![Permission Dialog](https://github.com/adityaper/MyntWaetherAppAssignment/assets/131134693/84ccb593-7d46-47b4-a098-c4c5501a6456)


**NOTE:** Being the only contributor I have put all code in master directly.
