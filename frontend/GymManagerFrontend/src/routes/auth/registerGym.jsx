import {Form, redirect} from "react-router-dom";
import axios from "axios";
import {Button} from "@mui/material";
import React from "react";

export async function action({request}) {
    const config = {
        headers: {
            'Content-Type': 'application/json',
            Authorization: localStorage.getItem('SavedToken')
        }
    }
    const formData = await request.formData();
    const gym = Object.fromEntries(formData);
    const gymJson = JSON.stringify(gym);
    await axios.post('http://localhost:8081/gyms/', gymJson, config)
        .then(response => console.log(response))
        .catch(err => console.log(err))
    return redirect(`/`);
}

export default function RegisterGym() {
    return (
        <Form method="post" id="register-gym-form">
            <p>
                <input
                    placeholder="Name"
                    aria-label="name"
                    type="text"
                    name="name"
                    required={true}
                />
            </p>
            <p>
                <input
                    placeholder="Lat"
                    aria-label="lat"
                    type="text"
                    name="lat"
                    required={true}
                />
                <input
                    placeholder="Lng"
                    aria-label="lng"
                    type="text"
                    name="lng"
                    required={true}
                />
            </p>
            <input
                name='avatarImgPath'
                placeholder='Image path'
            />
            <p>
                <textarea
                    placeholder="About"
                    aria-label="about"
                    rows="4"
                    cols="50"
                    name="about"
                    required={true}
                />
            </p>
            <p>
                <Button type="submit">Finalize Details</Button>
            </p>
        </Form>
    );
}
