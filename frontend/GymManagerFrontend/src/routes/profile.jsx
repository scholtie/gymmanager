import {useLoaderData} from "react-router-dom";

export async function loader() {
    const results = await fetch('http://localhost:8081/customers/loggedInUser',
        {headers: {Authorization: localStorage.getItem('SavedToken')}})
    if (!results.ok) throw new Error('Something went wrong!');
    return await results.json();
}

export default function Profile() {
    const user = useLoaderData();
    return (
        <div id="profile">
            <div>
                <h1>
                    {user.email}
                </h1>
                <p>{user.role}</p>
            </div>
        </div>
    );
}
