import {Link, Outlet} from "react-router-dom";

export default function Root() {
    return (
        <>
            <div id="sidebar">
                <nav>
                    <Link to={`register`}>Register Customer</Link>
                    <Link to={`registertrainer`}>Register Trainer</Link>
                    <Link to={`booksession`}>Book Session</Link>
                    <Link to={`trainers`}>Trainers</Link>
                </nav>
            </div>
            <div id="detail">
                <Outlet />
            </div>
        </>
    );
}