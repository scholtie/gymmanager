import axios from "axios";
import {Form, redirect} from "react-router-dom";
import {Button, TextField} from "@mui/material";
import {AdapterDateFns} from "@mui/x-date-pickers/AdapterDateFns";
import {DatePicker, LocalizationProvider} from "@mui/x-date-pickers";
import React, {useState} from "react";
import {format} from "date-fns";

let goalDate;

export async function action({request}) {
    const config = {
        headers: {
            'Content-Type': 'application/json',
            Authorization: localStorage.getItem('SavedToken')
        }
    }
    const formData = await request.formData();
    formData.append('date', format(goalDate, "yyyy-MM-dd"));
    const repetitionGoal = Object.fromEntries(formData);
    const repetitionGoalJson = JSON.stringify(repetitionGoal);
    await axios.post('http://localhost:8081/repetitiongoal/', repetitionGoalJson, config)
        .then(response => console.log(response))
        .catch(err => console.log(err))
    return redirect(`/progress`);
}

export default function CreateRepetitionGoal() {
    const [selectedDate, setSelectedDate] = useState(null);
    const handleDateChange = (date) => {
        setSelectedDate(date);
        goalDate = date;
    };
    const today = new Date();
    return (
        <Form method="post" id="create-repetition-goal-form">
            <p>
                <input
                    placeholder="Name"
                    aria-label="Name"
                    type="text"
                    name="name"
                />
            </p>
            <p>
                <input
                    placeholder="Value"
                    type="number"
                    name="value"
                />
            </p>
            <p>
                <input
                    placeholder="Repetition"
                    type="number"
                    name="repetitions"
                />
            </p>
            <p>
                <LocalizationProvider dateAdapter={AdapterDateFns}>
                    <DatePicker
                        minDate={today}
                        id="date"
                        name="date"
                        label="Select the completion goal date"
                        value={selectedDate}
                        onChange={handleDateChange}
                        textField={(params) => <TextField {...params} />}
                    />
                </LocalizationProvider>
            </p>
            <p>
                <Button type="submit">Set goal</Button>
            </p>
        </Form>
    );
}
