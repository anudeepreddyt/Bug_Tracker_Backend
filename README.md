# Bug Tracker — Backend

Working demo Link : https://bug-tracker-frontend-9r8n.onrender.com

REST API for a full-stack Bug Tracker application built with Spring Boot, PostgreSQL, and JWT authentication.

**Frontend Repository:** [Bug_Tracker_Frontend](https://github.com/anudeepreddyt/Bug_Tracker_Frontend)

---

## Tech Stack

- **Java 25** & **Spring Boot 4**
- **Spring Security** + **JWT** (with database token blacklisting for sign-out)
- **Spring Data JPA** & **PostgreSQL**
- **Swagger / OpenAPI** (`springdoc-openapi`)
- **Lombok** & **Maven**
- **Docker** (configured for Render deployment)

---

## What It Does

- **Auth & Users:** Register (`USER` / `ADMIN`), sign in to get a JWT, view/update profile details, change password, delete account, and sign out (blacklists the active token so it can't be reused).
- **Projects & Teams:** Create, delete, and look up projects by ID or name. Add or remove members with `MANAGER` or `DEVELOPER` roles.
- **Issues:** Create, edit, delete, and fetch issues per project. Supports statuses (`TODO`, `IN_PROGRESS`, `COMPLETED`) and priorities (`LOW`, `MEDIUM`, `HIGH`).
- **Comments:** Add, edit, delete, and fetch comments on issues.

---

## Running Locally

### Prerequisites
- Java 25
- PostgreSQL running locally with a database named `Bug_Tracker`
- Maven (or use the included `./mvnw` wrapper)

### Setup

1. Clone the repo:
   ```bash
   git clone [https://github.com/anudeepreddyt/Bug_Tracker_Backend.git](https://github.com/anudeepreddyt/Bug_Tracker_Backend.git)
   cd Bug_Tracker_Backend
