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


            <a class="navbar-brand fw-bold" href="/">
                AccessAble
            </a>


            <div class="d-flex align-items-center gap-3 nav-buttons">
                <#if isCustomer>
                <a class="nav-link text-white" href="/customer/account">Account</a>
                <#elseif isProvider>
                <a class="nav-link text-white" href="/provider/account">Account</a>
                </#if>
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
             <div class="text-center mb-5">
                        <h2 class="fw-bold">You can't access this content!</h2>
                        <img src="/customerpfp/smile.png" alt="oops" width="128" height="128">
                        <p class="text-muted">
                            Click on account or listings to return.
                        </p>
             </div>
        </div>
    </div>
    <footer class="bg-dark text-white text-center py-4">
        <p class="mb-0">© 2026 AccessAble. All rights reserved.</p>
    </footer>
</body>

</html>