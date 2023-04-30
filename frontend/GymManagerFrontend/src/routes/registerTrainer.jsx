import {Form, redirect} from "react-router-dom";
import axios from "axios";
import React, {useState} from 'react'
import {Button, Select} from "@mui/material";

export async function action({request}) {
    const config = {
        headers: {
            'Content-Type': 'application/json',
            Authorization: localStorage.getItem('SavedToken')
        }
    }
    const formData = await request.formData();
    const user = Object.fromEntries(formData);
    const userJson = JSON.stringify(user);
    await axios.post('http://localhost:8081/trainers/', userJson, config)
        .then(response => console.log(response))
        .catch(err => console.log(err))
    return redirect(`/`);
}

export default function TrainerRegistrationForm() {
    const [file, setFile] = useState()
    return (
        <Form method="post" id="trainer-register-form">
            <p>
                <input
                    placeholder="First Name"
                    value="testFirstName"
                    aria-label="First name"
                    type="text"
                    name="firstName"
                />
                <input
                    placeholder="Last Name"
                    value="testLastName"
                    aria-label="Last name"
                    type="text"
                    name="lastName"
                />
            </p>
            <p>
                <Select name="gender" id="gender" defaultValue='MALE'>
                    <option value="MALE">Male</option>
                    <option value="FEMALE">Female</option>
                    <option value="OTHER">Other</option>
                </Select>
            </p>
            <p>
                <Select name="status" id="status" defaultValue='available'>
                    <option value="available">Available</option>
                    <option value="unavailable">Unavailable</option>
                </Select>
            </p>
            <p>
                <Select name="gymId" id="gymId" defaultValue='1'>
                    <option value="1">1</option>
                </Select>
            </p>
            <p>
                {/*<FilePond*/}
                {/*    files={file}*/}
                {/*    onupdatefiles={setFile}*/}
                {/*    allowMultiple={false}*/}
                {/*    server="/api"*/}
                {/*    name="files"*/}
                {/*    labelIdle='Drag & Drop your files or <span class="filepond--label-action">Browse</span>'*/}
                {/*/>*/}
                <input
                    name='imgPath'
                    value='https://placehold.co/400'
                />
            </p>
            <p>
                <textarea
                    value="testIntroductiontestIntroductiontestIntroductiontestIntroduction"
                    placeholder="Introduction"
                    aria-label="Introduction"
                    rows="4"
                    cols="50"
                    name="introduction"
                />
            </p>
            <p>
                <Button type="submit">Finalize Details</Button>
            </p>
        </Form>
    );
}
