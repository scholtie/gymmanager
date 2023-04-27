import {Form, Link, Outlet, redirect, useLoaderData} from "react-router-dom";
import {Input, MenuItem, Select, Typography} from "@mui/material";
import React, {useState} from "react";
import axios from "axios";
import {LocalizationProvider, TimePicker} from "@mui/x-date-pickers";
import {AdapterDateFns} from "@mui/x-date-pickers/AdapterDateFns";

export async function loader() {
    console.log(localStorage.getItem('SavedToken'));
    const results = await fetch('http://localhost:8081/gyms/',
        { headers: { Authorization:localStorage.getItem('SavedToken') }})
    if (!results.ok) throw new Error('Something went wrong!');
    return await results.json();
}

export async function action({ request }) {
    const config = {
        headers: {
            'Content-Type': 'application/json'
        }
    }
    const formData = await request.formData();
    const session = Object.fromEntries(formData);
    const sessionJson = JSON.stringify(session);
    await axios.post('http://localhost:8081/gyms/setBusinessHours/', sessionJson, config)
        .then(response => console.log(response))
        .catch(err => console.log(err))
    return redirect(`/success`);
}

export default function SetBusinessHours() {
    const data = useLoaderData();
    return (
        <Form method="post" id="set-business-hours-form">
            <div>
                <Select defaultValue={1} label="Gym" name="gymId" id="gymId">
                    {data?.map((gym) => (
                        <MenuItem value={gym.id}>{gym.name}</MenuItem>))}
                </Select>
            </div>
            <div>
                <h2>You can modify each day later</h2>
                <Input type={'time'} id={'openTime'}/>
                <Input type={'time'} id={'closeTime'}/>
            </div>
            <p>
                <button type="submit">Set Business Hours</button>
            </p>
        </Form>
    );
}
