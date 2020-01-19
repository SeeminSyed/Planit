# Sprint 2 Retrospective :chart_with_downwards_trend:

## Retrospective Participants (Nov 7, 2019 at 10:30 am)
All developers were present and participated equally at the sprint 2 retrospective.

**Developers:**
* Sakina Gadriwala
* Kemar Harris
* Dineshan Pathmanathan
* Saad Shah
* Seemin Syed
* Jaya Thirugnanasampanthan

**Scrum Master:**
* Kevin Zhang 

For the purposes of sprint 2, the development team took bigger leaps into implementing bigger components for the application. Group members were allocated to work on specific parts of the overall project. Kemar, Saad, and Dineshan were responsible for implementing back-end related functionality (e.g. APIs, database tntegration). Sakina, Seemin, and Jaya took on roles that required completing front-end aspects (e.g. GUI, consuming APIs).

All tasks in sprint 2 were considered "done" (according to definition of "done" provided in done.md) and tested by Kevin. He commented that work demo-ed to him today was "much better than the last demo (sprint 1 work was tested on the console)". However, Kevin pointed out that our front-end and back-end components were not integrated together to be tested solely on an Android phone; this is the expectation for the remaining sprints. 

## New User Story Tickets
- PLANN-4: Rating an Activity Feature
- PLANN-87: Creating an Itinerary Using the Itinerary Endpoint

## New Task Tickets
- PLANN-87: Refactor work done in sprint 2

## New Bug Tickets
- PLANN-85: Itinerary Nav: Preserving values between pages
- PLANN-86: Validating Location

## What could we…
## Do more of?
#### Test APIs thoroughly
- Make sure all the connections to the database are established 
- The code should be able to detect parameters within the JSON body properly

#### Ensure databases are set up properly
- Move away from using localhost and working together with Tianze Sun  (Systems Administrator at UTSC) to host on a server
- Make sure proper restrictions and unique keys are in place for tables

#### Daily commits/work
* Due to the newly placed requirements for sprints with the standup.txt files, it is important to convey the progress made on the project everyday
 
## Do less of?
#### Hold long meetings
* They end up being unproductive

#### Complete large parts of code in short bursts
- Instead of trying to complete large portions of code in a time crunch, it's much better to complete portions of code over a steady period of time
- Developers will be better at spotting smaller errors this way

## Keep doing?
#### Be realistic about goals/deliverables
* Ensure all team members are aware of others' availability so we can plan our work accordingly
* Burndown chart indicates that while team struggled at the start of sprint 2, the team was able to complete all user stories by the end of it

#### Use technology that we are familiar with
* Do not focus too much in learning new technology 
* We plan to learn some new skills for frontend development (specifically, Flutter), but overall, we plan to use technology we were taught in past courses

#### Report bugs on JIRA
* Ensures everyone on the team is aware of the bug and its status

#### Clarify requirements with product owners regularly
* Ensures team is on track and product owners are satisfied with the work
* Kevin commented that requirements should be clarified with product owners as soon as possible (which we aim to do), but we will continue to accept requirements until we present our app

#### Code review 
* Ensure developer creates a pull-request and reviewer merges or provides feedback
* Reviewer should locally test work carefully to ensure merging does not break master branch

#### Continue proper branching structure
- Uphold the proper branching structure when developing a feature; create branches that begin with the respective JIRA ticket and work in them exclusively until ready for merging

## Start doing?

#### Combining Front-end and Back-end code
- Now that development teams have established the foundation for the code responsible for both ends, the focus has shifted to refining the code and connecting both sides via APIs

#### Addressing bugs
- During code reviews, issues were found in certain edge cases. These have been posted on the JIRA board as bug tickets for resolving during the sprint

## Stop doing?
#### Leave testing to the last minute
* We should be running unit tests frequently to ensure we are not breaking the build
* Reviewers should be careful in reviewing others' work (ask for clarification from the developer when necessary)
* the original developer of the branch should already be running tests to ensure their code compiles and runs successfully before submitting into code review

## Final Thoughts

The team still continues to hold up well with communication and assisting others when there are barriers to completion. However, with the amount of work needed to be completed in the upcoming sprints, it would be in the team's best interest to continue working at a reasonable pace spread out over long periods of time to ensure a successful and less problematic sprint. This was reflected in the burndown chart for sprint 2, as a sizable amount of points were reduced in the last few days compared to a residual reduction over time. As the team begins to improve on time management while upholding good communication skills, the upcoming sprints should pose little threat to the development cycle.
