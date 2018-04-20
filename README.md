# Little Magical Inn
This project is an answer to the interview coding project proposed by Ovia Health.

## Technologies Used
Java 8, Spark, Jackson

## Setting up your environment
* Install JRE 1.8 or higher on your machine if it isn't installed already (it probably is).
* Pull the code down to your local machine, either using Git or downloading the zip file and extracting it.

## Running the application
* Open the appropriate command-line tool for your environment
* Navigate to the directory where you downloaded the application
* For Unix-based systems, run the following command: './gradlew run'
* For Windows-based systems, run the following command: './gradlew.bat'

## Using the API
### Navigation
The base route for the API is 'http://localhost:8008/api/v1'
### Available Rooms Endpoint
Route: `/availableRooms`

Method: POST

POST Request Body Example:
```
{
	"date": "2018-04-20",
	"numberOfGuests": 2,
	"amountOfLuggage": 1
}
```

POST Response Body Example:
```
[
    {
        "date": 1524096000000,
        "room": {
            "roomName": "Room 1",
            "guestCapacity": 2,
            "storageCapacity": 1
        },
        "numberOfGuests": 0,
        "amountOfLuggage": 0,
        "full": false
    },
    {
        "date": 1524096000000,
        "room": {
            "roomName": "Room 2",
            "guestCapacity": 2,
            "storageCapacity": 0
        },
        "numberOfGuests": 0,
        "amountOfLuggage": 0,
        "full": false
    }
]
```
### Request Booking Endpoint
Route: `/booking`

Method: POST

POST Request Body Example:
```
{
	"date": "2018-04-19",
	"numberOfGuests": 2,
	"amountOfLuggage": 0
}
```

POST Response Body Example:
```
{
  "date" : 1524207600000,
  "room" : {
    "roomName" : "Room 1",
    "guestCapacity" : 2,
    "storageCapacity" : 1
  },
  "numberOfGuests" : 2,
  "amountOfLuggage" : 1,
  "full" : true
}
```

### Cleaner Schedule Endpoint
Route: `/booking?date={MM-dd-yyy}`

Method: GET

GET Response Body Example:
```
[
    {
        "cleaningSquad": {
            "cleaningSquadName": "Gnocchi Gnomes",
            "availableHours": 8,
            "payPerHour": 15
        },
        "date": 1524207600000,
        "assignments": [
            {
                "date": 1524207600000,
                "room": {
                    "roomName": "Room 1",
                    "guestCapacity": 2,
                    "storageCapacity": 1
                },
                "numberOfGuests": 2,
                "amountOfLuggage": 1,
                "full": true
            },
            {
                "date": 1524207600000,
                "room": {
                    "roomName": "Room 3",
                    "guestCapacity": 1,
                    "storageCapacity": 2
                },
                "numberOfGuests": 1,
                "amountOfLuggage": 1,
                "full": true
            }
        ],
        "timeRequired": 3.5
    }
]
```

## Project Questions
>At a high level, how does your system work? 

At a high level, this application works by keeping track of each bookable room (Room), the reservations made on 
those rooms at specific dates (Booking), the gnome squads that clean the rooms (CleaningSquad), and shifts they work to
clean those rooms on different days (CleaningSquadShift).

Storing these objects is done in the persistence layer, which in this prototype is abstracted away to just be in-memory.
Business logic for determining available rooms for a given request, picking the best room for a given request, and 
assigning gnome squads to clean each room is handled by the ReservationLedger and CleaningSquadScheduler manager classes.
The work of taking in these requests over HTTP and exposing the resulting data is handled by the Controller classes, 
which rely on a few utility classes to help them parse and validate data. Finally, the exposure of the endpoints themselves
is managed in the Application class, which uses Spark to quickly and easily set up HTTP endpoints.

Each endpoint returns more information that was originally specified, under the assumption that the extra data will
be useful to the end-user or to an application attempting to present this data to an end-user. For instance, the 
`/availableRooms` endpoint displays not just the Room object, but also the current Booking object, so that a user can
see how much space is currently available in the room on that date.

>How would we extend your system if we had to add more rooms, more business logic constraints, more gnomes?

The current business logic for fetching available rooms is set up in such a way that it does not rely on a set number 
or type of room, but instead iterates through all rooms once, looking for rooms that match the criteria of the request. 
The same is true for the business logic that chooses the best room for a given booking request. 

The business logic around the gnome cleaning schedule is ready for additional cleaning squads as well, but additional 
logic would need to be added if the number of rooms available exceeded the cleaning capacity of the hired gnome squads.
This was not implemented at this prototype stage in order to save time, since even if all current rooms are fully booked,
it will take the gnome squad at most 7 hours to complete their cleaning.

>What documentation, websites, papers, etc. did you consult for this assignment?

* http://sparkjava.com/documentation
* https://gradle.org/guides/
* https://github.com/FasterXML/jackson-databind
* https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html

>What third-party libraries or other tools does your application use? How did you choose each library or framework you used?
* Java - A strongly-typed and defined OOP language was helpful in realizing the design of a specification that had clear
objects and relationships between them. Additionally, clear inheritance made reusing code simple and readable. 
(Also, I had limited time and it's what I'm most comfortable with at the moment.)
* Spark - a lightweight Java 8 framework designed for making quick, low-verbosity APIs. I used this framework because it
allowed me to spin up a quick and simple API server easily, and it's methods make for easy-to-understand code.
* Jackson - a framework for quickly translating Java objects to JSON and back, I used Jackson for translating both my 
request and response objects, which I defined as Java classes so I could use inheritance and more easily extract the data
from them in code.

>How long did you spend on this exercise? If you had unlimited time to spend on this, how would you spend it and how 
would you prioritize each item?

I spent about 4.5 hours on the design and coding parts of the exercise, and another 45 minutes or so on the documentation.

If I had unlimited time and no preferences from the business owners, I would first prioritize implementing a more 
fleshed-out scheduling system for the cleaning gnomes, one that could handle an inn where the maximum number of rooms 
needing cleaning could exceed the cleaning capacity of the gnome squads. 

Next I would work with the inn owners to implement a more time-specific reservation system, with specified check-in and
check-out times, to assist and inform the gnome cleaning schedule, and give guests more flexibility.

After that, I'd want to work on improving scaling and hosting, to ensure that our system keeps up with the popularity of
the inn. Ultimately, though, my priorities would be driven by the business owners priorities, and what they think is 
most important to get online first. I'd discuss the options with them and advise them on what I think would be best, but
in the end, they're the ones who will know best what will help them succeed.

>If you were going to implement a level of automated testing to prepare this for a production environment, 
how would you go about doing so?

I'd start with unit tests for the business logic and utility functions, likely making use of some kind of mock testing 
data framework to simulate combinations of rooms, bookings, and gnomes. Then I'd move on to writing integration tests 
that would test each endpoint with both good and bad data to ensure that errors are handled gracefully, and good data 
returns expected results. 

I'd ideally set up a build pipeline with Jenkins or something similar to run the full suite of
tests upon every push, though as the app grows, this would need to evolve into a system where Docker images or something
similar were spun up to run tests for each push in parallel.