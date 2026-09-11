🏥 Hospital_Find_Doc

Hospital_Find_Doc is an Android application designed to help users manage and locate important hospital documents.

The application allows users to store important information such as the document name, ward, storage location, category, description, and last seen information. The main goal is to help users quickly locate the physical location of hospital documents.

📱 Project Overview

Hospital documents such as medical reports, prescriptions, patient files, and lab reports may be stored in different wards, shelves, or storage locations.

Hospital_Find_Doc provides a simple solution for storing document information and finding the physical location of a document quickly.

Example

Field

Example

Document Name

Blood Report

Ward

Ward A

Storage Location

Shelf 01

Category

Medical Report

Description

Blood test report

Last Seen

Yesterday

✨ Features

➕ Add new hospital documents

📄 View all saved documents

🔍 Search documents

📍 Track ward and storage location

🕒 Store last seen information

📋 View complete document details

✏️ Update document information

🗑️ Delete documents

📱 Display documents using RecyclerView

💾 Offline data storage using SQLite

⭐ Unique Feature

The main uniqueness of Hospital_Find_Doc is Physical Document Location Tracking.

Most document management applications focus mainly on storing digital files. This application focuses on helping users remember and find the physical location of important hospital documents.

The application stores:

Document Name

Ward or Department

Storage Location

Category

Description

Last Seen Information

This makes it easier to identify where a physical document is stored or where it was last seen.

🛠️ Technology Stack

Technology

Purpose

Kotlin

Application logic

XML

User interface design

Android Studio

Development environment

SQLite

Offline database

RecyclerView

Display document list

ConstraintLayout

Screen layout design

Intent

Navigation between activities

📂 Main Project Files

SplashActivity.kt

The first screen of the application.

Responsibilities:

Displays the application logo or animation

Opens the main screen after the splash screen

MainActivity.kt

The main home screen of the application.

Responsibilities:

Displays saved documents

Displays document count

Provides search functionality

Shows an empty message when no documents exist

Opens the Add Document screen

Displays documents using RecyclerView

AddDocumentActivity.kt

Used to add a new hospital document.

The user can enter:

Document Name

Ward / Department

Storage Location

Category

Description

Last Seen

The information is saved into SQLite using DatabaseHelper.

Document.kt

This is the data model class.

It represents one hospital document and stores:

ID

Document Name

Ward

Storage Location

Category

Description

Last Seen

DocumentAdapter.kt

Acts as a bridge between document data and RecyclerView.

Responsibilities:

Creates document cards

Displays document information

Uses item_document_card.xml

Binds document data to views

Handles document card interaction

DocumentDetailsActivity.kt

Displays complete information about the selected document.

It shows:

Document Name

Category

Ward

Storage Location

Description

Last Seen Information

It also provides:

Edit functionality

Delete functionality

DatabaseHelper.kt

Manages the SQLite database.

It performs CRUD operations:

Create → Insert document

Read → Retrieve documents

Update → Modify document information

Delete → Remove a document

Main functions include:

insertDocument()
getAllDocuments()
getDocumentById()
updateDocument()
deleteDocument()

🔄 Application Flow

SplashActivity
      ↓
MainActivity
      ↓
┌───────────────────────────┐
│                           │
▼                           ▼
Search Documents       Add Document
│                           │
▼                           ▼
RecyclerView          AddDocumentActivity
│                           │
▼                           ▼
DocumentAdapter       SQLite Database
│
▼
DocumentDetailsActivity
│
├───────────────┬───────────────┐
▼               ▼
Update          Delete
│               │
└───────┬───────┘
        ▼
 SQLite Database

🗄️ Database Structure

Database Name

FindDoc.db

Table Name

documents

Table Columns

Column

Description

id

Unique document ID

document_name

Name of the document

ward

Ward or department

storage_location

Physical storage location

category

Type of document

description

Additional information

last_seen

Last seen information

🔍 Search Functionality

Users can search documents using:

Document Name

Ward

Storage Location

Category

The RecyclerView updates to show matching documents.

📱 CRUD Functionality

➕ Create

User enters document information
        ↓
AddDocumentActivity
        ↓
Document object
        ↓
insertDocument()
        ↓
SQLite Database

👀 Read

SQLite Database
        ↓
getAllDocuments()
        ↓
DocumentAdapter
        ↓
RecyclerView

✏️ Update

Select Document
        ↓
Edit Information
        ↓
updateDocument()
        ↓
SQLite Database

🗑️ Delete

Select Document
        ↓
Delete Confirmation
        ↓
deleteDocument()
        ↓
Document Removed

🚀 How to Run the Project

Clone the repository:

git clone https://github.com/bhattdhruva2228-source/Hospital_Find_Doc.git

Open the project in Android Studio.

Allow Gradle to sync.

Connect an Android device or start an emulator.

Click the Run button.

🔮 Future Improvements

The application can be extended with:

📸 Document or storage location photo

☁️ Cloud backup

👤 User login and authentication

🔳 QR code for document location

🗺️ Hospital ward map

🔔 Document reminders

👥 Multiple users and admin system

🤖 AI-based smart search

🎯 Project Objective

The objective of Hospital_Find_Doc is to provide a simple and efficient way to manage hospital document information and help users locate physical documents quickly.

The application combines document management with physical location tracking.

👨‍💻 Developer

Dhruva Bhatt

GitHub: https://github.com/bhattdhruva2228-source

📄 License

This project is developed for educational and learning purposes.

⭐ If you like this project, consider giving the repository a star!
