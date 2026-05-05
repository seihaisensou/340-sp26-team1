<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Listing Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light d-flex flex-column min-vh-100">
    <nav class="navbar navbar-dark bg-dark">
        <div class="container">


            <a class="navbar-brand fw-bold" href="index.html">
                AccessAble
            </a>


            <div class="d-flex align-items-center gap-3 nav-buttons">
                <a class="nav-link text-white" href="/customer/account">Account</a>
                <a class="nav-link text-white" href="/customer/listings">Listings</a>
                <#if Session.SPRING_SECURITY_CONTEXT??>
                <a class="btn btn-outline-light" href="/logout">Logout</a>
                </#if>
                <#if !Session.SPRING_SECURITY_CONTEXT??>
                <a class="btn btn-outline-light" href="/customer/login">Login</a>
                <a class="btn btn-primary" href="/customer/sign-up">Sign Up</a>
                </#if>
            </div>

        </div>
    </nav>

    <div class="flex-grow-1">
        <div class="container py-5">



            <div class="row">
                <div class="col-md-6">
                    <img src="images/profile-pic.jpeg" class="img-fluid rounded shadow-sm">
                </div>

                <div class="col-md-6">
                    <h2 class="fw-bold">${listing.listingName}</h2>

                    <p class="text-muted">Hosted by: ${listing.provider.firstName} ${listing.provider.lastName}</p>
                   

                    <p><strong>Service Type:</strong> ${listing.category!""}</p>

                    <p><strong>Availability:</strong> ${listing.availability!""}</p>

                    <p><strong>Price:</strong> ${listing.price!""} ${listing.pricingType!""}</p>

                    <hr>

                    <h5>Description</h5>
                    <p>
                        ${listing.detailedDescription!""}
                    </p>
                    <form action="/customer/listing/${listing.listingId}/book" method="post">
                    <button class="btn btn-primary mt-3">Book Service</button></form>
                    <form action="/customer/listing/${listing.listingId}/favorite" method="post">
                    <button class="btn btn-success mt-3">Favorite Service</button></form>

                    <hr>

                    <h5>Reviews</h5>

                    <a href="/customer/listing/writereview/${listing.listingId}"><button class="btn btn-primary mt-3">Write review</button></a>

                    <p></p>


                    <#if reviews?? && (reviews?size> 0)>
                        <#list reviews as review>
                            <div class="card shadow-sm p-4 mb-4">
                                <div class="d-flex justify-content-between">
                                    <h6 class="fw-bold">${review.customer.firstName!} ${review.customer.lastName}</h6>
                                    <span class="text-warning"><#if (review.rating==5)>★★★★★<#elseif (review.rating==4)>★★★★<#elseif (review.rating==3)>★★★<#elseif (review.rating==2)>★★ <#else>★</#if></span>
                                </div>
                        
                                <p class="mt-2">
                                    ${review.comment}
                                </p>
                            </div>     
                        </#list>
                        <#else>
                            <div class="col-12 text-center">
                                <p>No reviews found.</p>
                            </div>
                        </#if>            

                    
                 </div>
            </div>

        </div>
    </div>
    <footer class="bg-dark text-white text-center py-4">
        <p class="mb-0">© 2026 AccessAble. All rights reserved.</p>
    </footer>
</body>

</html>