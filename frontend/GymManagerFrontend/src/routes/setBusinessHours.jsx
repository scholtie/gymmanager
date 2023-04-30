import {Form, redirect, useLoaderData} from "react-router-dom";
import {TextField, Typography} from "@mui/material";
import React, {useState} from "react";
import axios from "axios";

export async function loader() {
    const results = await fetch('http://localhost:8081/gyms/',
        {headers: {Authorization: localStorage.getItem('SavedToken')}})
    if (!results.ok) throw new Error('Something went wrong!');
    return await results.json();
}

export async function action({request}) {
    const config = {
        headers: {
            'Content-Type': 'application/json'
        }
    }
    const formData = await request.formData();
    const businessHours = [];
    const days = ['MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'];
    days.forEach(day => {
        const openTime = formData.get(`openTime${day}`);
        const closeTime = formData.get(`closeTime${day}`);
        const available = formData.get(`available${day}`) !== null;
        businessHours.push({day: day, openTime, closeTime, available});
    })

    //const session = Object.fromEntries(formData);
    const sessionJson = JSON.stringify(businessHours);
    await axios.post('http://localhost:8081/gyms/setBusinessHours/', sessionJson, config)
        .then(response => console.log(response))
        .catch(err => console.log(err))
    return redirect(`/success`);
}

export default function SetBusinessHours() {
    const data = useLoaderData();
    const [days, setDays] = useState({
        MONDAY: true,
        TUESDAY: true,
        WEDNESDAY: true,
        THURSDAY: true,
        FRIDAY: true,
        SATURDAY: true,
        SUNDAY: true,
    });

    const handleChange = (day) => {
        setDays((prevDays) => ({
            ...prevDays,
            [day]: !prevDays[day],
        }));
    };

    return (
        <Form method="post" id="set-business-hours-form">
            {Object.keys(days).map((day) => (
                <div key={day}>
                    <Typography variant="h6" component="h2">
                        {day}
                    </Typography>

                    <div>
                        <TextField
                            name={`openTime${day}`}
                            id={`openTime${day}`}
                            label="Open Time"
                            type="time"
                            defaultValue="06:00"
                            InputLabelProps={{
                                shrink: true,
                            }}
                            inputProps={{
                                step: 300, // 5 min
                            }}
                            disabled={!days[day]}
                        />
                        <TextField
                            name={`closeTime${day}`}
                            id={`closeTime${day}`}
                            label="Close Time"
                            type="time"
                            defaultValue="23:00"
                            InputLabelProps={{
                                shrink: true,
                            }}
                            inputProps={{
                                step: 300, // 5 min
                            }}
                            disabled={!days[day]}
                        />
                        <div>
                            <label>
                                <input
                                    type="checkbox"
                                    checked={days[day]}
                                    onChange={() => handleChange(day)}
                                    name={`available${day}`}
                                    id={`available${day}`}
                                />
                                Available
                            </label>
                        </div>
                    </div>
                </div>
            ))}
            <p>
                <button type="submit">Set Business Hours</button>
            </p>
        </Form>
    );
}
