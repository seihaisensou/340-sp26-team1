<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Provider Account</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
</head>

<body class="bg-light d-flex flex-column min-vh-100">

    <nav class="navbar navbar-dark bg-dark">
        <div class="container">

            <a class="navbar-brand fw-bold" href="/">
                AccessAble
            </a>

            <div class="d-flex align-items-center gap-3 nav-buttons">
                <a class="nav-link text-white" href="/provider/account">Account</a>
                <a class="nav-link text-white" href="/provider/my-listings">Listings</a>
                <a class="btn btn-outline-light" href="/provider/login">Login</a>
                <a class="btn btn-primary" href="/provider/sign-up">Sign Up</a>
            </div>

        </div>
    </nav>

    <div class="flex-grow-1">

        <div class="container py-5">

            <div class="text-center mb-5">
                <h2 class="fw-bold">Welcome Back, ${provider.firstName}</h2>
                <p class="text-muted">
                    Manage your listings, customers, and account settings below.
                </p>
            </div>

            <div class="row g-4 mb-5">

                <div class="col-md-4">
                    <a href="/provider/create" class="btn btn-primary w-100 py-3 shadow-sm">
                        + Create Listing
                    </a>
                </div>

                <div class="col-md-4">
                    <a href="/provider/my-listings" class="btn btn-outline-primary w-100 py-3 shadow-sm">
                        View My Listings
                    </a>
                </div>

                <div class="col-md-4">
                    <a href="/provider/customers" class="btn btn-outline-primary w-100 py-3 shadow-sm">
                        View Customers
                    </a>
                </div>

            </div>

            <div class="row justify-content-center mb-5">
                <div class="col-md-4">
                    <a href="/provider/reviews" class="btn btn-outline-primary w-100 py-3 shadow-sm">
                        View Reviews
                    </a>
                </div>
            </div>

        </div>

        <div class="container py-5">

            <div class="text-center mb-5">
                <h2 class="fw-bold">Account Settings</h2>
                <p class="text-muted">
                    View and update your account information below.
                </p>
            </div>

            <div class="row justify-content-center">

                <div class="col-md-5">
                    <div class="card shadow-sm p-4 text-center">

                        <img src="/images/profile-pic.jpeg" class="rounded-circle mb-3 mx-auto" width="120" height="120"
                            alt="Profile Image">

                        <h5 class="fw-bold">
                            ${provider.firstName} ${provider.lastName}
                        </h5>

                        <p class="text-muted mb-3">Provider Account</p>

                        <hr>

                        <div class="text-start">
                            <p><strong>Email:</strong> ${provider.email}</p>

                            <p><strong>Credentials:</strong></p>
                            <p class="text-muted">
                                ${provider.credentials}
                            </p>
                        </div>

                    </div>
                </div>

                <div class="col-md-7">
                    <div class="card shadow-sm p-4">

                        <h5 class="fw-bold mb-3">Update Information</h5>

                        <form action="/provider/update" method="post">

                            <div class="mb-3">
                                <label class="form-label">Email Address</label>
                                <input type="email" name="email" class="form-control" value="${provider.email}">
                            </div>

                            <div class="mb-3">
                                <label class="form-label">New Password</label>
                                <input type="password" name="password" class="form-control">
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Credentials</label>
                                <textarea name="credentials" class="form-control" rows="4">
${provider.credentials}
                        </textarea>
                            </div>

                            <button type="submit" class="btn btn-primary w-100">
                                Save Changes
                            </button>

                        </form>

                    </div>
                </div>

            </div>

        </div>

        <footer class="bg-dark text-white text-center py-4 mt-auto">
            <p class="mb-0">© 2026 AccessAble. All rights reserved.</p>
        </footer>

</body>

</html>