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

### Architecture

The app project uses MVVm (Model View ViewModel) architecture with a multi-module structure to separate *Presentation/Ui* from *Domain* and *Data* layer in the following hierarchy:

```
PRESENTATION/UI
     |
   DOMAIN
     |
   DATA
```

|Presenation/Ui Module|Description|
|-|-|
|app| Ui logic for displaying real estate *Listings* and *Details*. |
|core:ui| Contains *AppTheme* and re-usable ui components in Compose across the project. |

|Domain Module|Description|
|-|-|
|core:model| Domain models used across the project. |
|core:domain| *UseCases* to e.g. obtain real estate listings and commonly classes like *CoroutineDispatcherProvider*. |

|Data Module|Description|
|-|-|
|core:data| *Repositories* to e.g. fetch real estate data from apis. |

The main component chain looks like this:

```
[app]           MainActivity
                    |
                MainActivityUi
                    |  
                RealEstateListingsUi (Root navigation entry point)
                    |
                RealEstateListingsViewModel
                    |
[core:domain]   RealEstateUseCases
                    |
[core:data]     RealEstateRepository
                    |
                RealEstateApi
```


### Libraries / Tools

The project uses the following main libraries/tools:

- Kotlin + Coroutines
- Retrofit for Networking/Api
- UI
  - Compose, Material 3 
  - Coil for displaying images from remote
  - ComposeNavigation 3 
- DaggerHilt for dependency injection
- Testing
  - Junit4
  - Mockk
  - Turbine
- Ktlint for code formatting

## Out of Scope

At then end, I would like tho share the following things, which I haven't tackled in this challenge due to the fact, that it would exceed the scope in my opinion.

- Considering enum types in response objects.
- *Detekt* implemenation for finding code smells.
- Custom gradle plugins to centralizes build.gradle.kts configs for library/app modules.
- JUnit5 integration for unit testing.
