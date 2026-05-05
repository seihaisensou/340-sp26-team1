<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Provider Account</title>
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
                        <button type="submit" class="btn btn-outline-light">
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

        <div class="container py-5">

            <div class="text-center mb-4">
                <h2 class="fw-bold">
                    Welcome Back ${provider.firstName!''}
                </h2>
                <p class="text-muted">
                    Manage your listings and account settings below.
                </p>
            </div>

            <div class="row g-4 mb-5 justify-content-center">

                <div class="col-md-6 col-lg-3">
                    <a href="/provider/create" class="btn btn-primary w-100 py-3 shadow-sm">
                        + Create Listing
                    </a>
                </div>

                <div class="col-md-6 col-lg-3">
                    <a href="/provider/my-listings" class="btn btn-outline-primary w-100 py-3 shadow-sm">
                        View Listings
                    </a>
                </div>

                <div class="col-md-6 col-lg-3">
                    <a href="/provider/current-cust" class="btn btn-outline-primary w-100 py-3 shadow-sm">
                        View Customers
                    </a>
                </div>

                <div class="col-md-6 col-lg-3">
                    <a href="/provider/reviews" class="btn btn-outline-primary w-100 py-3 shadow-sm">
                        Reviews & Replies
                    </a>
                </div>

            </div>

            <div class="row justify-content-center">

                <div class="col-md-5">

                    <div class="card shadow-sm p-4 text-center">

                        <div class="text-center mb-3">
                            <div class="text-center mb-3">
                                <img src="/providerpfp/${provider.profileImagePath}?v=${.now?long}"
                                    class="rounded-circle shadow" style="width:120px; height:120px; object-fit:cover;"
                                    alt="Profile Image">
                            </div>
                        </div>
                        <h5 class="fw-bold">
                            ${provider.firstName!''} ${provider.lastName!''}
                        </h5>

                        <p class="text-muted mb-3">Provider Account</p>

                        <hr>

                        <div class="text-start">

                            <p><strong>Email:</strong> ${provider.email!''}</p>

                            <p><strong>Credentials:</strong></p>

                            <#if provider.credentials?? && provider.credentials?has_content>
                                <p class="text-muted">${provider.credentials}</p>
                                <#else>
                                    <p class="text-muted">No credentials provided</p>
                            </#if>

                        </div>

                        <hr>

                        <form action="/provider/upload-image" method="post" enctype="multipart/form-data">

                            <div class="mb-3">
                                <label class="form-label">Upload Profile Image</label>
                                <input type="file" name="file" class="form-control" accept="image/*">
                            </div>

                            <button type="submit" class="btn btn-primary w-100">
                                Upload Image
                            </button>

                        </form>

                    </div>

                </div>

                <div class="col-md-7">

                    <div class="card shadow-sm p-4">

                        <h5 class="fw-bold mb-3">Update Information</h5>

                        <form action="/provider/update" method="post">

                            <div class="mb-3">
                                <label class="form-label">First Name</label>
                                <input type="text" name="firstName" class="form-control"
                                    value="${provider.firstName!''}">
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Last Name</label>
                                <input type="text" name="lastName" class="form-control" value="${provider.lastName!''}">
                            </div>

                            <div class="mb-3">
                                <label class="form-label">New Password</label>
                                <input type="password" name="password" class="form-control">
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Credentials</label>
                                <textarea name="credentials" class="form-control"
                                    rows="4">${provider.credentials!''}</textarea>
                            </div>

                            <button type="submit" class="btn btn-primary w-100">
                                Save Changes
                            </button>



                        </form>

                        <hr>

                        <form action="/provider/delete-account" method="post"
                            onsubmit="return confirm('Are you sure you want to delete your provider account? This cannot be undone.');">

                            <button type="submit" class="btn btn-danger w-100">
                                Delete Account
                            </button>
                        </form>

                    </div>

                </div>

            </div>

        </div>

    </div>

    <footer class="bg-dark text-white text-center py-4 mt-auto">
        <p class="mb-0">© 2026 AccessAble. All rights reserved.</p>
    </footer>

</body>

</html>