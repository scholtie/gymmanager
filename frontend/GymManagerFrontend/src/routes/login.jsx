import {Form, redirect} from "react-router-dom";
import axios from "axios";

export async function action({ request }) {
    const config = {
        headers: {
            'Content-Type': 'application/json'
        }
    }
    const formData = await request.formData();
    const user = Object.fromEntries(formData);
    const userJson = JSON.stringify(user);
    await axios.post('http://localhost:8081/auth/login', userJson, config)
        .then(response => {
            console.log(response)
            let token = response.data.token;
            localStorage.setItem("SavedToken", 'Bearer ' + token);
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;
        })
        .catch(err => console.log(err))

    return redirect(`/`);
}

export default function Login() {
    return (
        <Form method="post" id="login-form">
            <label>
                <input
                    placeholder="Email"
                    type="email"
                    name="email"
                />
            </label>
            <p>
                <input
                    placeholder="Password"
                    aria-label="Password"
                    type="password"
                    name="password"
                />
            </p>
            <p>
                <button type="submit">Login</button>
            </p>
        </Form>
    );
}
