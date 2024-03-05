# Groceries Shop Till

## Overview
This project implements a basic Groceries Shop Till application that
allows scanning fruits and vegetables of different types and producing
a numeric bill at the end. It includes logic for special deals such as "2 for 3" and "buy 1 get 1 half price". 

## Features
- Scan fruits and vegetables to calculate the total bill
- Support for special deals such as "2 for 3" and "buy 1 get 1 half price"
- Can be defined supported items and special deals
- Programmable logic for adding new items and deals

## Usage
1. Define the list of supported items and special deals
2. Scan the list of items with possability to use discounts or not to calculate the total bill
3. View the end price with any special deal discounts subtracted

## Installation
1. Create mysql db schema in your localhost and call it: shop_db
2. Also in src/main/resources/application.yml change username and password under datasource and flyway
3. Launch Project and let flyway do its work
4. Use add-product endpoint to add items to the system
5. Try any of the scanner controller

## Development
1. This project was developed using [Java/Spring Boot/MySQL] and [REST/HTTP API].
2. MVC (Model-View-Controller) architectural pattern.
3. Unit tests were implemented using [JUnit 5] and [Mockito] for testing various functionalities.
4. Code is organized into modules for better modularity and reusability.
