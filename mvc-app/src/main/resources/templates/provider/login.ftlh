<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Provider Login</title>
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
                <a class="btn btn-outline-light" href="/provider/login">Login</a>
                <a class="btn btn-primary" href="/provider/sign-up">Sign Up</a>
            </div>

        </div>
    </nav>

    <div class="flex-grow-1">

        <div class="container d-flex justify-content-center align-items-center vh-100">
            <div class="card shadow-lg p-4" style="max-width: 400px; width: 100%;">
                <h3 class="text-center mb-4">Provider Login</h3>

                <p class="text-center mb-4">If you are a customer, login
                    <a class href="/customer/login">here.</a>
                </p>

                <#if error??>
                    <div class="alert alert-danger text-center">
                        ${error}
                    </div>
                </#if>>

                <form action="/provider/login" method="post">
                    <div class="mb-3">
                        <label class="form-label">Email address</label>
                        <input type="email" name="email" class="form-control" placeholder="Enter email">
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Password</label>
                        <input type="password" name="password" class="form-control" placeholder="Enter password">
                    </div>
                    <button type="submit" class="btn btn-primary w-100 mb-3">
                        Login
                    </button>

                    <div class="text-center">
                        <p class="text-center mb-4">Don't have an account?
                    <a class href="/provider/sign-up">Sign up!</a>
                </p>
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