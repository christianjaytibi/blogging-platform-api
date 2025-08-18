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

| Method   | Endpoints                    | Description                                         | Request Body           | Response Body           |
| :------- | :--------------------------- | :-------------------------------------------------- | :--------------------- | :---------------------- |
| `GET`    | `/api/blogs?term={optional}` | Retrieves a list of blogs with optional search term | None                   | `List<BlogResponseDto>` |
| `GET`    | `/api/blogs/{id}`            | Retrieves a specific blog using an ID               | None                   | `BlogResponseDto`       |
| `POST`   | `/api/blogs`                 | Creates a new blog                                  | `CreateBlogRequestDto` | `BlogResponseDto`       |
| `PUT`    | `/api/blogs/{id}`            | Updates an existing blog                            | `CreateBlogRequestDto` | `BlogResponseDto`       |
| `DELETE` | `/api/blogs/{id}`            | Delete a blog                                       | None                   | None                    |
