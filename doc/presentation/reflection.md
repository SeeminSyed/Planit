# Reflection :thumbsup: :thumbsdown:

## Tell us about tools/techniques/conventions that worked well for your team. Explain why.

### Using [JIRA](https://mcsapps.utm.utoronto.ca/jira) to track our work
JIRA allowed us to check each other's progress without having to message each other; our team was good with changing the statuses of their tickets. It also helped us organize and prioritize new bugs, improvements, and features. Consequently, our team was able to be more productive during each sprint.

### Code review
We were careful to only merge code to master after code review was performed based on the criteria of satisfaction so that our master branch would always be production-ready. We were able to do this by creating branches for each JIRA ticket and using pull-requests (which would notify the team when someone's code needed to be reviewed). We also requested Professor Dema to add a 'code review' column on JIRA to facilitate code review; others would know that a task would need (or is undergoing) code review. 

We did not have a formal procedure for code review. If a developer noticed that a pull-request was made or there was an unassigned ticket under the 'code review' column on JIRA, they would review the code and merge it to master, if the work was considered done based on the criteria of satisfaction, on a voluntary basis. This only worked because of the team's cohesion and communication.

### Holding a retrospective at the end of each sprint
This meeting allowed us to reflect on what worked and did not work in the previous sprint, and to commit to at least one significant improvement for the next sprint. We believe that each retrospective helped improve the way we worked as a team. For example, in our sprint 1 retrospective we committed to moving away from our previous work style and to work collaboratively on the frontend and backend components, which we followed through in sprint 2.

## Tell us about tools/techniques/conventions that didn't work well for your team. Explain why.

### Using the [Foursquare API](https://developer.foursquare.com/)
We were initially drawn to the Foursquare due to the variety of its public APIs. However, due to the [daily call quota](https://developer.foursquare.com/docs/api/troubleshooting/rate-limits) used to generate the itinerary based on the user's preferences, we were unable to test and use our app as thoroughly as we would have liked to. In hindsight, we should have researched other APIs during sprint 0 to ensure we would not have issues with using our app.

### Developing our app to be used on the console (with an in-memory implementation) for sprint 1
As a team, we decided that it would be best to focus on the backend implementation since it would be the most challenging aspect of our app and we assumed that the console frontend could easily be swapped with the actual UI later. However, we realized in sprint 2 that most of our work done in sprint 1 could not be used since we had to deal with writing endpoints that would need to communicate with an API. In addition, we had to connect our backend to our frontend.

Thus, we had more work than previously anticipated in sprints 2 and 3, which meant we were unable to implement some lower priority user stories. In hindsight, the team should not have focused on the backend implementation for sprint 1 and should have worked early on the frontend and backend components. This would allow us to finalize business and user requirements from a frontend perspective before actually developing any business logic in the backend.

### Splitting into frontend and backend teams
We considered this to be a double-edged sword. Having our large development team split into two allowed us to communicate easily (in person and online) within our sub-teams since we had scheduling issues in our larger groups. This arrangement also allowed us to have frontend and backend development occur in parallel during each sprint, switching which team does which end during the next sprint (so that everyone has an opportunity to work on both frontend and backend development); we originally believed this would help us achieve our sprint goals of having the frontend and backend components ready as early as possible.

In hindsight, splitting the team into two had significant consequences to how we communicated as one team. We found that the communication between the frontend and backend teams were extremely poor, which often resulted in the backend and frontend components not being able to be properly communicate to each other. For example, frontend and backend developers would expect different request and response bodies for our APIs. In the beginning, we wrote a set of requirements on a shared document and planned for the backend and frontend teams follow the requirements as written. However, changes made later on were unfortunately not reflected on the shared document, due to collective oversight.

Also, splitting the team into two meant that individuals were more knowledgeable about some sections of the code, but not others. This meant that when an issue was raised in later sprints, only a few individuals were able to investigate and resolve such issues, as they were most familiar with the circumstances surrounding that code.

## If you had to continue working as a team, and design your process, what would your process look like?
Most of us are taking CSCC09 next semester and since we really enjoyed working with each other this term, we plan to work as a team again :heart_eyes:

We plan to continue using the following tools since it really helped with team communication, development, and testing:
* Git
* Postman
* JIRA
* Google Hangouts (as a solution to scheduling issues)

However, we would prefer to use Discord instead of Slack (for team communication) since Slack has a 10K message visibility limit in its free version and does not allow group calls; both of which were obstacles to our communication we encountered fairly quickly. We learned in this course that our group relies significantly on text to communicate (our current message count is 25K as of December 2, 2019), and so we feel we would need a platform that better suits our needs in that regard.

Also, to address the challenge of us having everyone work on the frontend and backend but still maintain excellent communication, we should avoid splitting the team into two. Instead, we should have each person work on a frontend component and its corresponding backend component and have that developer be the "expert" on that feature. As the "expert", they should write appropriate documentation, fix bugs in their code, and assist with related features when needed.
