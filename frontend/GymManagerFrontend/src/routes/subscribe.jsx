import axios from "axios";
import {Form, redirect, useLoaderData, useLocation} from "react-router-dom";
import React, {useEffect, useState} from "react";

export async function loader() {
    const results = await fetch('http://localhost:8081/subscriptionplans/')

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
    const subscribe = Object.fromEntries(formData);
    const subscribeJson = JSON.stringify(subscribe);
    await axios.post('http://localhost:8081/subscribe/', subscribeJson, config)
        .then(response => console.log(response))
        .catch(err => console.log(err))
    console.log(subscribeJson)
    return redirect(`/`);
}

export default function Subscribe() {
    const data = useLoaderData();
    let { state } = useLocation();
    return (
        <Form method="post" id="book-session-form">
            <p>
                <select name="trainerId" id="trainerId" defaultValue={state ? state.data.trainer.id:1}>
                    <option value="1">trainer1</option>
                    <option value="2">trainer2</option>
                </select>
            </p>
            <label>
                <select name="customerId" id="customerId" defaultValue='1'>
                    <option value="1">customer1</option>
                </select>
            </label>
            <p>
                <select name="optionId" id="optionId">(
                    {data.map((sessionoption) => (
                        <option value={sessionoption.id}>{sessionoption.name}</option>))}
                </select>
            </p>
            <p><input
                placeholder="StartTime"
                type="datetime-local"
                name="start"
                id="start"
            /></p>
            <p>
                <button type="submit">Book Session</button>
            </p>
        </Form>
    );
}