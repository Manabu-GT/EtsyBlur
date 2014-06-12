EtsyBlur
===========

EtsyBlur is an Android library that allows developers to easily add a glass-like effect implemented in the 
[Etsy][1] app.

Screenshots:

Coming soon...

Try out the sample application:

<a href="https://play.google.com/store/apps/details?id=com.ms.square.android.etsydemo">
  <img alt="Android app on Google Play"
       src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>

Requirements
-------------
API Level 8 (Froyo) and above.

Setup
------
You just need to add the followings to your ***build.gradle*** file:

```
repositories {
    maven { url 'http://Manabu-GT.github.com/EtsyBlur/mvn-repo' }
}

dependencies {
    compile 'com.ms.square:etsyblur:0.1.0'
}

android {
    defaultConfig {
        renderscriptTargetApi 19
        renderscriptSupportMode true
    }
}
```

Usage
------
Using the library is really simple, just look at the source code of the [provided sample][2].

More detailed explanation will be coming soon...

License
----------

    Copyright 2014 Manabu Shimobe

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[1]: https://play.google.com/store/apps/details?id=com.etsy.android
[2]: https://github.com/Manabu-GT/EtsyBlur/tree/master/sample