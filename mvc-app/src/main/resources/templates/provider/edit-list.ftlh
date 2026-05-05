<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Edit Listing</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
    <div class="container py-5">
        <h2 class="text-center mb-4">Edit Listing</h2>

        <div class="card shadow-sm p-4">
            <form action="/provider/update/${listing.listingId}" method="post">

                <input type="hidden" name="listingId" value="${listing.listingId}">

                <div class="mb-3">
                    <label class="form-label">Listing Name</label>
                    <input type="text" name="listingName" class="form-control" value="${listing.listingName!""}"
                        required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Short Description</label>
                    <input type="text" name="shortDescription" class="form-control"
                        value="${listing.shortDescription!""}">
                </div>

                <div class="mb-3">
                    <label class="form-label">Detailed Description</label>
                    <textarea name="detailedDescription" class="form-control" rows="4">
${listing.detailedDescription!""}</textarea>
                </div>

                <div class="mb-3">
                    <label class="form-label">Category</label>
                    <input type="text" name="category" class="form-control" value="${listing.category!""}">
                </div>

                <div class="mb-3">
                    <label class="form-label">Location</label>
                    <input type="text" name="location" class="form-control" value="${listing.location!""}">
                </div>

                <div class="mb-3">
                    <label class="form-label">Availability</label>
                    <input type="text" name="availability" class="form-control" value="${listing.availability!""}">
                </div>

                <div class="mb-3">
                    <label class="form-label">Pricing Type</label>
                    <select name="pricingType" class="form-control">
                        <option value="HOURLY" <#if listing.pricingType?? && listing.pricingType=="HOURLY">selected
                            </#if>>Hourly</option>
                        <option value="FIXED" <#if listing.pricingType?? && listing.pricingType=="FIXED">selected</#if>
                            >Fixed</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label">Price</label>
                    <input type="number" name="price" class="form-control" value="${listing.price!0}">
                </div>

                <div class="d-flex justify-content-between mt-4">
                    <a href="/provider/my-listings" class="btn btn-secondary">Cancel</a>
                    <button type="submit" class="btn btn-primary">Save Changes</button>
                </div>

            </form>
        </div>
    </div>

    <footer class="bg-dark text-white text-center py-3 mt-auto">
        © 2026 AccessAble
    </footer>

</body>

</html>