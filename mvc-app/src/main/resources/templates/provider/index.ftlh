<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>Home Page</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light d-flex flex-column min-vh-100">

  <nav class="navbar navbar-dark bg-dark">
    <div class="container">

      <a class="navbar-brand fw-bold" href="/">
        AccessAble
      </a>

      <div class="d-flex align-items-center gap-3">

        <a class="nav-link text-white" href="/provider/account">Account</a>
        <a class="nav-link text-white" href="/provider/my-listings">Listings</a>

        <#if isLoggedIn?? && isLoggedIn>

          <a class="nav-link text-white" href="/provider/current-cust">
            View Customers
          </a>

          <form action="/provider/logout" method="post" class="d-inline">
            <button type="submit" class="btn btn-danger">
              Logout
            </button>
          </form>

          <#else>

            <a class="btn btn-outline-light" href="/provider/login">
              Login
            </a>

            <a class="btn btn-primary" href="/provider/sign-up">
              Sign Up
            </a>

        </#if>

      </div>

    </div>
  </nav>

  <div class="flex-grow-1">

    <section class="py-5">
      <div class="container">
        <div class="row align-items-center">

          <div class="col-md-6 mb-4 mb-md-0">
            <img src="images/home.jpeg" class="img-fluid rounded shadow" alt="Service illustration">
          </div>

          <div class="col-md-6">
            <h1 class="fw-bold mb-3">
              AccessAbility Right at Your Fingertips!
            </h1>

            <blockquote class="blockquote">
              <p class="fs-5 text-muted">
                “Accessibility allows us to tap into everyone’s potential.” — Debra Ruh
              </p>
            </blockquote>

            <p class="mt-3">
              AccessAble helps you find a trusted translation or interpretation provider.
              Browse listings, compare options, and choose the right provider for you.
            </p>

            <a href="/customer/listings" class="btn btn-primary btn-lg mt-3">
              View Listings
            </a>
          </div>

        </div>
      </div>
    </section>

    <section class="bg-white py-5 border-top">
      <div class="container text-center">
        <h2 class="fw-bold mb-3">About AccessAble</h2>

        <p class="text-muted mx-auto" style="max-width: 700px;">
          We are dedicated to helping individuals and businesses connect with top-rated accessibility providers.
          Our platform makes it easy to search, compare, and contact professionals — all in one place.
        </p>
      </div>
    </section>

    <section class="py-5 text-center">
      <div class="container">
        <h3 class="mb-3">
          Ready to find the perfect listing? <br>
          Create an account below!
        </h3>

        <a href="/provider/sign-up" class="btn btn-primary btn-lg px-5">
          Create Account
        </a>
      </div>
    </section>

  </div>

  <footer class="bg-dark text-white text-center py-4 mt-auto">
    <p class="mb-0">© 2026 AccessAble. All rights reserved.</p>
  </footer>

</body>

</html>