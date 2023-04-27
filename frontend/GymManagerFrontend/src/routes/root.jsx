import {Link, Outlet, redirect, useLoaderData} from "react-router-dom";

export async function loader() {
    // const config = {
    //     headers: {
    //         Authorization:localStorage.getItem('SavedToken')
    //     }
    // }
    // if (localStorage.getItem('SavedToken') != null) {
    //     const results = await fetch('http://localhost:8081/customers/loggedInCustomer', config);
    //     return await results.json();
    // }
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
                    {userType === null && <Link to={`register/customer`}>Register Customer</Link>}
                    {userType === null && <Link to={`register/trainer`}>Register Trainer</Link>}
                    {userType === null && <Link to={`register/gym`}>Register Gym</Link>}
                    {userType === 'TRAINER' && <Link to={`createSessionOption`}>Create Session Option</Link>}
                    {userType === 'CUSTOMER' && <Link to={`book-session`}>Book Session</Link>}
                    <Link to={`trainers`}>Trainers</Link>
                    <Link to={`gyms`}>Gyms</Link>
                    {userType === 'CUSTOMER' && <Link to={`subscribe`}>Subscribe</Link>}
                    {userType === 'GYM' && <Link to={`subscriptions`}>Subscriptions</Link>}
                    {userType === 'CUSTOMER' && <Link to={`review`}>Review</Link>}
                    <Link to={`login`}>Login</Link>
                    {userType === 'GYM' && <Link to={`setBusinessHours`}>Set Business Hours</Link>}
                    {data != null && <Link to={`profile`}>Profile</Link>}
                    {data != null && <Link to={`logout`}>Logout</Link>}
                </nav>
            </div>
            <div id="detail">
                <Outlet />
            </div>
        </>
    );
}
