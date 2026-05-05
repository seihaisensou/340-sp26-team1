<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>Service Listings</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light d-flex flex-column min-vh-100">

  <nav class="navbar navbar-dark bg-dark">
    <div class="container">
      <a class="navbar-brand fw-bold" href="/">AccessAble</a>

      <div class="d-flex align-items-center gap-3 nav-buttons">
        <#if Session.SPRING_SECURITY_CONTEXT??>
        <#if isCustomer>
        <a class="nav-link text-white" href="/customer/account">Account</a>
        <#elseif isProvider>
        <a class="nav-link text-white" href="/provider/account">Account</a>
        </#if>
        </#if>        
        <a class="nav-link text-white" href="/customer/listings">Listings</a>
        <#if Session.SPRING_SECURITY_CONTEXT??>
        <a class="btn btn-outline-light" href="/customer/logout">Logout</a>
        </#if>
        <#if !Session.SPRING_SECURITY_CONTEXT??>
        <a class="btn btn-outline-light" href="/customer/login">Login</a>
        <a class="btn btn-primary" href="/customer/sign-up">Sign Up</a>
        </#if>
      </div>
    </div>
  </nav>

  <div class="container py-4">
    <form action="/customer/listings/search" method="get">
          <div class="mb-3">
            <label class="form-label"></label>
            <input type="text" name="search" class="form-control" placeholder="Look for a service...">
          </div>
          <button type="submit" class="btn btn-primary w-100 mb-3">
            Search
          </button>
    </form>
    <div class="row g-4">
      <#if listings?? && (listings?size> 0)>
        <#list listings as listing>
        <#assign rating = reviews.getAverageRating(listing.listingId)>
          <div class="col-md-4">
            <div class="card shadow-sm h-100 position-relative listing-card">

              <div class="card-body">

                <h5 class="card-title">
                  ${listing.listingName!""} <span class="text-warning"><#if (rating > 0)> ${rating} <#else>N/A</#if>★</span>
                </h5>

                <p class="card-text">
                  ${listing.shortDescription!""}<br> 
                  ${listing.location!""}
                </p>

                <p class="fw-bold text-primary">
                  $${listing.price!0}
                  <#if listing.pricingType??>
                    ${listing.pricingType}
                  </#if>
                </p>

                <div>            
                  <a href="/customer/listing/${listing.listingId}" class="btn btn-outline-primary btn-sm">
                    View
                  </a>
                </div>

              </div>
            </div>
          </div>
        </#list>

        <#else>
          <div class="col-12 text-center">
            <p>No listings found.</p>
          </div>
        </#if>

    </div>
  </div>

  <footer class="bg-dark text-white text-center py-4 mt-auto">
    <p class="mb-0">© 2026 AccessAble. All rights reserved.</p>
  </footer>

</body>

</html>