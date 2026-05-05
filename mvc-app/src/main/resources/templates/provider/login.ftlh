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
            <a class="navbar-brand fw-bold" href="/">AccessAble</a>

            <div class="d-flex align-items-center gap-3 nav-buttons">
                <a class="nav-link text-white" href="/">Home</a>
                <a class="nav-link text-white" href="/customer/listings">Listings</a>
                <a class="btn btn-primary" href="/provider/sign-up">Sign Up</a>
            </div>
        </div>
    </nav>

    <div class="flex-grow-1">

    <div class="container d-flex justify-content-center align-items-center vh-100">

        <div class="card p-4 shadow" style="max-width: 400px; width: 100%;">

            <h3 class="text-center mb-3">Provider Login</h3>

            <p class="text-center mb-4">If you are a customer, login <a href="../customer/login">here.</a></p>

            <#if error??>
                <div class="alert alert-danger text-center">
                    Invalid email or password
                </div>
            </#if>

            <#if deleted??>
                <div class="alert alert-warning text-center">
                    Your provider account has been deleted.
                </div>
            </#if>

            <form action="/provider/login/perform_login" method="post">

                <div class="mb-3">
                    <label>Email</label>
                    <input type="email" name="email" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label>Password</label>
                    <input type="password" name="password" class="form-control" required>
                </div>

                <button class="btn btn-primary w-100">Login</button>

                <div class="text-center">
                        <small>Don't have an account?
                            <a href="/provider/sign-up">Sign Up</a>
                        </small>
                </div>

            </form>

        </div>
    </div>
</div>

</body>

</html>