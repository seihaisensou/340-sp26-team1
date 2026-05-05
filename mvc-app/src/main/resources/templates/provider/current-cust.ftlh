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

            <div class="d-flex align-items-center gap-3">

                <a class="nav-link text-white" href="/provider/account">Account</a>
                <a class="nav-link text-white" href="/provider/my-listings">Listings</a>

                <#if isLoggedIn?? && isLoggedIn>

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

            <h2 class="fw-bold mb-4 text-center">Current Customers</h2>

            <div class="card shadow-sm p-4">

                <div class="table-responsive">
                    <table class="table align-middle">

                        <thead class="table-light">
                            <tr>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Service Booked</th>
                                <th>Status</th>
                                <th>Selector</th>
                            </tr>
                        </thead>

                        <tbody>

                            <#if bookings?? && (bookings?size> 0)>

                                <#list bookings as booking>

                                    <tr>

                                        <td>${booking.customer.firstName!''} ${booking.customer.lastName!''}</td>

                                        <td>${booking.customer.email!''}</td>

                                        <td>${booking.listing.listingName!"No Service"}</td>

                                        <td>
                                            <#if booking.status == "ACTIVE"><span class="badge text-bg-primary rounded-pill">${booking.status}</span>
                                            <#elseif booking.status == "PENDING"><span class="badge text-bg-info rounded-pill">${booking.status}</span>
                                            <#elseif booking.status == "CANCELLED"><span class="badge text-bg-danger rounded-pill">${booking.status}</span>
                                            <#elseif booking.status == "COMPLETED"><span class="badge text-bg-success rounded-pill">${booking.status}</span>
                                            <#elseif booking.status == "PAUSED"><span class="badge text-bg-warning rounded-pill">${booking.status}</span>
                                            <#elseif booking.status == "RESCHEDULED"><span class="badge text-bg-dark rounded-pill">${booking.status}</span></#if></td>

                                        <td>

                                            <form action="/provider/booking/update/${booking.bookingId}" method="post">
                                                <select name="status" class="form-select form-select-sm"
                                                    onchange="this.form.submit()">

                                                    <option value="PENDING" <#if booking.status=="PENDING">selected</#if>>Pending</option>

                                                    <option value="ACTIVE" <#if booking.status=="ACTIVE">selected</#if>>Active</option>

                                                    <option value="RESCHEDULED" <#if booking.status=="RESCHEDULED">selected</#if>>Rescheduled</option>

                                                    <option value="PAUSED" <#if booking.status=="PAUSED">selected</#if>>Paused</option>

                                                    <option value="COMPLETED" <#if booking.status=="COMPLETED">selected</#if>>Completed</option>

                                                    <option value="CANCELLED" <#if booking.status=="CANCELLED">selected</#if>>Cancelled</option>

                                                </select>
                                            </form>

                                        </td>

                                    </tr>

                                </#list>

                            <#else>

                                <tr>
                                    <td colspan="4" class="text-center text-muted">
                                        No bookings found.
                                    </td>
                                </tr>

                            </#if>

                        </tbody>

                    </table>
                </div>

            </div>

        </div>
    </div>

    <footer class="bg-dark text-white text-center py-4">
        <p class="mb-0">© 2026 AccessAble. All rights reserved.</p>
    </footer>

</body>

</html>