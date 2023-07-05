## CuriousTale Android

Android app for curious tale platform

### Features

- to be done

### Structure

- Question Collections
  - <year>-<month>-<day> (ex: 2023-06-29)
    - title [string]
    - answers [arr]
      - user-id
      - answer [string]
- Users Collections
  - user-id (firebase auth generated)
    - answers
      - question-id (hash of question string md5)
        - answer [string]
        - question-id [string]
        - question [string]
        - timestamp [date]
