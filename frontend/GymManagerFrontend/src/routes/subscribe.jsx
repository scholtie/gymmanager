import axios from "axios";
import {Form, redirect, useLocation} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {DatePicker, LocalizationProvider} from "@mui/x-date-pickers";
import {AdapterDateFns} from "@mui/x-date-pickers/AdapterDateFns";
import {Button, MenuItem, Select, TextField} from "@mui/material";
import {format} from "date-fns";

let startDate = new Date();

export async function loader({state}) {
    const results = await fetch('http://localhost:8081/subscriptionplans/findByGym/1',
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
    formData.append('startDate', format(startDate, "yyyy-MM-dd"));
    const subscribe = Object.fromEntries(formData);
    const subscribeJson = JSON.stringify(subscribe);
    await axios.post('http://localhost:8081/subscriptions/subscribe', subscribeJson, config)
        .then(response => console.log(response))
        .catch(err => console.log(err))
    return redirect(`/`);
}

export default function Subscribe() {
    let {state} = useLocation();
    const [data, setData] = useState(null);
    if (state == null) {
        return redirect('/');
    }
    useEffect(() => {
        fetch('http://localhost:8081/subscriptionplans/findByGym/' + state.data.subPlan.gym.id,
            {headers: {Authorization: localStorage.getItem('SavedToken')}})
            .then(response => response.json())
            .then(data => setData(data))
            .catch(error => console.error(error));
    }, []);
    const [selectedDate, setSelectedDate] = useState(null);
    const [selectedOption, setSelectedOption] = useState(null);
    const handleDateChange = (date) => {
        setSelectedDate(date);
        startDate = date;
    };
    const handleOptionChange = (event) => {
        const optionId = parseInt(event.target.value);
        const selectedOption = data.find((option) => option.id === optionId);
        setSelectedOption(selectedOption);
    };
    const today = new Date();
    return (
        <Form method="post" id="subscribe-form">
            <p>
                <h1>{state.data.subPlan.gym.name}</h1>
            </p>
            <p>
                <Select
                    label="Subscription Plan"
                    name="subscriptionPlanId"
                    id="subscriptionPlanId"
                    defaultValue={state ? state.data.subPlan.id : 0}
                    onChange={handleOptionChange}>
                    {data?.map((subscriptionPlan) => (
                        <MenuItem value={subscriptionPlan.id}>{subscriptionPlan.name}</MenuItem>))}
                </Select>
                <p>
                    Price: {selectedOption?.price.toLocaleString("en-US", {style: "currency", currency: "HUF"})}
                </p>
                <p>
                    Duration: {selectedOption?.durationInDays} days
                </p>
                <p>
                    Description: {selectedOption?.description}
                </p>
            </p>
            <p>
                <LocalizationProvider dateAdapter={AdapterDateFns}>
                    <DatePicker
                        minDate={today}
                        id="startDate"
                        name="startDate"
                        label="Select the start date"
                        value={selectedDate}
                        onChange={handleDateChange}
                        textField={(params) => <TextField {...params} />}
                    />
                </LocalizationProvider>
            </p>
            <p>
                <Select defaultValue={"CASH"} label="Payment Method" name="paymentMethod" id="paymentMethod">
                    <MenuItem value={"CASH"}>Cash</MenuItem>
                    <MenuItem value={"CARD"}>Card</MenuItem>
                    <MenuItem value={"TRANSFER"}>Transfer</MenuItem>
                </Select>
            </p>
            <p>
                <Button type="submit">Subscribe to Gym</Button>
            </p>
        </Form>
    );
}
