# Accessable - MVC Application

A Spring MVC web application connecting Accessibility experts with customers for accurate, efficient translation services.

## Architecture Overview

This application follows the **Model-View-Controller (MVC)** pattern:

### Models (Entities)
Located in `src/main/java/com/csc340/AccessAble/Entities/`

- **User** - Base entity for authentication (abstract parent class)
- **Provider** - Extends User; manages listings and bookings
- **Customer** - Extends User; manages favorite listings and reviews, also creates bookings
- **Listing** - Services offered by Providers
- **Favorites** - Listings favorited by users for easy access
- **Booking** - Customer bookings to listings
- **Review** - Customer feedback on listings with Provider replies


### Views (Templates)
Located in `src/main/resources/templates/`

**Customer Views:**
- `customer/account.ftlh` - Overview with links to favorites, reviews, and bookings
- `customer/bookings.ftlh` - Table of current and past bookings
- `customer/editreview.ftlh` - Review editing form
- `customer/favoritelistings.ftlh` - Overview of customer's favorite listings
- `customer/listing.ftlh` - View of Provider listings with detailed descriptions
- `customer/listings.ftlh` - Small and discrete display of all listings in the app repository
- `customer/login.ftlh` - Form to sign into customer profile
- `customer/sign-up.ftlh` - Form to create customer profile
- `customer/userreviews.ftlh` - Overview of all reviews belonging to the customer
- `customer/writereview.ftlh` - Form to create a new review for a listing

**Provider Views:**
- `provider/account.ftlh` - Account overview with links to current customers, provider listings and customer reviews
- `provider/current-cust.ftlh` - Overview and dropdown to manage current customer bookings
- `provider/edit-list.ftlh` - Form to edit listing information such as pricing, description, availability
- `provider/edit-reply.ftlh` - Form to edit reply to review customer made
- `provider/my-listings.ftlh` - View all listings belonging to customer
- `provider/p-create-list.ftlh` - Create new listing
- `provider/p-reviews.ftlh` - View all reviews pertaining to provider alongside with a reply form  
- `provider/home.ftlh` - Technically belongs to provider, general landing page for users
- `provider/index.ftlh` - Duplicate of home
- `customer/login.ftlh` - Form to sign into provider profile
- `customer/sign-up.ftlh` - Form to create provider profile

**Public Pages:**
- `home.ftlh` - Landing page with role-based CTAs

### Controllers

**API Controllers** - RESTful endpoints for data operations:
- `BookingController` - Booking management
- `CustomerController` - Customer CRUD operations
- `ProviderController` - Provider profile operations
- `ListingController` - Listing management
- `ReviewController` - Review management with replies
- `FavoritesController` - Favorites management

**UI Controllers** - Page rendering and navigation:
- `HomePageController` - Public page (home)
- `ProviderUiController` - Provider account and all provider views
- `CustomerUiController` - Customer account and all customer views
- `ProviderAuthController` - Provider view handling login and sign up 
- `CustomerAuthController` - Customer view handling login and sign up 
- `ProfilePicController` - Controller handling image uploads


### Services
Located in `src/main/java/com/csc340/AccessAble/service/`

Business logic layer providing CRUD operations and domain-specific functionality:
- `CustomerService` - Customer registration, profile updates, account management
- `ProviderService` - Provider registration, profile, account management
- `ListingService` - Listing creation and management
- `FavoritesService` - Favorite creation and management
- `BookingService` - Booking creation and establishment 
- `ReviewService` - Review submission and rating based on reviews

### Repositories
Located in `src/main/java/com/csc340/AccessAble/repository/`

Data access layer interfacing with the database (Spring Data JPA):
- `CustomerRepository` - Customer retrieval, lookups and queries
- `ProviderRepository` - Provider management with retreival based on id and other parameters
- `ListingRepository` - Listing data repository, with ability to grab based off of name/description
- `FavoritesRepository` - Favorites repository (queries by customer and listingid)
- `BookingRepository` - Booking queries (by customer, listing, and provider)
- `ReviewRepository` - Review queries with filtering on description and sorting by rating

## Key Features

### User Roles & Authentication
- **Customer**: Browse products, book listings, favorite listings, leave reviews
- **Provider**: Create/manage listings, view listing reviews with customer replies, track ratings

### Customer Flow
1. Sign up and create customer profile
2. Browse available listings from providers
3. Create bookings with listings
4. Manage favorite listings (creation, deletion)
5. Leave reviews with 5-star ratings and a comment
6. View provider responses to reviews

### Provider Flow
1. Sign up and create provider profile
2. Create and manage listings (listingname, category, pricingtype, short description, detailed description, location, availability)
3. View all bookings to their listings
4. Monitor average ratings for listings
5. View customer reviews for their listings
6. Reply to customer reviews in real-time

### Navigation
All pages use a unified FreeMarker macro-based navbar that automatically adjusts based on:
- User role (provider/customer)
- Authentication status
- Responsive design (Bootstrap 5.3.2)

## Session Management
- Uses `HttpSession` for storing `customerId` and `providerId`
- Automatic redirect to signin for unauthenticated access to protected pages
- Session validation on all sensitive endpoints

## Database Relationships
- **One-to-Many**: Provider → Listings, Customer → Favorite Listings, Customer → Reviews
- **Many-to-One**: Booking → Customer/Listing/Provider, Review → Listing
- **Many-to-Many**: Provider → Customer
- **Cascade Operations**: Automatic cascading for related entity changes
- **JsonIgnoreProperties**: Prevents circular reference serialization