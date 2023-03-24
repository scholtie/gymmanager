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
    console.log(userJson);
    await axios.post('http://localhost:8081/trainers/', userJson, config)
        .then(response => console.log(response))
        .catch(err => console.log(err))
    return redirect(`/`);
}

export default function TrainerRegistrationForm() {
    const [file, setFile] = useState()
    return (
        <Form method="post" id="register-form">
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
                    value="test@gmail.com"
                    type="email"
                    name="email"
                />
            </label>
            <p>
                <input
                    placeholder="Username"
                    value="testUserName"
                    aria-label="Username"
                    type="username"
                    name="userName"
                />
            </p>
            <p>
                <input
                    placeholder="Password"
                    value="testPassword"
                    aria-label="Password"
                    type="password"
                    name="password"
                />
            </p>
            <p>
                <select name="gender" id="gender">
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                    <option value="other">Other</option>
                </select>
            </p>
            <p>
                <select name="status" id="status">
                    <option value="available">Available</option>
                    <option value="unavailable">Unavailable</option>
                </select>
            </p>
            <p>
                <select name="gymId" id="gymId">
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