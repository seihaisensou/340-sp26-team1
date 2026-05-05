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
        <a class="btn btn-success" href="/provider/create">
          + Create Listing
        </a>
        <div class="d-flex align-items-center gap-3 nav-buttons">

          <a class="nav-link text-white" href="/provider/account">Account</a>
          <a class="nav-link text-white" href="/provider/my-listings">Listings</a>

          <#if provider??>
            <form action="/provider/logout" method="post" class="d-inline">
              <button type="submit" class="btn btn-outline-light">
                Logout
              </button>
            </form>
            <#else>
              <a class="btn btn-outline-light" href="/provider/login">Login</a>
              <a class="btn btn-primary" href="/provider/sign-up">Sign Up</a>
          </#if>

        </div>
      </div>
    </div>
  </nav>

  <div class="container py-4">
    <div class="row g-4">

      <#if listings?? && (listings?size> 0)>

        <#list listings as listing>
          <div class="col-md-4">
            <div class="card shadow-sm h-100 position-relative listing-card">

              <div class="card-body">

                <h5 class="card-title">
                  <a href="/customer/listing/${listing.listingId}">${listing.listingName!""}</a>
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
                  <a href="/provider/edit/${listing.listingId}" class="btn btn-outline-secondary btn-sm">
                    Edit
                  </a>

                  <a href="/provider/delete/${listing.listingId}" class="btn btn-outline-danger btn-sm">
                    Delete
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