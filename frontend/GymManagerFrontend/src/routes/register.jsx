import axios from "axios";
import {Form, redirect} from "react-router-dom";
import {Button, MenuItem, Select} from "@mui/material";
import React from "react";

export async function action({request}) {
    const configPost = {
        headers: {
            'Content-Type': 'application/json'
        }
    }
    const formData = await request.formData();
    const user = Object.fromEntries(formData);
    const userJson = JSON.stringify(user);
    await axios.post('http://localhost:8081/auth/register', userJson, configPost)
        .then(response => {
            console.log(response)
            let token = response.data.access_token;
            localStorage.setItem("SavedToken", 'Bearer ' + token);
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;
        })
        .catch(err => console.log(err))
    const configRedirect = {
        headers: {
            Authorization: localStorage.getItem('SavedToken')
        }
    }
    if (user.role === "CUSTOMER") {
        return redirect('/register/customer', configRedirect);
    } else if (user.role === "TRAINER") {
        return redirect('/register/trainer', configRedirect);
    } else if (user.role === "GYM") {
        return redirect('/register/gym', configRedirect);
    }
    return null;
}

export async function loader() {
    // if (!!localStorage.getItem("SavedToken")){
    //     return redirect('/');
    // }
    // else{return null;}
    return null;
}

export default function RegistrationForm() {
    return (
        <Form method="post" id="register-form">
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
                <Select label="Role" name="role" id="role" defaultValue="CUSTOMER">
                    <MenuItem value="CUSTOMER">Customer</MenuItem>
                    <MenuItem value="TRAINER">Trainer</MenuItem>
                    <MenuItem value="GYM">Gym</MenuItem>
                </Select>
            </p>
            <p>
                <Button type="submit">Register</Button>
            </p>
        </Form>
    );
}
