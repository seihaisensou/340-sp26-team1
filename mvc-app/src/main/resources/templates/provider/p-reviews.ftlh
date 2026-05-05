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
    <div class="flex-grow-1">    
    <div class="container py-5">
        <#list reviews as review>
        <div class="card shadow-sm p-4 mb-4">
            <div class="d-flex justify-content-between">
                <h6 class="fw-bold">
                    ${review.customer.firstName} ${review.customer.lastName}
                </h6>
                <span class="text-warning">
                   <#if (review.rating==5)>★★★★★<#elseif (review.rating==4)>★★★★<#elseif (review.rating==3)>★★★<#elseif (review.rating==2)>★★ <#else>★</#if>
                </span>
            </div>
            <p class="mt-2">${review.comment}</p>
            <#if review.reply??>
                <div class="alert alert-secondary mt-3">
                    <strong>Your reply:</strong> ${review.reply}
                </div>
                <a href="reviews/editreply/${review.reviewId}" class="btn btn-sm btn-primary">
                        Edit Reply
                </a>

                <#else>
                    <form action="/provider/review/reply/${review.reviewId}" method="post" class="mt-3">
                        <textarea name="reply" class="form-control mb-2" placeholder="Write your response..."
                            rows="2"></textarea>

                        <button type="submit" class="btn btn-sm btn-primary">
                            Post Reply
                        </button>
                    </form>
            </#if>

        </div>
    </#list></div></div>

    <footer class="bg-dark text-white text-center py-4">
        <p class="mb-0">© 2026 AccessAble. All rights reserved.</p>
    </footer>

</body>

</html>