<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>Customer Sign Up </title>
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light d-flex flex-column min-vh-100">


  <nav class="navbar navbar-dark bg-dark">
    <div class="container">


      <a class="navbar-brand fw-bold" href="/">
        AccessAble
      </a>


      <div class="d-flex align-items-center gap-3 nav-buttons">
        <a class="nav-link text-white" href="../provider/home">Home</a>
        <a class="nav-link text-white" href="/customer/listings">Listings</a>
        <a class="btn btn-outline-light" href="/customer/login">Login</a>
      </div>

    </div>
  </nav>

  <div class="flex-grow-1">
    <div class="container d-flex justify-content-center align-items-center py-5">
      <div class="card shadow-lg p-4" style="max-width: 450px; width: 100%;">
        <h3 class="text-center mb-4">Create Customer Account</h3>

        <form action="/customer/sign-up/success" method="post">
          <div class="mb-3">
            <label class="form-label">First Name</label>
            <input type="text" name="firstName" class="form-control" placeholder="Enter first name">
          </div>

          <div class="mb-3">
            <label class="form-label">Last Name</label>
            <input type="text" name="lastName" class="form-control" placeholder="Enter last name">
          </div>

          <div class="mb-3">
            <label class="form-label">Email address</label>
            <input type="email" name="email" class="form-control" placeholder="Enter email">
          </div>

          <div class="mb-3">
            <label class="form-label">Password</label>
            <input type="password" name="password" class="form-control" placeholder="Create password">
          </div>          

          <button type="submit" class="btn btn-primary w-100 mb-3">
            Sign Up
          </button>
          <div class="text-center">
            <small>Are you a provider? Sign up
              <a href="../provider/sign-up">here.</a>
            </small>
          </div>
          <div class="text-center">
            <small>Already have an account?
              <a href="/customer/login">Login</a>
            </small>
          </div>
        </form>
      </div>
    </div>
  </div>
  <footer class="bg-dark text-white text-center py-4">
    <p class="mb-0">© 2026 AccessAble. All rights reserved.</p>
  </footer>
</body>

</html>