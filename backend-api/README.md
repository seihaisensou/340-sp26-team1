# API Documentation
**Version:** 1.1
**Last Updated:** March 26, 2026
**Base URL:** `http://localhost:8080/api`

---

## Project UML
![UML Class Diagram](../docs/uml.jpeg)

## Customer Management

### Create customer
**Endpoint:** `POST /customers`  

**Use Case:** Create Customer Account	

**Description:** Create a new customer account.

```http
POST /customers
Content-Type: application/json

{
  "location": "GSO",
  "email": "collateral@example.com",
  "firstName": "Dan",
  "lastName": "Smith",
  "password": "secret",
  "services": "English"
}
```

**Response:**
```json
{
  "firstName": "Dan",
  "lastName": "Smith",
  "location": "GSO",
  "services": "English",
  "bookings": null,
  "favorites": null,
  "reviews": null,
  "email": "collateral@example.com",
  "id": 6,
  "password": "secret",
  "role": "CUSTOMER"
}
```

**Status Code:** `201 Created`

### Get All Customers
**Endpoint:** `Get /customers`

**Use Case:** Get all customers 

**Description:** Retrieve list of all customer accounts.

```http
GET /customers
```

**Status Code:** `200 OK`

---

### Get Customer by ID
**Endpoint:** `GET /customers/{id}`

**Use Case:** Customer profile view

**Description:** Retrieve specific customer by ID.

```http
GET /customer/1
```

**Status Code:** `200 OK` or `404 Not Found`

---

### Get Customer by email
**Endpoint:** `GET /customers/email/{email}`
**Use Case:** Find customers by email 
**Description:** Retrieve specific customer by email.

```http
GET /customers/email/{email}
```

**Status Code:** `200 OK` or `404 Not Found`

---

### Update Customer
**Endpoint:** `PUT /customers/{id}`

**Use Case:** US-CUST-001 (Update Profile)

**Description:** Update customer profile information.

```http
PUT /customers/1
Content-Type: application/json

{
  "firstName": "Mask de"
}
```

**Response:** Updated customer object

**Status Code:** `200 OK` or `404 Not Found`

---

### Delete Customer
**Endpoint:** `DELETE /customer/{id}`

**Use Case:** Delete account

**Description:** Delete customer account.

```http
DELETE /customer/1
```

**Status Code:** `204 No Content` or `404 Not Found`

---

## Booking Management

### Create Booking
**Endpoint:** `POST /booking/customer/{customerId}/listing/{listingId}`  

**Use Case:** US‑CUST‑004 — Contact the provider

**Description:** Book a listing.

```http
POST /booking/customer/1/listing/1
Content-Type: application/json

{
"status" : "ACTIVE"
}
```

**Response:**
```json
{
	"bookingId": 1,
	"customer": {
		customer details here
	},
	"listing": {
		listing details here
	},
	{
		"listingId": 1
	},
	"status": "ACTIVE",
	"createdAt": "time of creation",
	"updatedAt": "same as time of creation at posting"
}

```

**Status Code:** `201 Created`

### Get All Bookings
**Endpoint:** `Get /booking`  

**Use Case:** Get All Bookings 

**Description:** Retrieve list of all bookings.

```http
GET /booking
```

**Status Code:** `200 OK`

---

### Get Booking By ID
**Endpoint:** `GET /booking/{id}`

**Use Case:** Retrieve booking by ID

**Description:** Retrieve booking listing by its ID.

```http
GET /booking/{id}
```

**Status Code:** `200 OK` or `404 Not Found`

---

### Get Booking by Customer 
**Endpoint:** `GET /booking/customer/{id}`

**Use Case:** Retrieve Bookings for Customer

**Description:** Retrieve list of all bookings for specific customer.

```http
GET /booking/customer/{id}
```

**Status Code:** `200 OK`

---

### Update Booking
**Endpoint:** `PUT /booking/{id}`

**Use Case:** US‑CUST‑004 — Contact the provider

**Description:** Update booking information.

```http
PUT /booking/1
Content-Type: application/json

{
  "status" : "CANCELLED"
}
```

**Response:** Updated Booking Object

**Status Code:** `200 OK` or `404 Not Found`

---

### Delete Booking
**Endpoint:** `DELETE /booking/{id}`

**Use Case:** Delete booking.

**Description:** Delete customer booking.

```http
DELETE /booking/1
```

**Status Code:** `204 No Content` or `404 Not Found`

---

## Favorites Management

### Create Favorite
**Endpoint:** `POST /favorites/customer/{customerId}/listing/{listingId}`  

**Use Case:** US‑CUST‑002 — Favorite listings

**Description:** Favorite a listing.
```http
POST /favorites/customer/1/listing/1
Content-Type: application/json

{

}
```

**Response:**
```json
{
	"favoritesId": 1,
	"customer": {
		 customer details
	},
	"listing": {
		 listing details
	},
	
	{
		"listingId": 1
	},
	"createdAt": "time created",
	"updatedAt": "same as time created (until updated)"
}


```

**Status Code:** `201 Created`

