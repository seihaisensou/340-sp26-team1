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


            <a class="navbar-brand fw-bold" href="../provider/index.html">
                AccessAble
            </a>


            <div class="d-flex align-items-center gap-3 nav-buttons">
               <a class="nav-link text-white" href="/customer/account">Account</a>
                <a class="nav-link text-white" href="/customer/listings">Listings</a>
                <a class="btn btn-outline-light" href="/login">Logout</a>
            </div>

        </div>
    </nav>

    <div class="flex-grow-1">
        <div class="container py-5">

            <div class="text-center mb-5">
                <h2 class="fw-bold">Write a Review</h2>
                <p class="text-muted">Give feedback to the provider.</p>
            </div>

            <div class="card shadow-sm p-4 mb-4">

                <div class="mt-3">
                    <form id="new-review" action="${listing.listingId}" method="post" enctype="multipart/form-data">
                    <textarea id= "comment" name="comment" class="form-control mb-2" rows="1" placeholder="Write your review..."></textarea>
                    <select name="rating" id="rating">
                        <option value="5">5 stars</option>
                        <option value="4">4 stars</option>
                        <option value="3">3 stars</option>
                        <option value="2">2 stars</option>
                        <option value="1">1 stars</option>
                    </select>

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