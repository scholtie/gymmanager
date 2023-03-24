import {Form, redirect} from "react-router-dom";
import axios from "axios";


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
                <label htmlFor="img">Select a profile picture:</label>
                <input type="file"
                       className="profilePic"
                       name="profilePic"
                       accept="image/png, image/jpeg, image/gif"/>
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