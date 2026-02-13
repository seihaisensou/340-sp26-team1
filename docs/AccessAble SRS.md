
# Requirements – Starter Template

**Project Name:** AccesAble \
**Team:** Kendall Williford - Provider, Max Reyes - Customer \
**Course:** CSC 340\
**Version:** 1.0\
**Date:** 2026-02-13

---

## 1. Overview

**Vision.** AccessAble is a web based application that aims to provide forms of accessability services to customers who need them. Providers will be able to create an account and post listings, and customers will be able to create an account and view those listings.

**Glossary** Terms used in the project
- **Term 1:** Accessability services - These services are meant to provide translators and interpretors for customers who need them.
- **Term 2:** Provider - A person who is certified (has formal education or has worked within this field for extended time) in specific translating or interpreting services.
- **Term 3:**

**Primary Users / Roles.**

- **Provider (e.g., Teacher/Doctor/Pet Sitter/etc. )** — create and modify account, create and modify listings, view reviews, view current customers.

**Scope (this semester).**
- Browse providers and services with filtering/sorting.
- Contact providers for services listed.
- Reviews and ratings (Customer writes review and rates: Provider views and replies to revies/ratings) with moderation.
- Provider account: create/edit profile and modify/delete service listings; view customer list
- Customer account: create/edit profile and accept/cancel service listing

**Out of scope (deferred).**
- Chat rooms and live calling within app.
- Payment processing within app.
- Expanding service listings allowed, to allow for more accessable services that are not currently provided.

> This document is **requirements‑level** and solution‑neutral; design decisions (UI layouts, API endpoints, schemas) are documented separately.

---

## 2. Functional Requirements (User Stories)
Write each story as: **As a `<role>`, I want `<capability>`, so that `<benefit>`.** Each story includes at least one **Given/When/Then** scenario.



### 2.2 Provider Stories
- **US‑PROV‑001 — Provider Accounts**  
   _Story:_ As a provider, I want create/modify a provider account so that I am able to publish listings or change aspects of my profile. 
  _Acceptance:_
  ```gherkin
  Scenario: Create/modify a provider account
    Given I am a certified accessability service provider
    When  I create/modify an account
    Then  the account is saved and posted to the web app, or changes made to an existing account are saved.
  ```

- **US‑PROV‑002 — <Service Listing>**  
_Story:_ As a provider, I want to publish a detailed service listing so that customers can view and understand the service I offer.
  _Acceptance:_
  ```gherkin
  Scenario: Publish service listing
    Given I have a verfied provider account
    When  I publish a detailed listing
    Then  the listing is saved and viewable on the listing page
  ```

- **US‑PROV‑003 — <Modify/Delete Service Listings>**  
_Story:_ As a provider, I want to be able to modify or delete my service listings so that customers have the most up-to-date listing information and they are not able to contact about a service no longer provided.
  _Acceptance:_

  ```gherkin
  Scenario: Modify/delete listings
    Given A listing is published
    When  I want to modify or delete the listing
    Then  those changes are automatically saved and published to the listing page.
  ```

  - **US‑PROV‑004 — <View Current Customers>**  
_Story:_ As a provider, I want to be able to view customers I have for specific listings so that there is organization for multiple service listings that different customers have signed up for.
  _Acceptance:_

  ```gherkin
  Scenario: View current customers
    Given I have multiple customers for different listings
    When  I go to see which customers have which services
    Then  that information is provided and sorted for me.
  ```

- **US‑PROV‑005 — <Read/Reply to Reviews>**  
_Story:_ As a provider, I want to be able to read and reply to reviews so that when a customer provides feedback I am notified and able to respond to their feedback.
  _Acceptance:_

  ```gherkin
  Scenario: Read/Reply to reviews
    Given A customer submits a review
    When  I view a review
    Then  I am able to reply directly to that review and it be published.
  ```

  - **US‑PROV‑006 — <View Ratings>**  
_Story:_ As a provider, I want to be able to view the ratings I receive so that I can understand how well my service is accepted.
  _Acceptance:_

  ```gherkin
  Scenario: View ratings
    Given A customer submits a rating
    When  The ratings(or average of multiple ratings) are posted
    Then  I am able to view the ratings.
  ```
 

## 3. Non‑Functional Requirements (make them measurable)
- **Performance:**  95% of discovery responses ≤ 1.5s; 99% of listing detail pages ≤ 1.0s under typical load.
- **Availability/Reliability:** 99.5% monthly uptime (maintenance excluded); basic retries for transient failures.
- **Security/Privacy:** Hashed & salted passwords; role‑based access checks.
- **Usability:** New providers complete listing in ≤ 3 minutes in hallway tests.

---

## 4. Assumptions, Constraints, and Policies
- Modern browsers (latest Chrome/Firefox/Edge/Safari); stable connectivity.
- Course timeline & campus infrastructure constraints apply.
- Policies: review allowed within 14-30 days; review content guidelines (no PII/harassment); cancelling service listing signup at least 24 hours before service.


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
