import {Form, redirect, useLoaderData, useLocation} from "react-router-dom";
import React from "react";
import axios from "axios";
import {Button, MenuItem, Rating, Select, Typography} from "@mui/material";

export async function loader() {
    const results = await fetch('http://localhost:8081/gyms/',
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
    const gymReview = Object.fromEntries(formData);
    const gymReviewJson = JSON.stringify(gymReview);
    await axios.post('http://localhost:8081/review/', gymReviewJson, config)
        .then(response => console.log(response))
        .catch(err => console.log(err))
    return redirect(`/success`);
}

export default function ReviewGym() {
    const data = useLoaderData();
    let {state} = useLocation();
    const [value, setValue] = React.useState(0);
    return (
        <Form method="post" id="review-gym-form">
            <div>
                <Select label="Gym" name="gymId" id="gymId"
                        defaultValue={state ? state.data.gym.id : 1}>
                    {data?.map((gym) => (
                        <MenuItem value={gym.id}>{gym.name}</MenuItem>))}
                </Select>
            </div>
            <div>
                <textarea
                    placeholder="Comment"
                    aria-label="comment"
                    rows="4"
                    cols="50"
                    name="comment"
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
            <div>
                <Button type="submit">Send Review</Button>
            </div>
        </Form>
    );
}
