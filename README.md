# Restaurant Listing Service

A Spring Boot microservice managing restaurant metadata within the Food-on-Wheels platform.

---

## Overview
- Exposes RESTful endpoints for restaurant CRUD operations.  
- Uses **Spring Data JPA** with **AWS RDS (MySQL)**.  
- Registered with **Eureka Server** for discovery.  
- Deployed on **AWS EKS** via **ArgoCD**.

---

## Tech Stack
Spring Boot, Spring Data JPA, AWS RDS, MySQL, Eureka Client, Docker, Kubernetes, JUnit, Jenkins, SonarQube, ArgoCD

---

## Endpoints
| Method | Endpoint | Description |
|---------|-----------|-------------|
| GET | `/fetchAllRestaurants` | Fetch all restaurants |
| POST | `/addRestaurant` | Add new restaurant |
| GET | `/fetchRestaurantById/{id}` | Get restaurant by ID |


## Future additions
- Remove restaurant 


## Part of

[food-on-wheels-org/README](https://github.com/food-on-wheels-org/README), the overview of the full application.
---

## Deployment
Built and analyzed through Jenkins and SonarQube.  
Image: `docker.io/tejassrivathsa/restaurant-listing-service:latest`  
Synced to EKS through ArgoCD.
