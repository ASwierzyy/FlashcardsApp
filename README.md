# 🌍 Flashcards App

A multilingual flashcards learning app built with **Java**, **Spring Boot**, and **modular JavaScript**, designed to manage vocabulary and test translations between **Polish**, **English**, and **German**.

---

## 🚀 Features

- Add, update, delete, and list flashcards (CRUD)
- Sort entries by any language or ID (asc/desc)
- Search by ID or word (case-insensitive)
- Start a quiz with a random word in a random language
- Validate answers server-side for accuracy and fairness
- Dynamic word formatting using the **Strategy Pattern** via **Spring Profiles** (`uppercase`, `lowercase`, `original`)
- Clean, responsive UI built with **HTML**, **CSS**, and **ES6 modules**

---

## 🧱 Architecture

- **Backend:** Java 23, Spring Boot, Spring Data JPA, H2 Database  
- **Frontend:** Vanilla JS (modular), HTML5, CSS3  
- **Architecture:** Layered (Entity → Repository → Service → Controller)  
- **DTOs:** Used to separate API contracts from domain models  
- **Profiles:** Word display strategy injected dynamically via Spring Profiles

---

## 🧪 Quiz Logic

- Random flashcard and random source language chosen on the backend
- User must provide correct translations for the other two languages
- Answers are validated on the server; correct answers are returned if incorrect

---
