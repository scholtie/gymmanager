import axios from "axios";
import {Form, redirect, useLoaderData, useLocation} from "react-router-dom";
import React, {useState} from "react";
import {MenuItem, Select} from "@mui/material";

let trainer;
let date;
let startTime;

export async function loader({params}) {
    let [options] = [];
    let [availableTimes] = [];
    let currentTrainer;
    trainer = params.trainerId;
    date = params.date;
    const config = {
        headers: {
            Authorization: localStorage.getItem('SavedToken')
        }
    }
    await fetch('http://localhost:8081/trainers/' + trainer, config).then((res) => res.json())
        .then((data) => {
            currentTrainer = data;
        });
    await fetch('http://localhost:8081/sessions/availableTimes/' + trainer + '/' + date, config).then((res) => res.json())
        .then((data) => {
            availableTimes = data;
        });
    await fetch('http://localhost:8081/sessionoptions/findByTrainer/' + trainer, config).then((res) => res.json())
        .then((data) => {
            options = data;
        });
    return [availableTimes, options, currentTrainer];
}

export async function action({request}) {
    const config = {
        headers: {
            'Content-Type': 'application/json',
            Authorization: localStorage.getItem('SavedToken')
        }
    }
    const formData = await request.formData();
    formData.append('trainerId', trainer);
    formData.append('start', date + ' ' + startTime);
    const session = Object.fromEntries(formData);
    const sessionJson = JSON.stringify(session);
    await axios.post('http://localhost:8081/sessions/', sessionJson, config)
        .then(response => console.log(response))
        .catch(err => console.log(err))
    return redirect(`/success`);
}

export default function BookSessionByDate() {
    const data = useLoaderData();
    const availableTimes = data[0];
    const options = data[1];
    const currentTrainer = data[2];
    const [selectedTime, setSelectedTime] = useState(null);
    const [selectedOption, setSelectedOption] = useState(options[0]);
    const handleOptionChange = (event) => {
        const optionId = parseInt(event.target.value);
        const selectedOption = options.find((option) => option.id === optionId);
        setSelectedOption(selectedOption);
    };
    const handleTimeChange = (event) => {
        setSelectedTime(event.target.value);
        startTime = event.target.value;
    };
    let {state} = useLocation();
    const today = new Date();
    return (
        <Form method="post" id="book-session-form">
            <h1>
                {currentTrainer.firstName} {currentTrainer.lastName}
            </h1>
            <p>
                <Select
                    defaultValue={selectedOption.id}
                    label="Option"
                    name="optionId"
                    id="optionId"
                    onChange={handleOptionChange}
                >
                    {options.map((sessionoption) => (
                        <MenuItem value={sessionoption.id} key={sessionoption.id}>
                            {sessionoption.name}
                        </MenuItem>
                    ))}
                </Select>
                <p>
                    Price: {selectedOption.price.toLocaleString("en-US", {style: "currency", currency: "HUF"})}
                </p>
                <p>
                    Length: {selectedOption.lengthMinutes} Minutes
                </p>
            </p>
            <p>
                <Select onChange={handleTimeChange} value={selectedTime}>
                    {availableTimes.map(([hour, minute]) => (
                        <MenuItem
                            value={`${hour.toString().padStart(2, "0")}:${minute
                                .toString()
                                .padStart(2, "0")}`}
                            key={`${hour}:${minute}`}
                        >
                            {`${hour.toString().padStart(2, "0")}:${minute
                                .toString()
                                .padStart(2, "0")}`}
                        </MenuItem>
                    ))}
                </Select>
            </p>
            <p>

            </p>
            <p>
                <button type="submit">Book Session</button>
            </p>
        </Form>
    );
}

