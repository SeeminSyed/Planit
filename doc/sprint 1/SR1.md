# Sprint 1 Retrospective :chart_with_downwards_trend:

## Retrospective Participants (Oct 24, 2019 at 10:30 am)
All developers were present and participated equally at the sprint 1 retrospective.

**Developers:**
* Sakina Gadriwala
* Kemar Harris
* Dineshan Pathmanathan
* Saad Shah
* Seemin Syed
* Jaya Thirugnanasampanthan

**Scrum Master:**
* Kevin Zhang

## New User Stories
As noted during the retrospective, the work done in sprint 1 was primitive; the dev team wanted to ensure that the validation was working properly. To avoid having integration problems in later sprints, it was agreed by all participants that we should aim to accomplish the following in the next sprint:
* frontend: have a working user interface to be able to input values on GUI
* backend: move your validation to a working server and also deal with data storage (i.e. some sort of database structure)

Consequently, these user stories were created. 

PLANN-59: Integrating the database for the project
* Subtasks: PLANN-60, PLANN-61

PLANN-62: Configure APIs for the FE to consume
* Subtasks: PLANN-63, PLANN-64, PLANN-65, PLANN-66, PLANN-67, PLANN-68, PLANN-69

PLANN-71: Consume the APIs configured
* Subtasks: PLANN-73, PLANN-74, PLANN-75

PLANN-72: Setup the GUI
* Subtasks: PLANN-76, PLANN-77, PLANN-78, PLANN-79, PLANN-80, PLANN-81, PLANN-82, PLANN-84

## What could we…
## Do more of?
#### Write and run unit tests 
* Ensure the unit test does one thing
* Use descriptive names and/or add Javadoc
* Run all unit tests locally before pushing changes to master
 
#### Hold short meetings regularly 
* This may not be feasible to have daily stand-up meetings, but we could check in with each other on our progress via Slack
 
#### Code review 
* Ensure developer creates a pull-request and reviewer merges or provides feedback
* Reviewer should locally test work carefully to ensure merging does not break master branch

#### Follow source control best practices
* Prefix commit messages with user story ID (as convention)
* Verify changes before committing (to prevent accidently checking in code we meant to revert)
* Branch
 
## Do less of?
#### Hold long meetings
* They end up being unproductive

#### Push changes directly to branch
* They sometimes end up breaking the build
* Ideally, we should be working in branches

## Keep doing?
#### Be realistic about goals/deliverables
* Ensure all team members are aware of others' availability so we can plan our work accordingly
* Burn down chart indicates that while team struggled at the start of sprint 1, the team was able to complete all user stories by the end of it

#### Use technology that we are familiar with
* Do not focus too much in learning new technology 
* We plan to learn some new skills for frontend development (specifically, Flutter), but overall, we plan to use technology we were taught in past courses

#### Report bugs on JIRA
* Ensures everyone on the team is aware of the bug and its status

#### Clarify requirements with product owners regularly
* Ensures team is on track and product owners are satisfied with the work

## Start doing?
#### Development
* As suggested by Kevin, we should commit to working on the user interface so that we are able to input fields 
* We need to do some research into Flutter
* We need to move our validation to a working server and also deal with data storage (i.e. some sort of database structure)
* Speak with Tianze Sun (Systems Administrator at UTSC) to help us host our PostgresSQL DB on a sever (this will allow our app to be used on any Android phone)

## Stop doing?
#### Leave testing to the last minute
* We should be running unit tests frequently to ensure we are not breaking the build
* Reviewers should be careful in reviewing others' work (ask for clarification from the developer when necessary)

## Final Thoughts
The team agrees that working with each other was the best part: 
* We all understand what the release goals are, which helps us work towards them
* Everyone is engaged and respected
* We help each other out

Overall, there were no bad experiences in sprint 1; we considered sprint 1 a period of adjustment. However, we are committed to moving away from doing primitive work and holding shorter meetings in sprint 2.  
