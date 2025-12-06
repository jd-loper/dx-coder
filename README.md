# Dx Coder—Medical Coding Assistant

A specialized AI tool built with Java that helps with extracting ICD-10-CM (diagnosis) and CPT (procedure) codes from medical documentation.

Built using the **Google Gemini API** for intelligence and **Gson** for structured data parsing.

## Tech Stack

* **Language:** Java 25
* **Build System:** Maven
* **AI Model:** Google Gemini 2.5 Flash (via REST API)
* **Libraries:**
    * `com.google.code.gson` (JSON Parsing)
    * `java.net.http.HttpClient` (Standard Java HTTP Client)

## Prerequisites

Before running this project, ensure you have:

1. **Java Development Kit (JDK)**.
2. A **Google Gemini API Key** (Get one at [Google AI Studio](https://aistudio.google.com/)).

## ▶️ Usage

1.  **Interact:**
    Type a medical scenario into the console.

    **Example Input:**
    > "Patient presents with a closed fracture of the right clavicle from a fall."

    **Example Output:**
    > Code: S42.001A [ICD-10-CM] - Fracture of unspecified part of right clavicle, initial encounter for closed fracture

2.  **Exit:**
    Type `exit` to close the application.