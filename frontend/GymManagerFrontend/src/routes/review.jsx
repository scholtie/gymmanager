import {Link} from "react-router-dom";
import React from "react";

export default function Review() {
    return (
        <div>
            <Link to={`/review/gym`}>
                Review a gym
            </Link>
            <p>
                <Link to={`/review/trainer`}>
                    Review a trainer
                </Link>
            </p>
        </div>
    );
}
