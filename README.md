# Student Management App

## Overview

This repository contains a student management application developed as an Advanced Programming Project for Spring 2024. The project is implemented using Java for the backend and [Flutter](https://flutter.dev/) for the mobile application. It provides functionalities for managing students, teachers, courses, and assignments.
You can watch this [video](https://www.aparat.com/v/pgbofnc) to see how the app works.

## Features

### CLI Application
- **Admin Menu**:
  - Add/Remove students, teachers, classes, assignments
  - Modify assignments, classes, student, and teacher data
- **Teacher Menu**:
  - Add/Remove students to/from classes
  - Add/Remove assignments to/from classes
  - Modify assignments, and student data

### Mobile Application
- **Welcome Page**: Navigation to login and sign-up
- **Sign Up & Login**: User authentication
![Section 1](https://github.com/user-attachments/assets/abcb926c-b902-428c-b424-9dfa0fa57537)
- **User Info**: Avatar upload, account deletion, password change
- **Home Screen**: Summary of records and assignments
![Section 2](https://github.com/user-attachments/assets/545f3cfb-0e06-46a5-850d-852bf9983123)
- **To-Do List**: Task management
- **Classes**: Class management
![Section 3](https://github.com/user-attachments/assets/c1da3a62-7a1f-4797-a7f6-d56e8d12ca9b)
- **News**: University news updates
- **Assignments**: Assignment details
![Section 4](https://github.com/user-attachments/assets/083ac99b-2afe-46fb-8b9c-6fd591f5de9a)

## Backend
- **Server**: Handles multiple client requests using Socket connections and multithreading.

## Technologies
- **Backend**: [Java](https://www.java.com/)
- **Frontend**: [Flutter](https://flutter.dev/) and [Dart](https://dart.dev/)

## Installation

### Prerequisites
- Java Development Kit (JDK)
- Flutter SDK

### Steps
1. Clone the repository:
    ```bash
    git clone https://github.com/ZahraAziziGit/student-management-app.git
    ```
2. Navigate to the project directory:
    ```bash
    cd student-management-app
    ```
3. Follow specific setup instructions for the backend and frontend as per the documentation.

## Usage

### Running the CLI
1. Compile and run the Main
	```bash
	cd src
	
	javac Main.java
	
	java Main.class
	```


### Running the Backend
1. Compile and run the Java server.
	```bash
	cd src/Server
	
	javac Server.java
	
	java Server.class
	```


### Running the Mobile App
1. Open the Flutter project in an IDE (e.g., Android Studio).
2. Run the app on an emulator or a physical device.

## TODO List
⬜ Uploading files for assignments

⬜ Adding a student to a course via the app

⬜  Sorting the assignments based on closer deadlines

⬜  Adding a dark theme

## License
This project is licensed under the [MIT](https://github.com/ZahraAziziGit/student-management-app/tree/main?tab=MIT-1-ov-file#) License.

## 
Thank you for checking out the Student Management App project! If you have any questions or feedback, feel free to reach out.

