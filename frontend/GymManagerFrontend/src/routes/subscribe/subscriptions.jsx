import {Link, Outlet, useLoaderData} from "react-router-dom";
import {Button} from "@mui/material";
import {useState} from "react";
import moment from "moment";

export async function loader() {
    const results = await fetch('http://localhost:8081/subscriptions/findByLoggedInUser',
        {headers: {Authorization: localStorage.getItem('SavedToken')}})

    if (!results.ok) throw new Error('Something went wrong!');

    return await results.json();
}

export default function Subscriptions() {
    const [deletedSubscriptions, setDeletedSubscriptions] = useState([]);

    const handleDeleteSubscription = async (subscriptionId) => {
        if (window.confirm('Are you sure you want to cancel this subscription? It will remain available until its current end date')) {
            const response = await fetch('http://localhost:8081/subscriptions/' + subscriptionId + '/cancelAtPeriodEnd', {
                method: 'GET',
                headers: {Authorization: localStorage.getItem('SavedToken')}
            });
            if (response.ok) {
                setDeletedSubscriptions([...deletedSubscriptions, subscriptionId]);
                window.location.reload();
            }
        }
    };
    const data = useLoaderData();
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
                                <h3>Customer email: {sub.customer.baseUser.email}</h3>
                                <p>Current Period Start: {moment(sub.currentPeriodStart, "yyyy.MM.DD").format("yyyy.MM.DD")}</p>
                                <p>Current Period End: {moment(sub.currentPeriodEnd, "yyyy.MM.DD").format("yyyy.MM.DD")}</p>
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
                    <div>
                        <i>No subscriptions</i>
                        <p><Button><Link to={'/gyms'}>Find a gym to subscribe to</Link></Button></p>
                    </div>
                )}
            </div>
        </>
    );
}

