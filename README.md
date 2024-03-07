# Entain Technical Task README
Technical task for Entain Mobile Engineer role


# Architecture
The high-level architecture adopted was Clean Architecture (data, domain, presentation) with the presentation layer sub-architecture being Model-View-ViewModel.

Alternatively, the presentation layer could have been Unidirectional Flow as recommended by Google for Android apps using Jetpack Compose. For this task, I went with MVVM.


# Feature
The main business logic will be found in the `domain` layer, especially the `RacingUseCase` and `RaceResponseToDomainModelMapper`.

The following rules apply: 
- There are 3 categories of races: Horse, Harness & Greyhound;
- The user will see thee races in ascending order by advertised start time;
- Races that have an advertised start time past 1 minute will no longer be displayed;
- De-selecting a category will display the next 5 races of all categories;
- The user will see data such as: Meeting Name, Race Number & Advertised Start (as a countdown timer);
- The user will always see 5 races and data will automatically refresh;


# Additional Notes
A polling strategy was implemented for the automatic data refreshes. However, an alternative was to implement WebSockets for networking instead of polling.


# Testing Notes
Press 'Next to Go' which will navigate you to a new screen.

Select each of the filters: Horse, Harness & Greyhounds and observe the races change accordingly.
De-select the filters to return to the 'All' race categories.

At any stage, wait for a races countdown timer to reach -59 / -60 and observe the race disappear and new races load in.
