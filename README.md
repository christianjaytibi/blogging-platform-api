# Blogging Platform API

## Description

A simple RESTful API for a personal blogging platform. This is a sample solution for the [Blogging Platform API project on **roadmap.sh**](https://roadmap.sh/projects/blogging-platform-api).

## Overview

### Key Features
- Create a new blog post
- Update an existing blog post
- Delete an existing blog post
- Get a single blog post
- Get all blog posts
- Filter blog posts by a search term

### Technologies Used
- **Java 21**
- **Spring Boot 3.5.4**
  - **Spring Web**
  - **Spring Data JPA**
- **PostgreSQL 17**
- **Apache Maven**
  
## API Endpoints

| Method   | Endpoints                    | Request Body           | Response Body           |
| :------- | :--------------------------- | :--------------------- | :---------------------- |
| `GET`    | `/api/blogs?term={optional}` | None                   | `List<BlogResponseDto>` |
| `GET`    | `/api/blogs/{id}`            | None                   | `BlogResponseDto`       |
| `POST`   | `/api/blogs`                 | `CreateBlogRequestDto` | `BlogResponseDto`       |
| `PUT`    | `/api/blogs/{id}`            | `CreateBlogRequestDto` | `BlogResponseDto`       |
| `DELETE` | `/api/blogs/{id}`            | None                   | None                    |
