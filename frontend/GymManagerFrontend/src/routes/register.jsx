import {Form, redirect} from "react-router-dom";
import axios from "axios";
import {MenuItem, Select} from "@mui/material";
import React from "react";

export async function action({ request }) {
    const config = {
        headers: {
            'Content-Type': 'application/json'
        }
    }
    const formData = await request.formData();
    const user = Object.fromEntries(formData);
    const userJson = JSON.stringify(user);
    await axios.post('http://localhost:8081/auth/register', userJson, config)
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
                    placeholder="Password"
                    aria-label="Password"
                    type="password"
                    name="password"
                />
            </p>
            <p>
                <Select label="Gender" name="gender" id="gender" defaultValue="MALE">
                        <MenuItem value="MALE">Male</MenuItem>
                        <MenuItem value="FEMALE">Female</MenuItem>
                        <MenuItem value="OTHER">Other</MenuItem>
                </Select>
            </p>
            <p>
                <button type="submit">Register</button>
            </p>
        </Form>
    );
}
