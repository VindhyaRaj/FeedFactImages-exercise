Android Proficiency Exercise


* An app that consumes a REST service and displays photos with headings and descriptions.

* Json feed Ingested from : https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json

* Code is in KOTLIN

* HTTP library for networking - Retrofit

* Other 3rd party apis are used such as Picasso & Gson


UI Specifications :

 --> The feed contains a title and a list of rows.

 --> The content is displayed (including image, title and description) in a ListView

 --> The title in the ActionBar is updated from the json data.

 -->Each row should is dynamically sized to the right height to display its content - no clipping, no
extraneous white-space etc. This means some rows are larger than others.

 --> Loaded the images lazily (Used Picasso library for that).

 --> Implemented pull to refresh function allowing the data & view to be updated(SwipeRefreshLayout)

 --> Non blocking UI while the data is being loaded.

Testing :

 --> Manually verified on AVD as well as testing device

 --> Unit tested with AndroidJUnit4

 --> Verified various scenarios like in network being available/ not available etc.

 Improvisations :

 --> For much more complex feeds,  MVVM pattern along with data binding for clean and modular code

 --> Dependency Injection can be used for things like getting a retrofitclient etc.
