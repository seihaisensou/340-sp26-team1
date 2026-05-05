<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Create Listing</title>
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
    </nav>

    <div class="flex-grow-1">
        <div class="container py-5">

            <div class="card shadow-lg p-4 mx-auto" style="max-width: 750px;">

                <h3 class="fw-bold mb-4 text-center">Create New Service Listing</h3>

                <form action="/provider/create" method="post" enctype="multipart/form-data">

                    <div class="mb-3">
                        <label class="form-label">Listing Name</label>
                        <input type="text" name="listingName" class="form-control"
                            placeholder="e.g. Professional Spanish Translator" required>
                    </div>


                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Category</label>
                            <select name="category" class="form-select" required>
                                <option value="Translator">Translator</option>
                                <option value="Interpretor">Interpretor</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Pricing Type</label>
                            <select name="pricingType" class="form-select" required>
                                <option value="Flat Rate">Flat Rate</option>
                                <option value="Hourly">Hourly</option>
                            </select>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label class="form-label">Pricing($)</label>
                            <input type="number" name="price" class="form-control" placeholder="Enter price" required>
                        </div>
                    </div>


                    <div class="mb-3">
                        <label class="form-label">Short Description</label>
                        <input type="text" name="shortDescription" class="form-control"
                            placeholder="Brief summary for listing card" required>
                    </div>


                    <div class="mb-3">
                        <label class="form-label">Detailed Description</label>
                        <textarea name="detailedDescription" class="form-control" rows="4"
                            placeholder="Describe your service in detail..." required></textarea>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Upload Service Image</label>
                        <input type="file" name="image" class="form-control">
                    </div>


                    <div class="mb-3">
                        <label class="form-label">Location</label>
                        <input type="text" name="location" class="form-control" placeholder="City, State">
                    </div>


                    <div class="mb-4">
                        <label class="form-label">Availability</label>
                        <input type="text" name="availability" class="form-control"
                            placeholder="Weekdays, Weekends, etc." required>
                    </div>


                    <div class="text-center">
                        <button type="submit" class="btn btn-primary btn-lg px-5">
                            Publish Listing
                        </button>
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