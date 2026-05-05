<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>View Reviews</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

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
                <a class="btn btn-outline-light" href="/customer/logout">Logout</a>
            </div>

        </div>
    </nav>

    <div class="flex-grow-1">
        <div class="container py-5">

            <div class="text-center mb-5">
                <h2 class="fw-bold">My Reviews</h2>
                <p class="text-muted">Read past reviews you've made.</p>
            </div>

            <#if customerreviews?? && (customerreviews?size> 0)>

            <#list customerreviews as review>
              <div class="card shadow-sm p-4 mb-4">
                    <div class="d-flex justify-content-between">
                        <h6 class="fw-bold"><a href="../customer/listing/${review.getListing().getListingId()}"> ${review.getListing().getProvider().getFirstName()} ${review.getListing().getProvider().getLastName()}</a></h6>
                        <span class="text-warning"><#if (review.rating==5)>★★★★★<#elseif (review.rating==4)>★★★★<#elseif (review.rating==3)>★★★<#elseif (review.rating==2)>★★ <#else>★</#if></span>
                    </div>

                    <p class="mt-2">
                        ${review.getComment()}
                    </p>

                    <div class="mt-3">
                        <a href="/customer/userreviews/editreview/${review.reviewId}" class="btn btn-sm btn-primary">
                            Post new edit
                        </a>
                    </div>
                </div>
            </#list>

            <#else>
            <div class="col-12 text-center">
                <p>No reviews found.</p>
            </div>
            </#if>

            
        </div>
    </div>
    <footer class="bg-dark text-white text-center py-4">
        <p class="mb-0">© 2026 AccessAble. All rights reserved.</p>
    </footer>
</body>

</html>