import {Form, redirect} from "react-router-dom";
import axios from "axios";

export async function action({ request }) {
    const config = {
        headers: {
            'Content-Type': 'application/json'
        }
    }
    const formData = await request.formData();
    const gym = Object.fromEntries(formData);
    const gymJson = JSON.stringify(gym);
    await axios.post('http://localhost:8081/gyms/', gymJson, config)
        .then(response => console.log(response))
        .catch(err => console.log(err))
    return redirect(`/`);
}

export default function RegisterGym() {
    return (
        <Form method="post" id="register-gym-form">
            <p>
                <input
                    placeholder="Name"
                    aria-label="name"
                    type="text"
                    name="name"
                    value = "FunnyGym"
                />
            </p>
            <p>
                <input
                    placeholder="Lat"
                    aria-label="lat"
                    type="text"
                    name="lat"
                    value = "23.2352"
                />
                <input
                    placeholder="Lng"
                    aria-label="lng"
                    type="text"
                    name="lng"
                    value = "63.2352"
                />
            </p>
            <input
                name = 'avatarImgPath'
                value = 'https://placehold.co/400'
            />
            <p>
                <textarea
                    placeholder="About"
                    aria-label="about"
                    rows="4"
                    cols="50"
                    name="about"
                    value = "GymDescription GymDescription GymDescription GymDescription "
                />
            </p>
            <p>
                <button type="submit">Register</button>
            </p>
        </Form>
    );
}