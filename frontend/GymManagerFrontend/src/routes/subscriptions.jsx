import {Link, Outlet, useLoaderData} from "react-router-dom";
import {Button} from "@mui/material";
import {useState} from "react";

export async function loader() {
    const results = await fetch('http://localhost:8081/subscriptions/findByLoggedInUser',
        {headers: {Authorization: localStorage.getItem('SavedToken')}})

    if (!results.ok) throw new Error('Something went wrong!');

    return await results.json();
}

export default function Subscriptions() {
    const [deletedSubscriptions, setDeletedSubscriptions] = useState([]);

    const handleDeleteSubscription = async (subscriptionId) => {
        const response = await fetch('http://localhost:8081/subscriptions/' + subscriptionId, {
            method: 'DELETE',
            headers: {Authorization: localStorage.getItem('SavedToken')}
        });
        if (response.ok) {
            setDeletedSubscriptions([...deletedSubscriptions, subscriptionId]);
        }
    };
    const data = useLoaderData();

    // Format date string to a proper date format
    const formatDate = (dateString) => {
        const date = new Date(dateString);
        return date.toLocaleDateString();
    };

    return (
        <>
            <div>
                {data.length ? (
                    <ul>
                        {data.map((sub) => (
                            <li key={sub.id}>
                                <h1>{sub.subscriptionPlan.gym.name}</h1>
                                <h2>
                                    {sub.customer.firstName || sub.customer.lastName ? (
                                        <>
                                            {sub.customer.firstName} {sub.customer.lastName}
                                        </>
                                    ) : (
                                        <i>No Name</i>
                                    )}{" "}
                                </h2>
                                <p>Current Period Start: {formatDate(sub.currentPeriodStart)}</p>
                                <p>Current Period End: {formatDate(sub.currentPeriodEnd)}</p>
                                <p>Cancel at the end of the period: {String(sub.cancelAtPeriodEnd)}</p>
                                <p>Ongoing: {String(sub.ongoing)}</p>
                                <p>Payment Method: {sub.defaultPaymentMethod}</p>
                                <Button
                                    color="error"
                                    onClick={() => handleDeleteSubscription(sub.id)}>
                                    Cancel subscription
                                </Button>
                            </li>
                        ))}
                    </ul>
                ) : (
                    <p>
                        <i>No subscriptions</i>
                        <p><Button><Link to={'/gyms'}>Find a gym to subscribe to</Link></Button></p>
                    </p>
                )}
            </div>
            <div id="detail">
                <Outlet/>
            </div>
        </>
    );
}

