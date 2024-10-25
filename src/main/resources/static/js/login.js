document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('form');

    form.addEventListener('submit', function (e) {
        e.preventDefault();
        const phone = document.getElementById('phone').value;
        const password = document.getElementById('password').value;

        const userLogin = {
            phone: phone,
            password: password
        };

        console.log(userLogin.phone);
        console.log(userLogin.password);

        axios.post('/api/users/login', userLogin)
            .then(function (resp) {
                localStorage.setItem("token", resp.data);
                window.location.href = '/building-search';
            })
            .catch(function (err) {
                console.error("Login failed:", err.response || err.message);
            });
    });
});
