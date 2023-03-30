import axios from "axios";
import {Form, redirect, useLoaderData, useLocation} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {DatePicker, LocalizationProvider} from "@mui/x-date-pickers";
import {AdapterDayjs} from "@mui/x-date-pickers/AdapterDayjs";
import {AdapterDateFns} from "@mui/x-date-pickers/AdapterDateFns";
import {Button, MenuItem, Select, TextField} from "@mui/material";

export async function loader({state}) {
    console.log(state)
    const results = await fetch('http://localhost:8081/subscriptionplans/findByGym/1')
    //const results = await fetch('http://localhost:8081/gyms/')

    if (!results.ok) throw new Error('Something went wrong!');
    return await results.json();
}

export async function action({ request }) {
    const config = {
        headers: {
            'Content-Type': 'application/json'
        }
    }
    console.log(request)
    const formData = await request.formData();
    const subscribe = Object.fromEntries(formData);
    const subscribeJson = JSON.stringify(subscribe);
    console.log(subscribeJson)
    await axios.post('http://localhost:8081/subscriptions/subscribe', subscribeJson, config)
        .then(response => console.log(response))
        .catch(err => console.log(err))
    return redirect(`/`);
}

export default function Subscribe() {
    let {state} = useLocation();
    const [data, setData] = useState(null);
    if (state == null){
        redirect('/')
    }
    useEffect(() => {
        fetch('http://localhost:8081/subscriptionplans/findByGym/' + state.data.subPlan.gym.id)
            .then(response => response.json())
            .then(data => setData(data))
            .catch(error => console.error(error));
    }, []);
    const [selectedDate, setSelectedDate] = useState(null);
    const handleDateChange = (date) => {
        setSelectedDate(date);
    };
    return (
        <Form method="post" id="subscribe-form">
            <label>
                <Select label="Customer" name="customerId" id="customerId" value='1'>
                    <MenuItem value={"1"}>customer1</MenuItem>
                </Select>
            </label>
            <p>
                <h1>{state.data.subPlan.gym.name}</h1>
            </p>
            <p>
                <Select label="Subscription Plan" name="subscriptionPlanId" id="subscriptionPlanId"
                        defaultValue={state ? state.data.subPlan.id : 1}>
                    {data?.map((subscriptionPlan) => (
                        <MenuItem value={subscriptionPlan.id}>{subscriptionPlan.name}</MenuItem>))}
                </Select>
            </p>
            <p>
                <LocalizationProvider dateAdapter={AdapterDateFns}>
                    <DatePicker
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