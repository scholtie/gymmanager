import {Link, useLoaderData} from "react-router-dom";
import {Button} from "@mui/material";
import moment from "moment/moment.js";
import {useState} from "react";


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
    const [deletedNumericGoals, setDeletedNumericGoals] = useState([]);
    const [deletedRepetitionGoals, setDeletedRepetitionGoals] = useState([]);
    const data = useLoaderData();
    const numericGoals = data[0];
    const repetitionGoals = data[1];
    const handleDeleteNumericGoal = async (numericGoalId) => {
        if (window.confirm('Are you sure you want to delete this goal?')) {
            const response = await fetch('http://localhost:8081/numericgoal/' + numericGoalId, {
                method: 'DELETE',
                headers: {Authorization: localStorage.getItem('SavedToken')}
            });
            if (response.ok) {
                setDeletedNumericGoals([...deletedNumericGoals, numericGoalId]);
            }
        }
    };
    const handleDeleteRepetitionGoal = async (repetitionGoalId) => {
        if (window.confirm('Are you sure you want to delete this goal?')) {
            const response = await fetch('http://localhost:8081/repetitionoal/' + repetitionGoalId, {
                method: 'DELETE',
                headers: {Authorization: localStorage.getItem('SavedToken')}
            });
            if (response.ok) {
                setDeletedRepetitionGoals([...deletedRepetitionGoals, repetitionGoalId]);
            }
        }
    };
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
                                    <p>Date: {moment(numericGoal.date, "yyyy.MM.DD").format("yyyy.MM.DD")}</p>
                                    <p>Value: {numericGoal.value}</p>
                                    <Button
                                        color="error"
                                        onClick={() => handleDeleteNumericGoal(numericGoal.id)}>
                                        Delete progress
                                    </Button>
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
                                    <p>Date: {moment(repetitionGoal.date, "yyyy.MM.DD").format("yyyy.MM.DD")}</p>
                                    <p>Value: {repetitionGoal.value}</p>
                                    <p>Repetitions: {repetitionGoal.repetitions}</p>
                                    <Button
                                        color="error"
                                        onClick={() => handleDeleteRepetitionGoal(repetitionGoal.id)}>
                                        Delete progress
                                    </Button>
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
