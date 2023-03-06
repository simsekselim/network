# Abstract

Kotlin network monitoring library for Android.

[![WebSite](https://img.shields.io/badge/-Android-gray?logo=android)](http://selimsimsek.com) [![WebSite](https://img.shields.io/badge/-Kotlin-gray?logo=kotlin)](http://selimsimsek.com)

## Installation

Use Gradle:

```gradle
repositories {
  google()
  mavenCentral()
}

dependencies {
  implementation 'com.github.simsekselim:network:1.1.0'
}
```

##Usage

```private fun observeNetworkConnection(){
        NetworkStatusHelper(applicationContext).observe(this) { networkStatus ->
            when (networkStatus) {
                is NetworkStatus.Available -> {
                    display(networkStatus)
                }
                is NetworkStatus.Unavailable -> {
                    display(networkStatus)
                }
            }
        }
    }

    private fun display(status: NetworkStatus) {
        binding.apply {
            when (status) {
                is NetworkStatus.Available -> {
                    animateNetworkLayout(true)
                }
                is NetworkStatus.Unavailable -> {
                    animateNetworkLayout(false)
                }
            }
        }
    }
```

 # Author
 [![Linkedin](https://img.shields.io/badge/-linkedin-grey?logo=linkedin)](https://www.linkedin.com/in/simsekselim/) [![Medium](https://img.shields.io/badge/-medium-grey?logo=medium)](https://medium.com/@simsekselim)  
