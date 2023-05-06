import {Form, Link, Outlet, redirect, useLoaderData} from "react-router-dom";
import {Button, Input} from "@mui/material";
import React, {useState} from "react";
import moment from "moment";
import axios from "axios";

let sessionOptionId;
export async function loader({params}) {
    sessionOptionId = params.sessionOptionId;
    const results = await fetch('http://localhost:8081/sessionoptions/' + sessionOptionId,
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
    formData.append('id', sessionOptionId);
    const sessionOption = Object.fromEntries(formData);
    const sessionOptionJson = JSON.stringify(sessionOption);
    await axios.post('http://localhost:8081/sessionoptions/', sessionOptionJson, config)
        .then(response => console.log(response))
        .catch(err => console.log(err))
    return redirect(`/session-options`);
}

export default function EditSessionOption() {
    const data = useLoaderData();
    return (
        <Form method="post" id="edit-session-option-form">
            <div>
                <p>Name: <input name='name' placeholder='Name' defaultValue={data.name}></input></p>
                <p>Price: <input name='price' placeholder='Price' defaultValue={data.price}></input></p>
                <p>Length: <input name='lengthMinutes' placeholder='Length' defaultValue={data.lengthMinutes}></input> minutes</p>
                <p>Description: <input name='description' placeholder='Description' defaultValue={data.description}></input></p>
            </div>
            <p>
                <Button type="submit">Save</Button>
            </p>
        </Form>
    );
}

