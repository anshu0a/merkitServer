# 🌍 Merkit

> **Create. Share. Preserve. Discover.**

**Merkit** is a full-stack application built to bring posts, articles, digital time capsules, AI conversations, notifications, and user profiles together in one platform.

The application allows users to share their thoughts through posts and articles, **plant digital time capsules**, discover capsules through feeds and locations, interact with an AI chatbot, manage chatbot conversations, and maintain their personal profile.

---

# 📸 Screenshots

Add your project screenshots inside the `screenshots` folder.

```text
screenshots/
├── home.png
├── capsule.png
├── map.png
└── chatbot.png
```

Then reference them in this README:

```markdown
## 📸 Screenshots

### Home

![Merkit Home](screenshot/home.png)

### Digital Create 

![Article](screenshot/create1.png)
![Prectction](screenshot/create2.png)

### Capsule Map

![Capsule Map](screenshot/map.png)

### Calendar

![Timeline](screenshot/date.png)

### AI Chatbot

![AI Chatbot](screenshot/bot1.png)
![History](screenshot/bot2.png)
![Language](screenshot/bot3.png)

### Not Found

![404](screenshot/notfound.png)
```

---

## ✨ Features

### 🏠 Home

The Home section provides a central feed where users can discover different types of content.

* **All Article Feed**

  * Discover articles shared by users
  * Read detailed stories and information
  * Explore article content

* **All Post Feed**

  * View posts shared by users
  * Discover new content
  * Interact with posts

* **All Capsule Feed**

  * Discover digital time capsules
  * Explore memories created by users
  * Find preserved moments

* **All Trending**

  * Discover trending content
  * Explore popular posts, articles, and capsules

---

## ✍️ Create

The Create section allows users to create different types of content.

### 📝 Create Article

Users can create and publish detailed articles.

* Add article title
* Write article content
* Add tags
* Upload images
* Publish immediately
* Schedule for later

### 📱 Create Post

Users can share their thoughts and moments through posts.

* Create a post
* Add content
* Share images
* Publish content
* Share moments with other users

### ⏳ Plant Capsule

**Digital Time Capsule** is one of the main features of Merkit.

Users can plant a digital capsule containing a memory, message, image, or other information that can be preserved for the future.

Features include:

* Create a digital time capsule
* Add a personal message
* Attach images/files
* Set capsule information
* Associate capsules with locations
* Discover capsules through the capsule feed
* Find capsules through search
* Explore capsules on the map
* Preserve memories for the future

### 🌎 Capsule Discovery

Merkit provides multiple ways to discover digital time capsules:

```text
                 Digital Time Capsule
                         │
          ┌──────────────┼──────────────┐
          ↓              ↓              ↓
     Capsule Feed      Search          Map
          │              │              │
          └──────────────┼──────────────┘
                         ↓
                  Discover Memories
```

This allows memories to be discovered not only through the normal feed but also through **search and geographical locations**.

---

## 🤖 Bot

Merkit includes an AI-powered chatbot that allows users to interact with an AI assistant.

### 💬 Chat

Users can:

* Ask questions
* Get AI-generated responses
* Start new conversations
* Continue conversations
* Use Markdown-supported responses

### 🕘 Chat History

Previous conversations can be accessed through chat history.

Users can:

* View previous conversations
* Open existing chats
* Continue conversations
* Manage their conversations

### ⭐ Save Chat

Important conversations can be saved for future reference.

Saved chats can be kept separately from temporary conversations.

### 🗑️ Delete Chat

Users can delete conversations they no longer need.

---

## 🔔 Notification

The Notification section keeps users informed about activity within Merkit.

It can be used for:

* New interactions
* Content activity
* Capsule-related updates
* Application notifications

---

## 👤 Profile

The Profile section provides a personal space for each user.

Users can:

* View profile information
* Manage their account
* View their content
* Access their capsules
* View their activity

---

## ⚙️ More

The More section provides additional application features and settings.

It acts as the central place for features that do not belong directly to Home, Create, Bot, Notification, or Profile.

---

# 🏗️ Application Structure

```text
Merkit
│
├── Home
│   ├── All Article Feed
│   ├── All Post Feed
│   ├── All Capsule Feed
│   └── All Trending
│
├── Create
│   ├── Create Article
│   ├── Create Post
│   └── Plant Capsule
│
├── Bot
│   ├── Chat History
│   ├── Save Chat
│   └── Delete Chat
│
├── Notification
│
├── Profile
│
└── More
```

