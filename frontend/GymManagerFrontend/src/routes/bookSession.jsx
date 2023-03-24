import axios from "axios";
import {Form, redirect} from "react-router-dom";
import React, {useEffect, useState} from "react";

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
    console.log(sessionJson)
    return redirect(`/`);
}

export default function BookSession() {
    const [data, setData] = useState([]);
    const getData = () => {
        axios.get('http://localhost:8081/sessionoptions/')
            .then(res => {
                setData(res.data)
            }).catch(err => {
            console.log(err)
        })
    }
    useEffect(getData);
    return (
        <Form method="post" id="book-session-form">
            <p>
                <select name="trainerId" id="trainerId" defaultValue='1'>
                    <option value="1">trainer1</option>
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