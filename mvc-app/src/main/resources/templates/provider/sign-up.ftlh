<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>Provider Sign Up</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light d-flex flex-column min-vh-100">

      <nav class="navbar navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand fw-bold" href="/">AccessAble</a>

            <div class="d-flex gap-3">
                <a class="nav-link text-white" href="/provider/login">Login</a>
                <a class="nav-link text-white" href="/provider/sign-up">Sign Up</a>
            </div>
        </div>
    </nav>

  <div class="container d-flex justify-content-center align-items-center py-5">
    <div class="card shadow-lg p-4" style="max-width: 450px; width: 100%;">

      <h3 class="text-center mb-4">Create Provider Account</h3>

      <#if error??>
        <div class="alert alert-danger">${error}</div>
      </#if>

      <form action="/provider/signup" method="post" enctype="multipart/form-data">

        <input type="text" name="firstName" value="${provider.firstName!''}" class="form-control mb-2"
          placeholder="First Name" required>

        <input type="text" name="lastName" value="${provider.lastName!''}" class="form-control mb-2"
          placeholder="Last Name" required>

        <input type="email" name="email" value="${provider.email!''}" class="form-control mb-2"
          placeholder="Email Address" required>

        <input type="password" name="password" class="form-control mb-2" placeholder="Password (min 6 characters)" required>

        <input type="file" name="file" class="form-control mb-3" required>

        <button type="submit" class="btn btn-primary w-100">
          Sign Up
        </button>

        <div class="text-center">
          <small>
            Looking for customer sign up? Sign up <a href="/customer/sign-up">here.</a>
          </small>
        </div>
        <div class="text-center">
            <small>Already have an account?
              <a href="/provider/login">Login</a>
            </small>
        </div>

      </form>

    </div>
  </div>

</body>

</html>