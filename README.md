## CuriousTale Android

Android app for curious tale platform


### Entities

- User
  - streak: int
  - lastSubmissionDate: timestamp

- Question
  - title: string
  - tags: string

- Answer
  - question-id: string
  - user-id: string
  - question: string
  - answer: string
  - timestamp: timestamp

- Suggestion
  - suggestion: string
  - user-id: string

### Datastructure Requirements

- How to get question of the day?
  - path = <questions>/<yy-mm-dd>/    
  - output = Question

- How to get all answers of an user?
  - path = <useranswers>/<user-id>/<answers>/
  - output = Answer[]

- How to get current streak of the user?
  - path = <users>/<user-id>/streak

- How will admin see all questions
  - same as user

- How will admin see all answers of single question
  - path = <user-answers>/<user-id>/<answers>/<question-id>
  - output = Answer[]
  - test = need to check collection group query to do this

- How will I see all suggestions
  - path = <suggestions>/
  - output = Suggestion[]

