import {Link, Outlet, useLoaderData} from "react-router-dom";
import {Button} from "@mui/material";

export async function loader() {
    const config = {
        headers: {
            Authorization: localStorage.getItem('SavedToken')
        }
    }
    try {
        if (localStorage.getItem('SavedToken')) {
            const results = await fetch('http://localhost:8081/customers/loggedInUser', config);
            return await results.json();
        }
    } catch (e) {
        return null;
    }
    return null;
}

export default function Root() {
    const data = useLoaderData();
    let userType;
    if (data != null) {
        userType = data.role;
    }
    return (
        <>
            <div id="sidebar">
                <nav>
                    {data === null && <Link to={`register`}>Register</Link>}
                    {userType === 'TRAINER' && <Link to={`createSessionOption`}>Create Session Option</Link>}
                    {userType === 'CUSTOMER' && <Link to={`book-session`}>Book Session</Link>}
                    <Link to={`trainers`}>Trainers</Link>
                    <Link to={`gyms`}>Gyms</Link>
                    {(userType === 'GYM' || userType === 'CUSTOMER') && <Link to={`subscriptions`}>Subscriptions</Link>}
                    {userType === 'CUSTOMER' && <Link to={`review`}>Review</Link>}
                    {data == null && <Link to={`login`}>Login</Link>}
                    {(userType === 'GYM' || userType === 'TRAINER') &&
                        <Link to={`setBusinessHours`}>Set Business Hours</Link>}
                    {data != null && <Link to={`profile`}>Profile</Link>}
                    {(userType === 'CUSTOMER' || userType === 'TRAINER') && <Link to={`sessions`}>My Sessions</Link>}
                    {userType === 'CUSTOMER' && <Link to={`progress`}>My Progress</Link>}
                    {userType === 'TRAINER' && <Link to={`session-options`}>My Session Options</Link>}
                    {data != null && <Button color="error"> <Link to={`logout`}>Logout</Link></Button>}
                </nav>
            </div>
            <div id="detail">
                <Outlet/>
            </div>
        </>
    );
}
