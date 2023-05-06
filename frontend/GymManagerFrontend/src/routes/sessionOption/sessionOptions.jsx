import {Link, Outlet, useLoaderData} from "react-router-dom";
import {Button} from "@mui/material";
import {useState} from "react";
import moment from "moment";

export async function loader() {
    const results = await fetch('http://localhost:8081/sessionoptions/findByLoggedInTrainer',
        {headers: {Authorization: localStorage.getItem('SavedToken')}})

    if (!results.ok) throw new Error('Something went wrong!');

    return await results.json();
}

export default function SessionOptions() {
    const [deletedSessionOptions, setDeletedSessionOptions] = useState([]);

    const handleDeleteSessionOption = async (sessionOptionId) => {
        if (window.confirm('Are you sure you want to cancel this subscription?')) {
            const response = await fetch('http://localhost:8081/sessionoptions/' + sessionOptionId, {
                method: 'DELETE',
                headers: {Authorization: localStorage.getItem('SavedToken')}
            });
            if (response.ok) {
                setDeletedSessionOptions([...deletedSessionOptions, sessionOptionId]);
            }
        }
    };
    const data = useLoaderData();
    return (
        <>
            <div>
                {data.length ? (
                    <ul>
                        {data.map((sesOption) => (
                            <li key={sesOption.id}>
                                <h1>Name: {sesOption.name}</h1>
                                <p>Price: {sesOption.price}</p>
                                <p>Length: {sesOption.lengthMinutes} minutes</p>
                                <p>Description: {sesOption.description}</p>
                                <Button
                                    color="error"
                                    onClick={() => handleDeleteSessionOption(sesOption.id)}>
                                    Delete
                                </Button>
                                <Button>
                                    <Link to={'edit/' + sesOption.id}>Edit</Link>
                                </Button>
                            </li>
                        ))}
                    </ul>
                ) : (
                    <div>
                        <i>No session options</i>
                        <p><Button><Link to={'/createSessionOption'}>Create a session option</Link></Button></p>
                    </div>
                )}
            </div>
        </>
    );
}

