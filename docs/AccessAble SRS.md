
# Requirements – Starter Template

**Project Name:** Your App Name \
**Team:** Names and roles \
**Course:** CSC 340\
**Version:** 1.0\
**Date:** 2026-01-30

---

## 1. Overview
**Vision.** One or two sentences: who this is for, the core problem, and the outcome.

**Glossary** Terms used in the project
- **Term 1:** description.
- **Term 2:** description

**Primary Users / Roles.**
- **Customer (e.g., Student/Patient/Pet Owner/etc. )** — 1 line goal statement.
- **Provider (e.g., Teacher/Doctor/Pet Sitter/etc. )** — 1 line goal statement.
- **SysAdmin (optional)** — 1 line goal statement.

**Scope (this semester).**
- <capability 1>
- <capability 2>
- <capability 3>

**Out of scope (deferred).**
- <deferred 1>
- <deferred 2>

> This document is **requirements‑level** and solution‑neutral; design decisions (UI layouts, API endpoints, schemas) are documented separately.

---

## 2. Functional Requirements (User Stories)
Write each story as: **As a `<role>`, I want `<capability>`, so that `<benefit>`.** Each story includes at least one **Given/When/Then** scenario.

### 2.1 Customer Stories
- **US‑CUST‑001 — Register & manage profile**  
  _Story:_ As a customer, I want to create or update my profile (name, language, location) so that I receive relevant services and communications.  
  _Acceptance:_
  ```gherkin
  Scenario: Register with valid details
    Given I am not registered
    When  I sign up with required fields  
    Then  My customer profile is created and visible in my account
  ```

- **US‑CUST‑002 — Browse provider profiles**  
  _Story:_ As a customer, I want to browse provider profiles (local, national, language) so that I can learn more about the services they offer before contacting them.
  _Acceptance:_
  ```gherkin
  Scenario: Sort providers by distance and language
    Given multiple providers exist within 25 miles
    When  I sort by distance
    Then  the nearest providers are ordered first
  ```

  - **US‑CUST‑003 — Discover services**  
  _Story:_ As a customer, I want to filter providers by languages supported so that I can find services that suit my needs.  
  _Acceptance:_
  ```gherkin
  Scenario: Filter services provided by providers by language
    Given there are multiple services that support "Chinese" and "Captioning" alongside multiple that do not
    When  I filter providers by "Chinese" and "Captioning" while sorting by "Rating"
    Then  I see only providers that support Chinese captioning by the highest rated first.
  ```

  - **US‑CUST‑004 — Contact the provider**  
  _Story:_ As a customer, I want to be able the contact the provider so that I may receive the services I require.  
  _Acceptance:_
  ```gherkin
  Scenario: Contact the provider
    Given that the provider has a contact information
    When  I click contact
    Then  I will be forwarded to the provider's contact info
  ```

  - **US‑CUST‑005 — Write Reviews**  
  _Story:_ As a customer, I want to rate service quality and timeliness so that the community benefits from my feedback.  
  _Acceptance:_
  ```gherkin
  Scenario: Eligible Review within policy window
    Given I booked and received a service within the last 14 days
    When  I submit a rating and comment
    Then  the review is recorded and linked to the provider
      And it is published or queued per moderation policy
  ```

  - **US‑CUST‑006 — Read Reviews**  
  _Story:_ As a customer, I want to read reviews for a provider so that I can make an informed decision. 
  _Acceptance:_
  ```gherkin
  Scenario: List recent reviews
    Given reviews exist for a provider
    When  I open reviews
    Then  I see the most revent reviews with rating and comment
  ```

### 2.2 Provider Stories
- **US‑PROV‑001 — <short title>**  
  _Story:_ As a provider, I want … so that …  
  _Acceptance:_
  ```gherkin
  Scenario: <happy path>
    Given <preconditions>
    When  <action>
    Then  <observable outcome>
  ```

- **US‑PROV‑002 — <short title>**  
  _Story:_ As a provider, I want … so that …  
  _Acceptance:_
  ```gherkin
  Scenario: <happy path>
    Given <preconditions>
    When  <action>
    Then  <observable outcome>
  ```

### 2.3 SysAdmin Stories
- **US‑ADMIN‑001 — <short title>**  
  _Story:_ As a sysadmin, I want … so that …  
  _Acceptance:_
  ```gherkin
  Scenario: <happy path>
    Given <preconditions>
    When  <action>
    Then  <observable outcome>
  ```

- **US‑ADMIN‑002 — <short title>**  
  _Story:_ As a sysadmin, I want … so that …  
  _Acceptance:_
  ```gherkin
  Scenario: <happy path>
    Given <preconditions>
    When  <action>
    Then  <observable outcome>
  ```

---

## 3. Non‑Functional Requirements (make them measurable)
- **Performance:** description 
- **Availability/Reliability:** description
- **Security/Privacy:** description
- **Usability:** description

---

## 4. Assumptions, Constraints, and Policies
- list any rules, policies, assumptions, etc.

---

## 5. Milestones (course‑aligned)
- **M2 Requirements** — this file + stories opened as issues. 
- **M3 High‑fidelity prototype** — core customer/provider flows fully interactive. 
- **M4 Design** — architecture, schema, API outline. 
- **M5 Backend API** — key endpoints + tests. 
- **M6 Increment** — ≥2 use cases end‑to‑end. 
- **M7 Final** — complete system & documentation. 

---

## 6. Change Management
- Stories are living artifacts; changes are tracked via repository issues and linked pull requests.  
- Major changes should update this SRS.
