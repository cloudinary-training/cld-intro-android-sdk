# Introduction to Cloudinary's Android SDK
This repository is used in conjunction with the Cloudinary Academy training course **Introduction to Cloudinary's Android SDK**.

If you would like to take this xx minute course, you can enroll for free at [training.cloudinary.com](https://training.cloudinary.com).

# Course Outline:
The information below corresponds with the [slides](<>) used in [https://training.cloudinary.com/learn/course/<fill-in-later>]).

Course Topics:
- Introduction
  - Mobile Development
  - Environment Setup
- Upload
  - Upload
  - Pre Processing Image 
- Delivery
  - Transformations
  - Complex Transformations
  - Optimizations
- User Interface
  - Upload Widget
  - Cloudinary Native Video Player
  - Video Feed 
- Download
  - Glide
  - Picasso
  - Fresco
- Integration
  - Glide Integration
- Further Support

---

## Setup Environment

### Java
The Cloudinary Java SDK will run on Java 6 and any higher version. For purposes of training, we are using **Java Version 8**.

### IDE

We present this content using [Android Studio](https://developer.android.com/studio)

You may import the Java code used into your favorite IDE. You'll find the `MainActivity.java` in repo in the folder `app/src/main/java/com/cloudinary/academy_course`.

This is the entry point to static "runners" that contain code to help you upload, manage and transform your media.


### Install Java

- Java 6 or greater, we are be using version 8 in the course
- Java Download www.oracle.com/java/technologies/javase/javase-jdk8-downloads

You can find [instructions for downloading and installing Java ](https://www.java.com/en/download/help/index_installing.html) for many environments.

- Mac : brew install recommended https://brew.sh/


### Verify your version of Java is 6+

```
java -version

```
Sample output for Version 8

```bash
$ java -version
java version "1.8.0_311"
Java(TM) SE Runtime Environment (build 1.8.0_311-b11)
Java HotSpot(TM) 64-Bit Server VM (build 25.311-b11, mixed mode)
```

## Executing Code

### Providing Cloudinary Credentials

You will need to [register for a free Cloudinary account](https://cloudinary.com/users/register/free) in order to work along with this training.

It is recommended that you create a training account for this training to keep this work separate from any work you do in a production Cloudinary account.

You only need an email address that can be verified to set up this account.

Navigate to:  [https://cloudinary.com/console](https://cloudinary.com/console)

![cloudinary url](https://res.cloudinary.com/jen-brissman/image/upload/v1702021754/cld-url.png)

Cloudinary provides 3 credentials for developers to use in interacting with SDK's and Widgets:

1. CLOUD_NAME
2. API_KEY
3. API_SECRET

CLOUD_NAME and API_KEY can be revealed without creating any security issues, but you must keep API_SECRET private. Don't check it into GitHub and don't include it in blog posts.
- For this course, you only need your cloud name, so there won't be any risk of security. 

## Assets
All local assets used in training, are located in the `cld-intro-android-sdk/app/src/main/res
/drawable/` directory

---
## Resources

[Cloudinary Academy](training.cloudinary.com)

[Cloudinary Documentation](https://cloudinary.com/documentation)

[Cloudinary Academy GitHub Respositories](https://github.com/cloudinary-training)

[Cloudinary GitHub Repository](https://github.com/cloudinary)

[Support Request](https://support.cloudinary.com/hc/en-us/requests/new)

[Support Email support@cloudinary.com](mailto:support@cloudinary.com)

## Asset Credits
- lorikeet.jpg [image from Pexels by Mark Broadhurst](https://www.pexels.com/photo/blue-orange-and-green-bird-on-yellow-flower-105808/)
- aerial.jpg [image from Pexels by Oliver Sjöström](https://www.pexels.com/photo/body-of-water-near-green-mountain-931018/)
- boats.jpg [image from Pexels by Vincent Rivaud](https://www.pexels.com/photo/white-boats-on-body-of-water-2265876/)
- coconuts.jpg [image from Pexels by Oliver Sjöström](https://www.pexels.com/photo/person-standing-on-dirt-surrounded-by-coconut-trees-1005417/)
- swing.jpg [image from Pexels by Gerald Yambao](https://www.pexels.com/photo/photo-of-boy-swinging-over-body-of-water-2413238/)
- coffee-with-a-view.jpg - [image from Pexels by Andy Vu](https://www.pexels.com/photo/man-in-brown-jacket-and-brown-hat-standing-on-rock-near-lake-3217911/)
- backpack.mp4 - [video from Pexels by Mikita Yo](https://www.pexels.com/video/a-man-standing-still-on-a-cliff-edge-8644064/)
- waterfall.mp4 - [video from Pexels by Beckett Johnson](https://www.pexels.com/video/footage-of-waterfalls-5022215/)
- kids-hiking.mp4 - [video from Pexels by RDNE Stock Project](https://www.pexels.com/video/two-boys-walking-on-a-park-8083560/)
- cove.mp4 - [video from Pexels by Tima Miroshnichenko](https://www.pexels.com/video/sea-waves-crashing-the-cliff-coast-6010489/)
