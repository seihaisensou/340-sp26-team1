<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Customer Login</title>
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
                <a class="nav-link text-white" href="/provider/index.html">Home</a>
                <a class="nav-link text-white" href="/customer/listings">Listings</a>
                <a class="btn btn-primary" href="/customer/sign-up">Sign Up</a>
            </div>

        </div>
    </nav>

    <div class="flex-grow-1">

        <div class="container d-flex justify-content-center align-items-center vh-100">
            <div class="card shadow-lg p-4" style="max-width: 400px; width: 100%;">
                <h3 class="text-center mb-4">Customer Login</h3>

                <p class="text-center mb-4">If you are a service provider, login <a href="../provider/login">here.</a></p>

                <#if error??>
                    <div class="alert alert-danger text-center">
                        Invalid email or password
                    </div>
                </#if>

                <#if deleted??>
                <div class="alert alert-warning text-center">
                    Your customer account has been deleted.
                </div>
                </#if>

                <form action="/customer/login/perform_customer_login" method="post">
                    <div class="mb-3">
                        <label class="form-label">Email</label>
                        <input type="email" name="username" class="form-control" placeholder="Enter email">
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Password</label>
                        <input type="password" name="password" class="form-control" placeholder="Enter password">
                    </div>
                    <button type="submit" class="btn btn-primary w-100 mb-3">
                        Login
                    </button>

                    <div class="text-center">
                        <small>Don't have an account?
                            <a href="/customer/sign-up">Sign Up</a>
                        </small>
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