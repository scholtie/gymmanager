import {Form, redirect} from "react-router-dom";
import {updateContact} from "../contacts.js";
import {useState} from "react";
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
    console.log(userJson);
    await axios.post('http://localhost:8081/customers/', userJson, config)
            .then(response => console.log(response))
            .catch(err => console.log(err))
    return redirect(`/`);
}

export default function RegistrationForm() {
    return (
        <Form method="post" id="register-form">
            <p>
                <input
                    placeholder="First Name"
                    aria-label="First name"
                    type="text"
                    name="firstName"
                />
                <input
                    placeholder="Last Name"
                    aria-label="Last name"
                    type="text"
                    name="lastName"
                />
            </p>
            <label>
                <input
                    placeholder="Email"
                    type="email"
                    name="email"
                />
            </label>
            <p>
                <input
                    placeholder="Username"
                    aria-label="Username"
                    type="username"
                    name="userName"
                    />
            </p>
            <p>
                <input
                    placeholder="Password"
                    aria-label="Password"
                    type="password"
                    name="password"
                />
            </p>
            <p>
                <button type="submit">Register</button>
            </p>
        </Form>
    );
}