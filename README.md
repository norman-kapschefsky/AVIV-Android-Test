# Android Coding Challenge

## Task

```
For this test, you have to create a simple application, with 2 main screens:
- List page : a real estate listing page.
- Details page : a details page allowing you to explore each item of the list previously created.

You can choose the architecture and tools you want.
It is also possible to use external libraries.
Only prerequisite: 100% Kotlin code
```

## Solution

Real Estate *Listings* and *Details* are implemented for:
- Phones and Tablets
  - To reduce complexity, there are no specific ui adjustments for certain device types which would lead into a better user experience. Here, it just scales.
- Portrait and Landscape device orientation
  - Note that a width above 600dp shows content in *TwoPane* layout on a single screen.
- Dark and Light mode

Here is an overview of what it looks like.

|Device|Screenshots|
|-|-|
|Phone Portrait, Dark mode| <img src="/docs/image_phone_listings_portrait.png" width=180/> <img src="/docs/image_phone_details_portrait.png" width=180/> |
|Phone Landscape, Dark mode| <img src="/docs/image_phone_listings_landscape.png" height=180/> <img src="/docs/image_phone_details_landcape.png" height=180/> |
|Tablet Portrait, Light mode| <img src="/docs/image_tablet_listings_portrait.png" width=180/> <img src="/docs/image_tablet_details_portrait.png" width=180/> |
|Tablet Landscape, Light mode| <img src="/docs/image_tablet_listings_landscape.png" height=180/> <img src="/docs/image_tablet_details_landcape.png" height=180/> |
