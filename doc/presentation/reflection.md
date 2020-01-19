# Reflection :thumbsup: :thumbsdown:

## Tell us about tools/techniques/conventions that worked well for your team. Explain why.

### Using [JIRA](https://mcsapps.utm.utoronto.ca/jira) to track our work
Using JIRA allowed us to check each other's progress without having to message each other; our team was good with changing the statuses of their tickets. It also helped us organize and prioritize new bugs, improvements, and features. Consequently, our team was able to be more productive during the sprints.

### Peer review
As a team, we were careful to check in peer-reviewed code to master so that our master branch would always be production-ready. We were only able to do this by creating branches and pull-requests (which would notify the team when someone's code needed to be reviewed). We did not have a formal procedure for code review; if a developer noticed that a pull-request was made, they would review the code and merge it to master, if no issues were found. 

### Holding a retrospective at the end of each sprint
This meeting allowed us to reflect on what worked and did not work in the previous sprint, and to commit to at least one improvement for the next sprint. We believe that each retrospective helped improve the way we worked as a team. For example, in our sprint 1 retrospective, we committed to moving away from our primitive work and to work collaboratively on the frontend and backend components, which we followed through with in sprint 2.

## Tell us about tools/techniques/conventions that didn't work well for your team. Explain why.

### Using [Slack](https://cscc01-workspace.slack.com/?redir=%2Fgantry%2Fclient) as our main communication tool
While Slack enabled us to collaborate on documentation (through Google Drive integration) and organize our conversations into channels, we lost our older conversations due to hitting the 10K limit midway through the semester. Some vital information that we lost included links to resources to help us develop our app and our daily standup documentation.

We did not consider moving to a new platform since we already had the app installed on our laptops and phones, and did not want to waste time and resources to transfer our communication to a new platform late in the semester. This meant that we had to back up our documentation and messages locally on our machines for the remaining sprints. In hindsight, we should have researched more on communication/collaboration platforms and chosen the best one to meet our needs rather than going by industry reputation.

### Using the [Foursquare API](https://developer.foursquare.com/)
Due to the [daily call quota](https://developer.foursquare.com/docs/api/troubleshooting/rate-limits) used to generate the itinerary based on the user's preferences, we were unable to test and use our app as often as we would have liked to. In hindsight, we should have researched other APIs in sprint 0 to ensure we would not have issues with using our app.

### Developing our app to be used on the console (with an in-memory implementation) for sprint 1
As a team, we decided that it would be best to focus on the backend implementation since it would the most challenging aspect of our app and we assumed that the console frontend could easily be swapped with the actual UI later. However, we realized in sprint 2 that most of our work done in sprint 1 could not be used since we had to deal with writing endpoints that would need to communicate with an API. In addition, we had to integrate our backend to our frontend. 

Thus, we had more work than previously anticipated in sprints 2 and 3, which meant we were unable to implement some lower priority user stories. In hindsight, the team should not have focused on the backend implementation for sprint 1 and should have worked early on the frontend and backend components. 
 
 ## If you had to continue working as a team, and design your process, what would your process look like?
