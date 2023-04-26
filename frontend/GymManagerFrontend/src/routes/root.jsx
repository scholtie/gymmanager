import {Link, Outlet} from "react-router-dom";

export default function Root() {
    return (
        <>
            <div id="sidebar">
                <nav>
                    <Link to={`register`}>Register Customer</Link>
                    <Link to={`registertrainer`}>Register Trainer</Link>
                    <Link to={`registergym`}>Register Gym</Link>
                    <Link to={`createSessionOption`}>Create Session Option</Link>
                    <Link to={`booksession`}>Book Session</Link>
                    <Link to={`trainers`}>Trainers</Link>
                    <Link to={`gyms`}>Gyms</Link>
                    <Link to={`subscribe`}>Subscribe</Link>
                    <Link to={`subscriptions`}>Subscriptions</Link>
                    <Link to={`review`}>Review</Link>
                    <Link to={`login`}>Login</Link>
                </nav>
            </div>
            <div id="detail">
                <Outlet />
            </div>
        </>
    );
}
