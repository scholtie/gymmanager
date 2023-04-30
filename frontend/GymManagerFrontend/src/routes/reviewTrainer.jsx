import {Form, redirect, useLoaderData, useLocation} from "react-router-dom";
import React from "react";
import axios from "axios";
import {Button, MenuItem, Rating, Select, Typography} from "@mui/material";

export async function loader() {
    const results = await fetch('http://localhost:8081/trainers/',
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
    const session = Object.fromEntries(formData);
    const sessionJson = JSON.stringify(session);
    await axios.post('http://localhost:8081/review/', sessionJson, config)
        .then(response => console.log(response))
        .catch(err => console.log(err))
    return redirect(`/success`);
}

export default function ReviewTrainer() {
    const data = useLoaderData();
    let {state} = useLocation();
    const [value, setValue] = React.useState(0);
    return (
        <Form method="post" id="review-gym-form">
            <div>
                <Select label="Trainer" name="trainerId" id="trainerId"
                        defaultValue={state ? state.data.trainer.id : 1}>
                    {data?.map((trainer) => (
                        <MenuItem value={trainer.id}>{trainer.firstName} {trainer.lastName}</MenuItem>))}
                </Select>
            </div>
            <div>
                <textarea
                    placeholder="Comment"
                    aria-label="comment"
                    rows="4"
                    cols="50"
                    name="comment"
                    value="I like this gym"
                />
            </div>
            <div>
                <Typography component="legend">Rating</Typography>
                <Rating
                    name="rating"
                    id="rating"
                    value={value}
                    onChange={(event, newValue) => {
                        setValue(newValue);
                    }}
                />
            </div>
            <input
                placeholder="CustomerId"
                aria-label="customerId"
                type="number"
                name="customerId"
                value="1"
            />
            <div>
                <Button type="submit">Send Review</Button>
            </div>
        </Form>
    );
}
