# Sprint 3 Retrospective :chart_with_downwards_trend:

## Retrospective Participants (Nov 21, 2019 at 10:30 am)
All developers were present and participated equally at the sprint 3 retrospective.

**Developers:**
* Sakina Gadriwala
* Kemar Harris
* Dineshan Pathmanathan
* Saad Shah
* Seemin Syed
* Jaya Thirugnanasampanthan

**Scrum Master:**
* Kevin Zhang 

By the end of sprint 3, we finished our key features (and added some uniqueness to our app); all user stories worked on during sprint 3 are considered "done" (according to the definition of "done" provided in sprint 0). For sprint 4, we have opened some bug tickets to work on.

## New User Story Tickets
N/A

## New Task Tickets
N/A

## New Bug Tickets
- PLANN-86: Validating Location and Transportation User Input
- PLANN-113: Fix Saving Details
- PLANN-112: Fix endpoints in getItinerary
- PLANN-114: Fixing FE BE Connection issues
- PLANN-122: Events Details Page
- PLANN-115: Connect FE and BE Attractions API
- PLANN-116: Modify GET getItinerary for multiple request fields
- PLANN-123: Change Event + Search
- PLANN-126: Create Itinerary FE


## What could we…
## Do more of?
#### Test APIs thoroughly
- Make sure all the connections to the database are established 
- The code should be able to detect parameters within the JSON body properly

#### Reguarly communicate regarding task work
* Developers struggled to complete their tasks on time since their work relied on others; it is recommended that members gather task requirements (e.g. the response body format of an endpoint) early as possible to avoid last minute code check-ins

## Do less of?
#### Complete large parts of code in short bursts
- Instead of trying to complete large portions of code in a time crunch, it's much better to complete portions of code over a steady period of time
- Developers will be better at spotting smaller errors this way

## Keep doing?
#### Code review 
* Ensure developer creates a pull-request and reviewer merges or provides feedback
* Reviewer should locally test work carefully to ensure merging does not break master branch

#### Continue proper branching structure
- Uphold the proper branching structure when developing a feature; create branches that begin with the respective JIRA ticket and work in them exclusively until ready for merging

## Start doing?
#### Combining FE and BE code
- Now that development teams have established the foundation for the code responsible for both ends, the focus has shifted to refining the code and connecting both sides via APIs

#### Addressing bugs
- During code reviews, issues were found in certain edge cases. These have been posted on the JIRA board as bug tickets for resolving during the sprint

## Stop doing?
#### Leave testing to the last minute
* We should be running unit tests frequently to ensure we are not breaking the build
* Reviewers should be careful in reviewing others' work (ask for clarification from the developer when necessary)
* The original developer of the branch should already be running tests to ensure their code compiles and runs successfully before submitting into code review

## Final Thoughts
In sprint 3, we implemented all the key features of our app. We were able to add some uniqueness to our app, which will hopefully separate us from the other *planit* apps and existing competition (e.g. *Trip Advisor* & *Expedia*). One aspect of the sprint we could work on improving is committing early as possible; a lot of our work was completed towards the end of the week. For sprint 4, we will commit to checking in code early on (even if they are very small contributions). To do this, it is important that everyone has the information they need to complete their respective tasks; it is suggested that specific details are asked early on to avoid last minute committing. 