### Get All Favorites
**Endpoint:** `Get /favorites`  

**Use Case:** US‑CUST‑002 — Favorite listings

**Description:** Retrieve list of all favorites in the database.

```http
GET /favorites
```

**Status Code:** `200 OK`

---

### Get Favorite By ID
**Endpoint:** `GET /favorites/{id}`
**Use Case:** US‑CUST‑002 — Favorite listings
**Description:** Retrieve favorite listing by its ID.

```http
GET /favorites/{id}
```

**Status Code:** `200 OK` or `404 Not Found`

---

### Get Favorites by Customer 
**Endpoint:** `GET /favorites/customer/{id}`

**Use Case:** US‑CUST‑002 — Favorite listings

**Description:** Retrieve list of all favorites for specific customer.

```http
GET /favorites/customer/{id}
```

**Status Code:** `200 OK`

---

### Delete Favorite
**Endpoint:** `DELETE /favorites/{id}`

**Use Case:** US‑CUST‑002 — Favorite listings

**Description:** Delete customer favorite.

```http
DELETE /favorites/1
```

**Status Code:** `204 No Content` or `404 Not Found`

---

## Provider Management

### Create Provider
**Endpoint:** `POST /providers`  

**Use Case:** Create Provider Account

**Description:** Create a new provider account.

```http
POST /providers
Content-Type: application/json

{
  "email": "alice@example.com",
  "password": "secret",
  "firstName": "Alice",
  "lastName": "Smith",
  "credentials": "Bachelors in ASL"
}
```

**Response:**
```json
{
"email": "alice@example.com",
	"password": "secret",
	"firstName": "Alice",
	"lastName": "Smith",
	"credentials": "Bachelors in ASL",
	"id": 1,
	"role": "PROVIDER"
}
```

**Status Code:** `201 Created`

### Get All Providers
**Endpoint:** `Get /providers`  

**Use Case:** Get all providers 

**Description:** Retrieve list of all provider accounts.

```http
GET /providers
```

**Status Code:** `200 OK`

---

### Get Provider by ID
**Endpoint:** `GET /providers/{id}`

**Use Case:** Provider profile view

**Description:** Retrieve specific provider by ID.

```http
GET /providers/1
```

**Status Code:** `200 OK` or `404 Not Found`

---

### Get Provider's Customer List
**Endpoint:** `GET /providers/{id}/customers`

**Use Case:** US-PROV-004 (View Current Customers)

**Description:** Retrieve list of customers for specific provider.

```http
GET /providers/{id}/customers
```

**Status Code:** `200 OK`

---

### Update Provider
**Endpoint:** `PUT /providers/{id}`

**Use Case:** US-PROV-001 (Update Profile)

**Description:** Update provider profile information.


```http
PUT /providers/1
Content-Type: application/json

{
  "firstName": "Alice",
  "credentials": "Bachelors in Japanese"
}
```

**Response:** Updated customer object

**Status Code:** `200 OK` or `404 Not Found`

---

### Delete Provider
**Endpoint:** `DELETE /providers/{id}`

**Use Case:** Delete account

**Description:** Delete provider account.

```http
DELETE /providers/1
```

**Status Code:** `204 No Content` or `404 Not Found`

---

## Listing Management

### Create Listing
**Endpoint:** `POST /api/listings`  

**Use Case:** US-PROV-002 (Service Listing)

**Description:** Create a new service listing.

```http
POST /listings
Content-Type: application/json

{
"listingName" : "ASL Tutoring"
}
```

**Response:**
```json
{
"listingName": "ASL Interpreting Tutor",
	"provider": {
		"email": "alice@example.com",
		"password": "secret",
		"firstName": "Alice",
		"lastName": "Smith",
		"credentials": "Bachelors in ASL",
		"id": 1,
		"role": "PROVIDER"
		},
	"listingId": 1
}
```

**Status Code:** `201 Created`

### Get All Listings
**Endpoint:** `Get /listings`  

**Use Case:** Get All Listings 

**Description:** Retrieve list of all service listings.

```http
GET /listings
```

**Status Code:** `200 OK`

---

### Get Listing By ID
**Endpoint:** `GET /listings/{id}`

**Use Case:** Retrieve Listing by ID

**Description:** Retrieve specific listing by its ID.

```http
GET /listings/{id}
```

**Status Code:** `200 OK` or `404 Not Found`

---

### Get Listing by Provider 
**Endpoint:** `GET /listings/provider/{id}`

**Use Case:** Retrieve Listings for Provider

**Description:** Retrieve list of all service listings for specific provider.

```http
GET /listings/provider/{id}
```

**Status Code:** `200 OK`

---

### Get Listing by Description and Rating 
**Endpoint:** `GET /listings/filter/{listingname/description}`

**Use Case:** US‑CUST‑002 — Favorite listings

**Description:** Retrieve list of all service listings for specific provider. Uses ReviewService's getAverageRating to sort list using a comparator.

```http
GET /listings/filter/tutoring
```

**Status Code:** `200 OK`

---

