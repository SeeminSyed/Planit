# Summary

## Project objectives:
The objectives of the planit app are as follows:

- Generate itineraries based on a set of given data, such as location, budget, interests, etc. This is the main feature of the app.

- Implement functionality for users to modify their itineraries by changing the events that are given to them with the introduction of a change button. This will show the user a set of alternatives that they can choose from.

- Give users personalized itineraries based on ratings of previous experiences, and the user’s personal interests.

- Provide users the ability to access maps and booking sources within the app so they don’t have to navigate to different platforms.

## Key users:
- Allison Davis – A twenty-eight-year-old bank teller. She lives in the York region and loves to see new things with her boyfriend Jason. She wants to begin planning her own trips. She isn’t very tech savvy. She hates walking far distances and driving downtown, so she usually uses her PRESTO card to get around.

- Mark Walter – A forty-one-year-old father of four. He is strong with technology. Because Mark owns several franchises, he isn’t worried about money. When Mark goes on a trip, he wants to see all there is to see. He can drive, and when he goes on trips, he plans to. Growing up in New York City has blessed Mark with a great sense of direction.

- Justin Marc – A twenty-one-year-old UofT Computer Science student who needs a break from all the code. Justin lives in downtown Toronto. He’s also unparalleled when it comes to technology. Slowly over time, he’s been expanding his social circle. He’s met some party friends and has found himself going out clubbing more and more. There’s only one problem–- he barely gets the scene at all! Not to mention, Justin is flat broke. This means that Justin will be walking everywhere and spending as little as possible. 

## Scenarios:
- As a user, I want to be able to rate a completed activity so that the next generated itinerary will be personalized.

  * Use a database handler class to insert the rating and preference of the type of the event into the database. The next time this user creates an itinerary, use this rating value in our algorithm for preferred events. 

- As a user, I want to be able to enter my location (i.e. city and country) manually.

  * Have an input method that takes in text. Have one for city and one for country.

- As a user, I should be able to choose the number of people that the itinerary is being planned for.

  * Have a numerical input for the number of people. Use this to get the user’s chosen number.

- As a user, I should be able to choose a time range for the itinerary.

  * Use a time input method to get the time that the user chose.

- As a user, I want to be able to see a map to help me navigate from one location to another from within the application.

  * Use google/apple maps and location services to display a map with directions between locations.

- As a user, I want to be able to choose from a selection of attractions so that the itinerary reflects my interests.

  * Have the users select filters that are related to itineraries. During itinerary creation, ignore any events that don’t fall under these selected filters.

- As a user, I want to choose a transportation method that works with my budget.

  * Use an input option that lets a user select from a set of options.

- As a user, I want to choose a medium budget as I can afford a nicer (but still affordable) trip.

  * Use a numerical input option to record the given budget.

- As a user, I want to provide a maximum distance between my activities so that I am able to get around easily.

  * Use a numerical input option to record the given maximum distance.

- As a user, I want to be able to change an activity.

  * Have a display for each event. On the change button on the display, when it is tapped, a view of alternate events will be shown. Tapping this event will open details about it, and selecting it will swap it with the given event.

## Principles:

- Create an itinerary for users that is tailored to their preferences based on their previous ratings of other events. 

- Eliminate the frustration of planning trips using multiple different services by streamlining all trip planning through the application.


