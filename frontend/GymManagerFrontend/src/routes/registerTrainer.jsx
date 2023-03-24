import {Form, redirect, useLoaderData} from "react-router-dom";
import axios from "axios";
import React, { useState } from 'react'
import ReactDOM from 'react-dom'

// Import React FilePond
import { FilePond, registerPlugin } from 'react-filepond'

// Import FilePond styles
import 'filepond/dist/filepond.min.css'

// Import the Image EXIF Orientation and Image Preview plugins
// Note: These need to be installed separately
// `npm i filepond-plugin-image-preview filepond-plugin-image-exif-orientation --save`
import FilePondPluginImageExifOrientation from 'filepond-plugin-image-exif-orientation'
import FilePondPluginImagePreview from 'filepond-plugin-image-preview'
import 'filepond-plugin-image-preview/dist/filepond-plugin-image-preview.css'

// Register the plugins
registerPlugin(FilePondPluginImageExifOrientation, FilePondPluginImagePreview)

export async function action({ request }) {
    const config = {
        headers: {
            'Content-Type': 'application/json'
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
            <label>
                <input
                    placeholder="Email"
                    value="test1@gmail.com"
                    type="email"
                    name="email"
                />
            </label>
            <p>
                <input
                    placeholder="Username"
                    value="test1UserName"
                    aria-label="Username"
                    type="username"
                    name="userName"
                />
            </p>
            <p>
                <input
                    placeholder="Password"
                    value="testPassword1"
                    aria-label="Password"
                    type="password"
                    name="password"
                />
            </p>
            <p>
                <select name="gender" id="gender" defaultValue='MALE'>
                    <option value="MALE">Male</option>
                    <option value="FEMALE">Female</option>
                    <option value="OTHER">Other</option>
                </select>
            </p>
            <p>
                <select name="status" id="status" defaultValue='available'>
                    <option value="available">Available</option>
                    <option value="unavailable">Unavailable</option>
                </select>
            </p>
            <p>
                <select name="gymId" id="gymId" defaultValue='1'>
                    <option value="1">1</option>
                </select>
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
                    name = 'imgPath'
                    value = 'https://placehold.co/400'
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
                <button type="submit">Register</button>
            </p>
        </Form>
    );
}