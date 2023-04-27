import {Link, useLoaderData} from "react-router-dom";
import {Map, Marker} from "pigeon-maps";

export async function loader() {
    console.log(localStorage.getItem('SavedToken'));
    const results = await fetch('http://localhost:8081/customers/profile',
        { headers: { Authorization:localStorage.getItem('SavedToken') }})
    console.log(results);
    if (!results.ok) throw new Error('Something went wrong!');
    return await results.json();
}

export default function Profile() {
    const user = useLoaderData();
    return (
        <div id="profile">
            <div>
                <h1>
                    {user.baseUser.email}
                </h1>
                <p></p>
            </div>
        </div>
    );
}
