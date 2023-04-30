import {Link, useLoaderData} from "react-router-dom";
import {Button} from "@mui/material";


export async function loader() {
    let [numericGoals] = [];
    let [repetitionGoals] = [];
    const config = {
        headers: {
            Authorization: localStorage.getItem('SavedToken')
        }
    }
    await fetch('http://localhost:8081/repetitiongoal/getForLoggedInCustomer', config)
        .then((res) => res.json())
        .then((data) => {
            repetitionGoals = data;
        });
    await fetch('http://localhost:8081/numericgoal/getForLoggedInCustomer', config)
        .then((res) => res.json())
        .then((data) => {
            numericGoals = data;
        });
    return [numericGoals, repetitionGoals];
}

export default function Progress() {
    const data = useLoaderData();
    const numericGoals = data[0];
    const repetitionGoals = data[1];
    return (
        <div id="progress">
            <div id="numericGoals">
                <h1>Numeric Goals</h1>
                <p>
                    {numericGoals.length ? (
                        <ul>
                            {numericGoals.map((numericGoal) => (
                                <li key={numericGoal.id}>
                                    <p>Name: {numericGoal.name}</p>
                                    <p>Date: {numericGoal.date}</p>
                                    <p>Value: {numericGoal.value}</p>
                                </li>
                            ))}
                        </ul>
                    ) : (
                        <p>
                            <i>No numeric goals yet</i>

                        </p>
                    )}

                </p>
                <p><Button>
                    <Link to={'/progress/create-numeric-goal'}>
                        Create a numeric goal
                    </Link>

                </Button></p>
            </div>
            <div id="repetitionGoals">
                <h1>Repetition Goals</h1>
                <p>
                    {repetitionGoals.length ? (
                        <ul>
                            {repetitionGoals.map((repetitionGoal) => (
                                <li key={repetitionGoal.id}>
                                    <p>Name: {repetitionGoal.name}</p>
                                    <p>Date: {repetitionGoal.date}</p>
                                    <p>Value: {repetitionGoal.value}</p>
                                    <p>Repetitions: {repetitionGoal.repetitions}</p>
                                </li>
                            ))}
                        </ul>
                    ) : (
                        <p>
                            <i>No repetition goals specified</i>

                        </p>
                    )}

                </p>
                <p><Button>
                    <Link to={'/progress/create-repetition-goal'}>
                        Create a repetition goal
                    </Link>

                </Button></p>
            </div>
        </div>
    );
}
