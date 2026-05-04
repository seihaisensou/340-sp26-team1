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
            <a class="navbar-brand fw-bold" href="/provider/home">AccessAble</a>

            <div class="d-flex gap-3">
                <a class="nav-link text-white" href="/provider/login">Login</a>
                <a class="nav-link text-white" href="/provider/sign-up">Sign Up</a>
            </div>
        </div>
    </nav>

    <div class="container d-flex justify-content-center align-items-center vh-100">

        <div class="card p-4 shadow" style="max-width: 400px; width: 100%;">

            <h3 class="text-center mb-3">Provider Login</h3>

            <#if error??>
                <div class="alert alert-danger text-center">
                    Invalid email or password
                </div>
            </#if>

            <form action="/perform_login" method="post">

                <div class="mb-3">
                    <label>Email</label>
                    <input type="email" name="email" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label>Password</label>
                    <input type="password" name="password" class="form-control" required>
                </div>

                <button class="btn btn-primary w-100">Login</button>

            </form>

        </div>
    </div>

</body>

</html>