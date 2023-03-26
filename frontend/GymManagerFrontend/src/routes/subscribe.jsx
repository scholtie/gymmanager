import axios from "axios";
import {Form, redirect, useLoaderData, useLocation} from "react-router-dom";
import React, {useEffect, useState} from "react";

export async function loader() {
    const results = await fetch('http://localhost:8081/subscriptionplans/findByGym/1')

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
    console.log(subscribeJson)
    await axios.post('http://localhost:8081/subscriptions/subscribe', subscribeJson, config)
        .then(response => console.log(response))
        .catch(err => console.log(err))
    return redirect(`/`);
}

export default function Subscribe() {
    const data = useLoaderData();
    let { state } = useLocation();
    return (
        <Form method="post" id="subscribe-form">
            <label>
                <select name="customerId" id="customerId" defaultValue='1'>
                    <option value="1">customer1</option>
                </select>
            </label>
            <p>
                <select name="subscriptionPlanId" id="subscriptionPlanId">(
                    {data.map((subscriptionPlan) => (
                        <option value={subscriptionPlan.id}>{subscriptionPlan.name}</option>))}
                </select>
            </p>
            <p><input
                placeholder="StartTime"
                type="datetime-local"
                name="startDate"
                id="startDate"
            /></p>
            <p>
                <select name="paymentMethod" id="paymentMethod">
                    <option value="CASH">Cash</option>
                    <option value="CARD">Card</option>
                    <option value="TRANSFER">Transfer</option>
                </select>
            </p>
            <p>
                <button type="submit">Subscribe to Gym</button>
            </p>
        </Form>
    );
}