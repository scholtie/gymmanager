import {Link, useLoaderData} from "react-router-dom";
import {Button} from "@mui/material";
import {useState} from "react";

export async function loader() {
    try {
        const results = await fetch('http://localhost:8081/sessions/findAllByLoggedInUser',
            {headers: {Authorization: localStorage.getItem('SavedToken')}})

        if (!results.ok) {
            throw new Error('Something went wrong!');
        }
        return await results.json();
    } catch (e) {
        return null;
    }
}

export default function Sessions() {
    const data = useLoaderData();
    const [deletedSessions, setDeletedSessions] = useState([]);

    const handleDeleteSession = async (sessionId) => {
        const response = await fetch('http://localhost:8081/sessions/' + sessionId, {
            method: 'DELETE',
            headers: {Authorization: localStorage.getItem('SavedToken')}
        });
        if (response.ok) {
            setDeletedSessions([...deletedSessions, sessionId]);
        }
    };
    return (
        <>
            <div>
                {data.length ? (
                    <ul>
                        {data.map((session) => (
                            <li key={session.id}>
                                <p>Session Start: {session.start}</p>
                                <p>Session Type: {session.option.name}</p>
                                <p>Session Length: {session.option.lengthMinutes} Minutes</p>
                                <p>Trainer: {session.trainer.firstName} {session.trainer.lastName}</p>
                                <p>Customer: {session.customer.firstName} {session.customer.lastName}</p>
                                <Button color="error" onClick={() => handleDeleteSession(session.id)}>Delete
                                    session</Button>
                            </li>
                        ))}
                    </ul>
                ) : (
                    <p>
                        <i>No Sessions</i>
                        <p><Button><Link to={'/book-session'}>Book a session</Link></Button></p>

                    </p>
                )}
            </div>
        </>
    );
}
