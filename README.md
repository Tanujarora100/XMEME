# XMEME
# XMeme Backend

<p align="center">
  <img src="https://img.shields.io/badge/Java-11-blue" alt="Java Version" width="150" height="30">
  <img src="https://img.shields.io/badge/Spring%20Boot-2.7.1-green" alt="Spring Boot Version" width="200" height="30">
  <img src="https://img.shields.io/badge/MongoDB-4.4-yellow" alt="MongoDB" width="150" height="30">
  <img src="https://img.shields.io/badge/Swagger-UI-brightgreen" alt="Swagger" width="120" height="30">
  <img src="https://img.shields.io/badge/JUnit-5-red" alt="JUnit" width="100" height="30">
  <img src="https://img.shields.io/badge/Mockito-3.10-orange" alt="Mockito" width="120" height="30">
  <img src="https://img.shields.io/badge/Docker-Container-blue" alt="Docker" width="150" height="30">
</p>
Welcome to the XMeme Backend! This backend server is the heart of the XMeme meme-sharing platform, built using Spring Boot and MongoDB.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## Overview

This project serves as the robust backend infrastructure for XMeme, an engaging and dynamic meme-sharing application. At its core, this backend empowers users to create, retrieve, and manage their favorite memes effortlessly. It accomplishes this by providing a suite of RESTful API endpoints meticulously designed for meme enthusiasts.
The backend is thoughtfully crafted following the Model-View-Controller (MVC) architectural pattern. This architecture is the cornerstone of the project, ensuring not only the seamless operation of the current features but also laying a strong foundation for future enhancements. It embodies the principles of maintainability, scalability, and code organization, making it a reliable and flexible platform for meme sharing.

In addition to the robust backend architecture, I've taken a step further to enhance the project's deployment and scalability. The entire solution has been meticulously containerized using Docker. This means that the application, along with its dependencies and configuration, is neatly encapsulated within Docker containers.

## Features

- Create and post memes.
- Retrieve and view memes.
- Swagger API documentation for easy API exploration.
- Unit testing using JUnit and Mockito for quality assurance.
- MongoDB for data storage.

## Getting Started

1. **Prerequisites:**

   - Java 11 or higher
   - MongoDB 4.4 or higher
   - Gradle (for building and running the project)

2. **Clone the Repository:**

   ```bash
   git clone https://github.com/your-username/XMEME.git
