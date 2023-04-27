import axios from "axios";
import {Form, Link, redirect} from "react-router-dom";

export default function Success() {
    return (
        <div>
        <h1>Successful</h1>
            <h2>

                <Link to="/">
                Return to home page
                </Link>
            </h2>
        </div>)
}
