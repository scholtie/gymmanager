import {Form, redirect, useLoaderData} from "react-router-dom";
import axios from "axios";
import React, {useState} from 'react'
import {Button, MenuItem, Select} from "@mui/material";

export async function loader() {
    const results = await fetch('http://localhost:8081/gyms/',
        {headers: {Authorization: localStorage.getItem('SavedToken')}})

    if (!results.ok) throw new Error('Something went wrong!');

    return await results.json();
}

export async function action({request}) {
    const config = {
        headers: {
            'Content-Type': 'application/json',
            Authorization: localStorage.getItem('SavedToken')
        }
    }
    const formData = await request.formData();
    const user = Object.fromEntries(formData);
    const userJson = JSON.stringify(user);
    await axios.post('http://localhost:8081/trainers/', userJson, config)
        .then(response => console.log(response))
        .catch(err => console.log(err))
    return redirect(`/`);
}

export default function TrainerRegistrationForm() {
    const gyms = useLoaderData();
    return (
        <Form method="post" id="trainer-register-form">
            <p>
                <input
                    placeholder="First Name"
                    aria-label="First name"
                    type="text"
                    name="firstName"
                    required={true}
                />
                <input
                    placeholder="Last Name"
                    aria-label="Last name"
                    type="text"
                    name="lastName"
                    required={true}
                />
            </p>
            <p>
                <Select name="gender" id="gender" defaultValue='MALE'>
                    <MenuItem value="MALE">Male</MenuItem>
                    <MenuItem value="FEMALE">Female</MenuItem>
                    <MenuItem value="OTHER">Other</MenuItem>
                </Select>
            </p>
            <p>
                <Select name="status" id="status" defaultValue='available'>
                    <MenuItem value="available">Available</MenuItem>
                    <MenuItem value="unavailable">Unavailable</MenuItem>
                </Select>
            </p>
            <div>
                <Select label="Gym" name="gymId" id="gymId"
                        defaultValue={1}>
                    {gyms?.map((gym) => (
                        <MenuItem value={gym.id}>{gym.name}</MenuItem>))}
                </Select>
            </div>
            <p>
                <input
                    placeholder='Image path'
                    name='imgPath'
                />
            </p>
            <p>
                <textarea
                    placeholder="Introduction"
                    aria-label="Introduction"
                    rows="4"
                    cols="50"
                    name="introduction"
                    required={true}
                />
            </p>
            <p>
                <Button type="submit">Finalize Details</Button>
            </p>
        </Form>
    );
}
