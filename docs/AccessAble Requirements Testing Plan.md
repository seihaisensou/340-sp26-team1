**Project Name:** AccessAble   
**Version:** 1.0
**Date:** 5/5/2026  
**Purpose:** This document outlines comprehensive test scenarios for each functional requirement (user story) in the AccessAble system.

## Actors
- Provider P: Service Providers (Translation, Captioning, Interpreters)
- Customer C: Customer
- Service S: Listing

## Use Cases
#### 1. Provider: US‑PROV‑001 — Provider Accounts, US‑PROV‑002 — Service Listing 
1. Provider P1 logs in for the first time and creates a profile.
2. P1 creates a listing with a value for searchable criteria. C1 (C1=Listing Name).
3. P1 potentially makes a mistake or decides to delete the listing L1.
P1 exits the app.

#### 2. Customer: US‑CUST‑001 — Register & manage profile
1. Customer C1 logs in for the first time and creates a profile.
2. Customer C1 decides to delete their account. C1 exits the app.

#### 3. Customer:  US‑CUST‑001 — Register & manage profile, US‑CUST‑003 — Discover services, US‑CUST‑004 — Contact the provider.
1. Customer C2 logs in for the first time and creates a new profile.
2. C2 views available listings S1 and S2.
3. C2 books S1.

#### 4. Provider: US‑PROV‑004 — View Current Customers
1. P1 log in and views their current customers.
2. P1 updates the booking status of C2 and L1. C2 exits.

#### 5. Customer: US‑CUST‑005 — Write Reviews
1. C2 log in and views their bookings.
2. C2 writes a positive review of listing S1. 
3. C2 is forwarded to the listing's page and reads reviews. C2 exits. US‑CUST‑006 — Read Reviews

#### 6. Provider: US‑PROV‑005 — Read/Reply to Reviews, US‑PROV‑006 — View Ratings, US‑PROV‑003 — Modify/Delete Service Listings
1. Provider P1 logs in and reads their review and replies with thanks. 
2. P1 views listing ratings.
3. P1 modifies the price of S1.
4. P1 exits.

## CROSS-CUTTING TEST SCENARIOS (Non-Functional Requirements)

### Performance Requirements

**Scenario P1: Browse listings response time < 1.5 seconds**
- **Setup:** Server under typical load
- **Steps:**
  1. Measure response time for "Browse" page load with 5 providers, 10+ listings
  2. Repeat 10 times
- **Expected Outcome:** 95% of requests ≤ 1.5 seconds

**Scenario P2: Listing page load < 1.0 second**
- **Setup:** Server under typical load
- **Steps:**
  1. Measure response time for listing page
  2. Repeat 10 times
- **Expected Outcome:** 99% of requests ≤ 1.0 second

### Security & Privacy Requirements

**Scenario S1: Role-based access control**
- **Setup:** Customer user tries to access Provider account
- **Steps:**
  1. Customer logs in
  2. Attempts to navigate to "/provider/account"
  3. Observes system response
- **Expected Outcome:**
  - Access is denied (403 Forbidden)
  - User is redirected to home or error page
  - No Provider data is exposed

**Scenario S1: User based access control**
- **Setup:** Customer user tries to edit another user's review
- **Steps:**
  1. Customer logs in
  2. Attempts to navigate to "/customer/userreviews/editreview/{anotherid}"
  3. Observes system response
- **Expected Outcome:**
  - Access is denied (403 Forbidden)
  - User is redirected to home or error page
  - No user data is exposed

### Usability Requirements

**Scenario U1: New user creates first booking in ≤ 3 minutes**
- **Setup:** New user participates in hallway test
- **Steps:**
  1. User logs in (account pre-created)
  2. User browses listings
  3. User selects a listing and books it
  4. Record total time
- **Expected Outcome:** Time to complete booking ≤ 3 minutes

**Scenario U2: Provider can create listing in ≤ 5 minutes**
- **Setup:** New provider account; interview/walkthrough observed
- **Steps:**
  1. Provider logs in
  2. Navigates to "Create Listing"
  3. Fills in listing details (title, pricing, description, availability, location)
  4. Submits listing
  5. Record total time
- **Expected Outcome:** Time to complete ≤ 5 minutes
