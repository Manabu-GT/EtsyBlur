EtsyBlur
===========

EtsyBlur is an Android library that allows developers to easily add a glass-like effect 
implemented in the [Etsy][1] app.

<img src="https://raw.github.com/Manabu-GT/EtsyBlur/master/art/readme_demo.gif" width=244 height=415 alt="Quick Demo">

Try out the sample application:

<a href="https://play.google.com/store/apps/details?id=com.ms.square.android.etsyblurdemo">
  <img alt="Android app on Google Play"
       src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>

Requirements
-------------
API Level 8 (Froyo) and above.

Setup
------
The library is pushed to Maven Central as an AAR, 
so you just need to add the followings to your ***build.gradle*** file:

```groovy
dependencies {
    compile 'com.ms-square:etsyblur:0.1.3'
}

android {
    defaultConfig {
        renderscriptTargetApi 23
        renderscriptSupportMode true
    }
}
```

Usage
------
Using the library is really simple, just look at the source code of the [provided sample][2].

### For NavigationDrawer

* `EtsyActionBarDrawerToggle`

Simply replace the ActionBarDrawerToggle class by this class to get the blur effect.

Also, your layout file for the main activity should look like the following.
To make the library work, you have to use the id 'container' for the content frame and 'blur_view'
for the ImageView to show the blurred image.

```xml
<android.support.v4.widget.DrawerLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">
    
    <FrameLayout
        android:id="@id/container"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

    <!-- needed w/this id for EtsyLib to work -->
    <ImageView
        android:id="@id/blur_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:scaleType="centerCrop"
        android:visibility="gone">
    </ImageView>

    <fragment android:id="@+id/navigation_drawer"
        android:layout_width="@dimen/navigation_drawer_width"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:name="com.ms.square.android.etsyblurdemo.NavigationDrawerFragment"
        tools:layout="@layout/fragment_navigation_drawer" />

</android.support.v4.widget.DrawerLayout>
```

###  For DialogFragment

* `BlurDialogFragmentHelper`

This is a helper class to make the dialog background into the blurred image of the underlying content.
To make it work, instantiate this class in your DialogFragment(ex.. in onCreate) and 
forward the following methods in it to this helper class.

 - public void onCreate()
 - public void onActivityCreated()
 - public void onStart()
 - public void onDismiss()

Note:

In your DialogFragment, override either onCreateDialog() or onCreateView(), not both.
For a simple example, please look at the BlurDialogFragment in [provided sample][2].

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
