import {Link, useLoaderData, useLocation} from "react-router-dom";
import React, {useState} from "react";
import {Button, MenuItem, Select, TextField} from "@mui/material";
import {AdapterDateFns} from "@mui/x-date-pickers/AdapterDateFns";
import {DatePicker, LocalizationProvider} from "@mui/x-date-pickers";
import {format} from 'date-fns';

export async function loader() {
    let trainers;
    await fetch('http://localhost:8081/trainers/',
        {headers: {Authorization: localStorage.getItem('SavedToken')}}).then((res) => res.json())
        .then((data) => {
            trainers = data;
        });
    return trainers
}

export default function BookSession() {
    const data = useLoaderData();
    let {state} = useLocation();
    const [selectedTrainer, setSelectedTrainer] = useState(data[0].id);
    const handleTrainerChange = event => {
        setSelectedTrainer(event.target.value);
    };
    const [selectedDate, setSelectedDate] = useState(new Date());
    const handleDateChange = date => {
        setSelectedDate(date);
    };
    const today = new Date();
    return (
        <div>
            <p>
                <Select
                    onChange={handleTrainerChange}
                    value={selectedTrainer}
                    label="Trainer"
                    name="trainerId"
                    id="trainerId"
                    defaultValue={state ? state.data.trainer.id : 1}>
                    {data?.map((trainer) => (
                        <MenuItem value={trainer.id}>{trainer.firstName} {trainer.lastName}</MenuItem>))}
                </Select>
            </p>
            <p>
                <LocalizationProvider dateAdapter={AdapterDateFns}>
                    <DatePicker
                        minDate={today}
                        id="start"
                        name="start"
                        label="Select the date of the session"
                        value={selectedDate}
                        onChange={handleDateChange}
                        textField={(params) => <TextField {...params} />}
                    />
                </LocalizationProvider></p>
            <p>
                <Button>
                    <Link to={"/book-session/" + selectedTrainer + "/" + format(selectedDate, "yyyy-MM-dd")}>See
                        available options</Link>
                </Button>
            </p>
        </div>);
}
