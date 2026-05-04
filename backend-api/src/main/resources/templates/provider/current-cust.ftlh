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

                <a class="nav-link text-white" href="/provider/account">Account</a>
                <a class="nav-link text-white" href="/provider/my-listings">Listings</a>

                <#if provider??>
                    <form action="/logout" method="post" class="d-inline">
                        <button type="submit" class="btn btn-danger">
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
                            </tr>
                        </thead>

                        <tbody>

                            <#if customers?? && (customers?size> 0)>
                                <#list customers as customer>
                                    <tr>
                                        <td>${customer.firstName} ${customer.lastName}</td>
                                        <td>${customer.email}</td>
                                        <td>
                                            <#if customer.listings?? && (customer.listings?size> 0)>
                                                ${customer.listings[0].title!"No Service"}
                                                <#else>
                                                    No Service
                                            </#if>
                                        </td>
                                        <td>
                                            <span class="badge bg-success">Active</span>
                                        </td>
                                    </tr>
                                </#list>
                                <#else>
                                    <tr>
                                        <td colspan="4" class="text-center text-muted">
                                            No customers found.
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