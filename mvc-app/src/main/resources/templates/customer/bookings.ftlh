<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <title>Current Customers</title>
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
                <a class="nav-link text-white" href="/customer/account">Account</a>
                <a class="nav-link text-white" href="/customer/listings">Listings</a>
                <a class="btn btn-outline-light" href="/logout">Logout</a>
        
            </div>

        </div>
    </nav>

    <div class="flex-grow-1">
        <div class="container py-5">

            <h2 class="fw-bold mb-4 text-center">Current Bookings</h2>

            <div class="card shadow-sm p-4">

                <div class="table-responsive">
                    <table class="table align-middle">
                        <thead class="table-light">
                            <tr>
                                <th>Provider Name</th>
                                <th>Provider Email</th>
                                <th>Service Booked</th>
                                <th>Status</th>
                            </tr>
                        </thead>

                        <tbody>
                            
                            <#if bookings?? && (bookings?size> 0)>
                            <#list bookings as booking>
                            <tr>
                                <td>${booking.listing.provider.firstName} ${booking.listing.provider.lastName}</td>
                                <td>${booking.listing.provider.email}</td>
                                <td>${booking.listing.listingName}</td>
                                <td><span class="badge bg-success">${booking.status}</span></td>
                            </tr>
                            </#list>
                            </#if>                                                 
                        </tbody>
                    </table>
                    <#if !bookings?? || !(bookings?size>0)>
                        <div class="col-12 text-center">
                            <p>No listings found.</p>
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