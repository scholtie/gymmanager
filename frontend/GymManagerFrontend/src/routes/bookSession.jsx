import axios from "axios";
import {Form, redirect, useLoaderData, useLocation} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {MenuItem, Select, TextField} from "@mui/material";
import {AdapterDateFns} from "@mui/x-date-pickers/AdapterDateFns";
import {DatePicker, DateTimePicker, LocalizationProvider} from "@mui/x-date-pickers";

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
    return redirect(`/success`);
}

export default function BookSession() {
    const data = useLoaderData();
    console.log(data)
    let { state } = useLocation();
    const [selectedDate, setSelectedDate] = useState(null);
    const handleDateChange = (date) => {
        setSelectedDate(date);
    };
    return (
        <Form method="post" id="book-session-form">
            <p>
                <Select label="Trainer" name="trainerId" id="trainerId"
                        defaultValue={state ? state.data.trainer.id : 1}>
                    {data[1]?.map((trainer) => (
                        <MenuItem value={trainer.id}>{trainer.firstName}{trainer.lastName}</MenuItem>))}
                </Select>
            </p>
            <label>
                <Select name="customerId" id="customerId" defaultValue='1'>
                    <MenuItem value="1">customer1</MenuItem>
                </Select>
            </label>
            <p>
                <Select label="Option" name="optionId" id="optionId">
                    {data[0]?.map((sessionoption) => (
                        <MenuItem value={sessionoption.id}>{sessionoption.name}</MenuItem>))}
                </Select>
            </p>
            <p>
                <LocalizationProvider dateAdapter={AdapterDateFns}>
                    <DateTimePicker
                        id="start"
                        name="start"
                        label="Select the date and time of the session"
                        value={selectedDate}
                        onChange={handleDateChange}
                        textField={(params) => <TextField {...params} />}
                    />
                </LocalizationProvider></p>
            <p>
                <button type="submit">Book Session</button>
            </p>
        </Form>
    );
}