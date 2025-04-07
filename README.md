# stickynoteskotlin
StickyNotes in kotlin

This is me rewriting stuff that should not have to be rewritten.
I am taking a working note taking app that was written in java and converting it to kotlin with compose screens instead of xml.
I am using sqlite because it is easier and more readable than room.

Got the preferences in at last. For some reason the cultists at goggle have hidden the preference API. When searching for sharedpreferences all you get as answers is room database. In their religious zeal they have deprecated all the old preferences, and then just renamed them so that you can no longer find them. So instead of a PreferenceFragment, it is a PreferenceFragmentCompat, even though they are exactly the same thing.
Though I have to be honest, as much as I detest the bleating from the goggle cultists about reduced boiler plate, replacing the recycler adapter with a lazyColumn is pretty clever.
(What the cultists call boilerplate code I call the ability to see what is going on. Have you tried debugging a composable? That's all I'm asking, have you tried? Lambda's are just as bad.)

Missing all the "boilerplate" like font files, asset files, menus and xml files.