---

# 🛠️ Technology Stack

## Backend

The Merkit backend is built using **Spring Boot**.

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Security
* Hibernate
* REST APIs
* JWT Authentication
* MySQL

## Frontend

* React.js
* Vite
* JavaScript
* HTML5
* CSS3
* React Router
* Axios
* React Markdown

## Database

* MySQL

## Other Technologies

* Cloudinary
* MapLibre GL
* OpenFreeMap
* Git
* GitHub

---

# 🔐 Authentication & Security

Merkit uses secure authentication to protect user accounts and application resources.

Authentication features include:

* User Registration
* User Login
* JWT Authentication
* Access Token
* Refresh Token
* Forgot Password
* OTP Verification
* Password Reset
* Protected APIs
* User-based authorization

---

# 🧩 Backend Architecture

The Spring Boot backend follows a layered architecture.

```text
Client
  │
  ↓
Controller
  │
  ↓
Service
  │
  ↓
Repository
  │
  ↓
Database
```

### Controller

Handles HTTP requests and API endpoints.

### Service

Contains application and business logic.

### Repository

Handles database operations using Spring Data JPA.

### Entity

Represents database tables and relationships.

---

# 🗄️ Database

Merkit uses **MySQL** as its primary database.

The database stores information related to:

* Users
* Roles
* Posts
* Articles
* Capsules
* Locations
* AI Chats
* AI Messages
* Notifications
* Authentication
* Password Reset
* Refresh Tokens

---

# 🤖 AI Chat Architecture

The chatbot is designed around conversations and messages.

```text
AI Chat
   │
   ├── Chat
   │    ├── Message
   │    ├── Message
   │    └── Message
   │
   ├── Save Chat
   │
   └── Delete Chat
```

Each conversation can contain multiple messages, allowing users to continue previous AI conversations.

---

# ⏳ Digital Time Capsule

The Digital Time Capsule feature connects three important concepts:

```text
        Memory
           │
     ┌─────┴─────┐
     ↓           ↓
    Time       Location
     │           │
     └─────┬─────┘
           ↓
    Digital Capsule
```

A capsule can represent a memory that is connected to a particular **time and place**.

Users can create capsules and later discover them through:

* Capsule Feed
* Search
* Map

This makes Merkit more than a simple content-sharing platform by connecting digital memories with their context.

---

# 🚀 Getting Started

## 1. Clone the Repository

```bash
git clone https://github.com/anshu0a/merkitServer.git
```

```bash
cd merkitServer
```

## 2. Configure MySQL

Create a MySQL database for the application.

Configure the database connection in your Spring Boot configuration.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/merkit
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

## 3. Configure Environment Variables

Configure sensitive values such as:

```text
JWT_SECRET=
CLOUDINARY_CLOUD_NAME=
CLOUDINARY_API_KEY=
CLOUDINARY_API_SECRET=
MAIL_USERNAME=
MAIL_PASSWORD=
```

Do not upload real credentials to GitHub.

## 4. Run the Spring Boot Application

Using Maven:

```bash
mvn spring-boot:run
```

Or run the main Spring Boot application class from Eclipse/IntelliJ IDEA.

---

# 📡 REST API

The backend exposes REST APIs for different application modules.

```text
Authentication
      │
      ├── Register
      ├── Login
      ├── Forgot Password
      └── OTP

Content
      │
      ├── Posts
      ├── Articles
      └── Capsules

AI
      │
      ├── Ask
      ├── Chat History
      ├── Save Chat
      └── Delete Chat

User
      │
      ├── Profile
      └── Notifications
```

---

# 🔮 Future Improvements

Possible future improvements include:

* Enhanced capsule discovery
* More interactive map features
* Advanced AI capabilities
* More games
* Improved notifications
* Social interactions
* More personalization options
* Additional memory and capsule features

---

# 👨‍💻 Developer

**Anshu Kumar Gupta**

Java Full Stack Developer

**Technologies**

```text
Java
Spring Boot
Spring Security
React
JavaScript
MySQL
REST API
JWT
```

GitHub:

`https://github.com/anshu0a`

---

# 📄 License

This project is developed as a personal project.

---
