# Introduction

***Given:***

A city controller allowing you to:
1. Add a city. A city object consists of city name and country.
2. Look at existing cities.

A flight controller allowing you to:
1. Add a new flight. A flight object consists of source city, destination city and the fare.
2. Look at existing flights.
3. Update a flight
4. Delete a flight

***You have***

An itinerary controller allowing you:
1. Find the cheapest prices from source city to destination city within N number of stops.

# Usage

***To start the server locally***

java -jar

# Example

![Alt Flight Network](/Users/krishnande/flight.png)

***Given the above flight network***

Cheapest itinerary with a maximum of 1 stop (layover) from San Fransisco to Frankfurt is the from 
San Fransisco -> Atlanta -> Frankfurt, costing 900. However, if we increase the number of stops to 2, we can get a 
cheaper route from San Fransisco -> Boston -> Paris -> Frankfurt, costing 600.
   