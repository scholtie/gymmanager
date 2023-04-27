import {Form, redirect} from "react-router-dom";
import axios from "axios";

export async function action({ request }) {
    const config = {
        headers: {
            'Content-Type': 'application/json',
            Authorization:localStorage.getItem('SavedToken')
        }
    }
    const formData = await request.formData();
    formData.append('trainerId', 1);
    const sessionOption = Object.fromEntries(formData);
    const sessionOptionJson = JSON.stringify(sessionOption);
    console.log(sessionOptionJson);
    await axios.post('http://localhost:8081/sessionoptions/', sessionOptionJson, config)
        .then(response => console.log(response))
        .catch(err => console.log(err))
    return redirect(`/`);
}

export default function CreateSessionOption() {
    return (
        <Form method="post" id="create-session-option-form">
            <p>
                <input
                    placeholder="Name"
                    aria-label="Name"
                    type="text"
                    name="name"
                />
            </p>
            <label>
                <input
                    placeholder="Price"
                    type="number"
                    name="price"
                />
            </label>
            <p>
                <input
                    placeholder="Length in minutes"
                    aria-label="Length"
                    type="number"
                    name="lengthMinutes"
                />
            </p>
            <p>
                <input
                    placeholder="Max People"
                    aria-label="Max People"
                    type="number"
                    name="maxPeople"
                />
            </p>
            <p>
                <button type="submit">Register</button>
            </p>
        </Form>
    );
}
