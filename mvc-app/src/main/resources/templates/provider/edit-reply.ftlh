<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Manage Reviews</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

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
                <a class="btn btn-outline-light" href="/provider/logout">Logout</a>
            </div>

        </div>
    </nav>

    <div class="flex-grow-1">
        <div class="container py-5">

            <div class="text-center mb-5">
                <h2 class="fw-bold">Edit your reply</h2>
                <p class="text-muted">Revise your reply to the review.</p>                
            </div>

            <div class="card shadow-sm h-100 position-relative listing-card">

              <div class="card-body">

                <h5 class="card-title">
                  ${listing.listingName!""}
                </h5>

                <p class="card-text">
                  ${listing.shortDescription!""}<br> 
                  ${listing.location!""}
                </p>

                <p class="fw-bold text-primary">
                  $${listing.price!0}
                  <#if listing.pricingType??>
                    ${listing.pricingType}
                  </#if>
                </p>

                <div>            
                  <a href="/customer/listing/${listing.listingId}" class="btn btn-outline-primary btn-sm">
                    View
                  </a>
                </div>

              </div></div>
              <br>

            <div class="card shadow-sm p-4 mb-4">
                    <left><img src="/customerpfp/${(review.customer.profilePicturePath)!'arc.png'}" class="rounded-circle mb-3 mx-auto" width="60" height="60" alt="Profile Image"></left>
                    <div class="d-flex justify-content-between">
                       <h6 class="fw-bold">${review.customer.firstName} ${review.customer.lastName}</h6>
                       <span class="text-warning"><#if (review.rating==5)>★★★★★<#elseif (review.rating==4)>★★★★<#elseif (review.rating==3)>★★★<#elseif (review.rating==2)>★★ <#else>★</#if></span>
                    </div>
                 
                    <p class="mt-2">
                    ${review.comment}
                </p>

                <div class="alert alert-secondary mt-3">
                    <strong>Your reply:</strong> ${review.reply}
                </div>
            </div>

            <div class="card shadow-sm p-4 mb-4">

                <div class="mt-3">
                    <form id="edit-review" action="/provider/reviews/editreply/${review.reviewId}/" method="post">
                    <textarea id= "reply" name="reply" class="form-control mb-2" rows="1" placeholder="Edit your reply..."></textarea>
                    <br><br> 
                    <button type="submit" class="btn btn-primary">Post Review</button></form>
                </div>
            </div>

        </div>
    </div>
    <footer class="bg-dark text-white text-center py-4">
        <p class="mb-0">© 2026 AccessAble. All rights reserved.</p>
    </footer>
</body>

</html>