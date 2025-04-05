

---

# 🚀 Blog Application - Spring Boot Project

This Blog Application is a robust backend system built using **Spring Boot**, tailored for managing blog posts, categories, and user interactions. It integrates several advanced features including NLP-based comment moderation, content scheduling, SEO-friendly slugs, PDF exporting, and estimated read-time—all making it an ideal showcase for Java backend development.

---

## 🔐 Authentication & Authorization

> 🛠 **In Progress**

- **JWT (JSON Web Token)** (Planned):  
  Secure authentication using token-based sessions for stateless REST APIs. Each login returns a signed token, which is required to access protected endpoints.

- **Role-Based Access Control (RBAC)** (Planned):  
  Fine-grained access will be managed based on user roles like `ADMIN`, `AUTHOR`, and `READER`. Specific permissions will be applied on routes like post creation, comment approval, etc.

---

## 🚦 Core Features

### 📝 Post Management
- **CRUD Operations**: Users can create, edit, delete, and fetch blog posts.
- **Slug URLs**: Every post and category has a **slug** field (e.g., `my-first-post`), which is used to generate SEO-friendly and human-readable URLs.
- **PDF Export**: Posts can be exported as PDF documents using libraries like **Apache PDFBox** or **iText**.
- **Read Time Estimation**: Automatically estimates the reading time of a post based on word count (average reading speed: 200 words/min).

---

### ⏰ Scheduled Publishing

> ✅ **Implemented using Spring Scheduler**

- You can schedule a post to be **published at a future date/time** using a timestamp.
- This is achieved using Spring’s `@Scheduled` and a background task that checks draft posts for their scheduled publish time.
- A cron job or fixed delay periodically runs in the background, and when the current time matches the scheduled time, the post's status changes from `DRAFT` to `PUBLISHED`.

---

### 📚 Category Management

- **Slug Generation**: Generates slug from the category name (e.g., "Java Basics" → `java-basics`) for clean URLs.
- **Soft Delete**: Instead of deleting categories from the DB, a flag (`deleted = true`) is used. This allows admins to **restore** them if needed.

---

### 💬 Comment System

- **Like/Dislike**: Users can express their opinion on each comment.
  
### 🧠 Comment Moderation with Stanford NLP

> ✅ **Implemented using Stanford CoreNLP**

- Every comment submitted is passed through **Stanford NLP**'s sentiment and toxicity analysis.
- If inappropriate or toxic content is detected:
  - The comment is flagged as **"Pending Approval"**.
  - Only users with **admin role** can review and approve/reject such comments.
- Stanford NLP helps maintain a safe and respectful environment on the platform.

---

## 📌 Summary of Key Functionalities

| Feature                        | Description                                                                 |
|-------------------------------|-----------------------------------------------------------------------------|
| **Slug URLs**                 | Slug fields help generate clean, SEO-friendly URLs.                         |
| **Soft Delete with Restore**  | Deleted items are only hidden and can be recovered later.                   |
| **PDF Export**                 | Posts can be downloaded as PDF files.                                       |
| **Estimated Read Time**        | Automatically calculates how long a blog takes to read.                     |
| **Comment Moderation**         | Powered by Stanford NLP for toxicity detection.                             |
| **Like/Dislike on Comments**   | Adds interactivity and engagement.                                          |
| **Scheduled Publishing**       | Automatically publishes posts at a specified date/time.                     |
| **JWT Authentication**         | 🔧 In Progress: Token-based login system.                                   |
| **Role-Based Access Control**  | 🔧 In Progress: Permissions will vary by user role (Admin, Author, Reader). |

---

## 🛠️ Tech Stack

- **Backend**: Spring Boot  
- **Security**: Spring Security, JWT (Planned)  
- **Database**: MySQL  
- **ORM**: Hibernate & JPA  
- **NLP Engine**: Stanford CoreNLP  
- **PDF Export**: Apache PDFBox / iText  
- **Task Scheduler**: Spring `@Scheduled`  
- **IDE**: Vs Code

---


## er-diagram
 +--------------------+         +--------------------+
|      Category      |1       *|       Post         |
|--------------------|---------|--------------------|
| id (PK)            |         | postId (PK)        |
| categoryTitle      |         | title              |
| categoryDescription|         | content            |
| slug               |         | imageName          |
| isDeleted          |         | uploadDate         |
+--------------------+         | scheduledAt        |
                               | createdAt          |
                               | updatedAt          |
                               | readAt             |
                               | status             |
                               | category_id (FK)   |
                               | user_id (FK)       |
                               +--------------------+
                                        |1
                                        |
                                      * |
                                +----------------+
                                |     Comment     |
                                |----------------|
                                | id (PK)        |
                                | content        |
                                | likeCount      |
                                | dislikeCount   |
                                | post_id (FK)   |
                                +----------------+
                                        |1
                                        |
                                      * |
                                +-----------------------+
                                |   CommentReaction     |
                                |-----------------------|
                                | id (PK)               |
                                | reactionType (LIKE/..)|
                                | user_id (FK)          |
                                | comment_id (FK)       |
                                +-----------------------+

+--------------------+
|       User         |
|--------------------|
| id (PK)            |
| name               |
| email              |
| password           |
| about              |
| role (ENUM)        |
+--------------------+
        |1
        |
      * |
+----------------+
|     Post       |
|----------------|
| user_id (FK)   |
+----------------+
        |
      * |
+----------------------+
|  CommentReaction     |
|----------------------|
| user_id (FK)         |
+----------------------+


## 🚀 Getting Started

1. **Clone the Repository**  
```bash
git clone https://github.com/your-username/blog-application-springboot.git
cd blog-application-springboot
```

2. **Update MySQL Configuration**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_db
spring.datasource.username=root
spring.datasource.password=your_password
```

3. **Run the Application**
```bash
mvn spring-boot:run
```

---

## 🙌 Contribution

Pull requests are welcome. If you’d like to contribute, fork the repo and submit changes via a pull request. For suggestions, feel free to open issues.

---