### Update Listing
**Endpoint:** `PUT /listings/{id}`

**Use Case:** US-PROV-003 (Modify Service Listing)

**Description:** Update listing information.

```http
PUT /listings/1
Content-Type: application/json

{
  "listingName" : "ASL Interpreting"
}
```

**Response:** Updated Listing Object

**Status Code:** `200 OK` or `404 Not Found`

---

### Delete Listing
**Endpoint:** `DELETE /listings/{id}`

**Use Case:** US-PROV-003 (Delete Service Listing)

**Description:** Delete provider service listing.

```http
DELETE /providers/1
```

**Status Code:** `204 No Content` or `404 Not Found`

---

### Review Management

#### Create Review
**Endpoint:** `POST /reviews`

**Use Case:** Create Review

**Description:** review creation for provider to view and reply.

```http
POST /reviews
Content-Type: application/json

{
 "comment" : "Great communication"
 "rating" : 5.0;
}
```

**Response:**
```json
{
  {
	"comment": "Great service!",
		"provider": {
			"email": "alice@example.com",
			"password": "secret",
			"firstName": "Alice",
			"lastName": "Smith",
			"credentials": "Bachelors in ASL",
			"id": 1,
			"role": "PROVIDER"
		},
	"rating": 5,
	"reviewId": 1
	}
}
```

**Validation Rules:**
- `rating`: Must be between 1 and 5
- Review can only be created within 14-30 days of service completion

**Status Code:** `201 Created`

---

#### Get All Reviews
**Endpoint:** `GET /reviews`

**Use Case:** List of All Reviews

**Description:** Retrieve all reviews in the system.

```http
GET /reviews
```

**Status Code:** `200 OK`

---

#### Get Review by ID
**Endpoint:** `GET /reviews/{id}`

**Use Case:** Review detail view

**Description:** Retrieve specific review.

```http
GET /reviews/201
```

**Status Code:** `200 OK` or `404 Not Found`

---

#### Get Reviews by Provider
**Endpoint:** `GET /reviews/provider/{id}`

**Use Case:** US-PROV-005 (Read Reviews)

**Description:** Retrieve all reviews for a specific provider.

```http
GET /reviews/provider/1
```

**Response:** Array of reviews for the provider

**Status Code:** `200 OK`

---

### Get Rating for Provider
**Endpoint:** `GET /reviews/provider/{id}`

**Use Case:** US-PROV-006 (View Ratings)

**Description:** Retrieve all ratings for a specific provider.

```http
GET /reviews/provider/{providerId}/rating
```

**Response:** 
```
{
	"averageRating": 5
}
```
**Status Code:** `200 OK`

---

#### Update Review Reply
**Endpoint:** `PUT /reviews/provider/{providerId}/review/{reviewId}/reply`

**Use Case:** US-PROV-005 (Reply to Reviews)

**Description:** Provider reply to posted review.

```http
PUT /reviews/201
Content-Type: application/json

{
  {
  "reply": "Thank you for your feedback!"
}
}
```

**Response:** Updated review object

**Status Code:** `200 OK` or `404 Not Found`

---

#### Delete Review
**Endpoint:** `DELETE /reviews/{id}`

**Use Case:** Delete Review

**Description:** Delete a review, just to have if needed.

```http
DELETE /reviews/1
```

**Status Code:** `204 No Content` or `404 Not Found`

---

## Use Case Mapping

### Provider Use Cases

| Use Case | Description | Related Endpoints |
|----------|-------------|-------------------|
| **US-PROV-001** | Register & manage provider account | `POST /providers`, `PUT /providers/{id}`, `DELETE /providers/{id}` |
| **US-PROV-002** | Create service listings | `POST /listings` |
| **US-PROV-003** | Modify/delete service listings | `PUT /listings/{id}`, `DELETE /listings/id` |
| **US-PROV-004** | View current customers | `GET /providers/{id}/customers` |
| **US-PROV-005** | Read/reply reviews | `GET /reviews/{reviewId}/provider/{providerId}`, `PUT /reviews/provider/{providerId}/review/{reviewId}/reply`|
| **US-PROV-006** | View ratings| `GET /reviews/provider/{providerId}/rating` |

### Customer Use Cases

| Use Case | Description | Related Endpoints |
|----------|-------------|-------------------|
| **US-CUST-001** | Register & manage customer account | `POST /customers`,`PUT /customers/{id}`,`DELETE /customers/{id}` |
| **US-CUST-002** | Favorite listings | `POST /favorites/customer/{customerId}/listing/{listingId}`,`DELETE api/favorites/{id}`|
| **US-CUST-003** | Discover services | `GET /providers/{id}/customers` |
| **US-CUST-004** | Contact the provider | `POST /booking/customer/{customerId}/listing/{listingId}`, `PUT /booking/{id}`,`DELETE /booking/id` |
| **US-CUST-005** | Write Reviews | `POST /reviews/customer/{customerId}/listing/{listingId}`
| **US-CUST-006** | Read Reviews| `GET /reviews/provider/{providerId}` |




