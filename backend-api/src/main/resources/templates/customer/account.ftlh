<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Provider Account</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

</head9>

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
        <div class="container-fluid">
            <div class="row">


                <div class="container py-5">

                    <div class="text-center mb-5">
                        <h2 class="fw-bold">Welcome Back, Customer!</h2>
                        <p class="text-muted">
                            Manage your favorite services, location, and account settings below.
                        </p>
                    </div>
                    <div class="row justify-content-center mb-4">
                        <div class="col-md-4">
                            <a href="listings.html" class="btn btn-primary w-100 py-3 shadow-sm">
                                View Listings
                            </a>
                        </div>
                    </div>

                    <div class="row justify-content-center mb-4">
                        <div class="col-md-4">
                            <a href="favoritelistings.html" class="btn btn-outline-primary w-100 py-3 shadow-sm">
                                View Favorite Listings
                            </a>
                        </div>
                    </div>

                    <div class="row justify-content-center mb-4">
                        <div class="col-md-4">
                            <a href="userreviews.html" class="btn btn-outline-primary w-100 py-3 shadow-sm">
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

                    <div class="row g-4">

                        <div class="col-md-5">
                            <div class="card shadow-sm p-4 text-center">


                                <center><img src="images/egg-whole.webp" class="rounded-circle mb-3" width="120" height="120"
                                    alt="Profile Image"></center>
                                <h5 class="fw-bold">Emil Sinclair</h5>

                                <hr>

                                <div class="text-start">
                                    <p><strong>Email:</strong> esinclair@email.com</p>
                                    </p>
                                </div>

                                <div class="text-start">
                                    <p><strong>Location:</strong> Greensboro, NC</p>
                                    </p>
                                </div>

                                <div class="text-start">
                                    <p><strong>Languages:</strong> English, German</p>
                                    </p>
                                </div>

                            </div>
                        </div>


                        <div class="col-md-7">
                            <div class="card shadow-sm p-4">

                                <h5 class="fw-bold mb-3">Update Information</h5>

                                <form>

                                    <div class="mb-3">
                                        <label class="form-label">Email Address</label>
                                        <input type="email" class="form-control" value="esinclair@email.com">
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label">New Password</label>
                                        <input type="password" class="form-control" placeholder="Enter new password">
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label">Confirm Password</label>
                                        <input type="password" class="form-control" placeholder="Confirm password">
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label">Location</label>
                                        <input type="password" class="form-control" placeholder="Greensboro, NC">
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label">Languages</label>
                                        <textarea class="form-control" rows="1">English, German</textarea>
                                    </div>

                                    <button type="submit" class="btn btn-primary w-100">
                                        Save Changes
                                    </button>

                                </form>

                            </div>
                        </div>

                    </div>

                </div>

            </div>
            <footer class="bg-dark text-white text-center py-4">
                <p class="mb-0">© 2026 AccessAble. All rights reserved.</p>
            </footer>
</body>

</html>