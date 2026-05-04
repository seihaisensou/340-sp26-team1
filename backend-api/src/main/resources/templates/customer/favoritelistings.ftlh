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


      <a class="navbar-brand fw-bold" href="../provider/index.html">
                AccessAble
            </a>


      <div class="d-flex align-items-center gap-3 nav-buttons">
        <a class="nav-link text-white" href="/customer/account">Account</a>
        <a class="nav-link text-white" href="/customer/listings">Listings</a>
        <a class="btn btn-outline-light" href="/customer/login">Logout</a>
      </div>

    </div>
  </nav>
  <div class="flex-grow-1">
    <div class="container py-5">

      <h2 class="fw-bold mb-4 text-center">Favorite Listings</h2>

      <div class="row g-4">
        <#if favorites?? && (favorites?size> 0)>
         <#list favorites as favorite>
        <div class="col-md-4">


          
          <div class="card shadow-sm h-100 position-relative listing-card">

            <div class="card-body">
              <h5 class="card-title">
                 <a href="/customer/listing/${favorite.listing.listingId}">${favorite.listing.listingName}</a>
              </h5>

              <p class="card-text">
                 ${favorite.listing.shortDescription} <br>
                Local
                <br>
                5.0 <span class="text-warning">★</strong></span>
              </p>

              <p class="fw-bold text-primary">${favorite.listing.price!""} ${favorite.listing.pricingType!""}</p>


             

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
  </div>
  <footer class="bg-dark text-white text-center py-4">
    <p class="mb-0">© 2026 AccessAble. All rights reserved.</p>
  </footer>
</body>
</html>