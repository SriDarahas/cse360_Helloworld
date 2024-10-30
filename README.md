Help System Project - Phase Two
Project Overview
The Help System is a JavaFX-based application designed to provide a structured, role-based help environment for students, instructors, and admins. It allows different users to access, manage, and organize help content according to their roles. This repository includes all source code, documentation, UML diagrams, and test cases for Phase 2 of the project.

Key Features
Role-Based Access Control: Separate functionalities for Admin, Instructor, and Student roles.
Help Article Management: Allows for the creation, update, deletion, and organization of help articles.
Backup & Restore: Articles can be backed up to and restored from external files with options to merge or overwrite data.
Grouping: Articles can be grouped by topics for easier access and management (e.g., IDE or database related).
Project Structure
/src: Contains the Java source files.
/data: Contains data files for backup and restoration of help articles.
/diagrams: UML diagrams detailing the system architecture, class structures, and use cases.
/docs: Documentation for the project, including requirements, architecture, and design details.
README.md: This file, containing information about the project, setup, and usage.
Requirements
Java Development Kit (JDK): Version 8 or above.
JavaFX: Required for running the graphical interface.
JUnit: For running the automated test cases.
Usage
Admin Role: Access user management features, including creating, updating, and deleting articles; backup and restore functionality.
Instructor Role: Create and manage articles, organize them by topics, and view grouped articles.
Student Role: View and search articles based on keywords and topics.
Contributing
Each team member has contributed specific components to this project:

Sri Darahas Koneru: Documentation and UML diagrams.
Pruthvi Nandan Janga: Core classes for HelpSystem, AuthService, and FileManager.
Abhijeet Reddy Pailla: Screencasts for technical and user walkthroughs.
Trishank Putti: Code for HelpArticle and test cases for article and authentication functionalities.
Bavinesh Vegulla: HelpSystemUI implementation and role-based User classes.
