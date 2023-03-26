import axios from "axios";
import {Form, redirect, useLoaderData, useLocation} from "react-router-dom";
import React, {useEffect, useState} from "react";

export async function loader() {
    let options;
    let trainers;
    await fetch('http://localhost:8081/sessionoptions/').then((res) => res.json())
        .then((data) => {
            options = data;
        });
    await fetch('http://localhost:8081/trainers/').then((res) => res.json())
        .then((data) => {
            trainers = data;
        });
    return [options, trainers];
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
    await axios.post('http://localhost:8081/sessions/', sessionJson, config)
        .then(response => console.log(response))
        .catch(err => console.log(err))
    return redirect(`/`);
}

export default function BookSession() {
    const data = useLoaderData();
    let { state } = useLocation();
    return (
        <Form method="post" id="book-session-form">
            <p>
                <select name="trainerId" id="trainerId" defaultValue={state ? state.data.trainer.id:1}>(
                    {data[1].map((trainer) => (
                        <option value={trainer.id}>{trainer.firstName} {trainer.lastName}</option>))}
                </select>
            </p>
            <label>
                <select name="customerId" id="customerId" defaultValue='1'>
                    <option value="1">customer1</option>
                </select>
            </label>
            <p>
                <select name="optionId" id="optionId">(
                        {data[0].map((sessionoption) => (
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