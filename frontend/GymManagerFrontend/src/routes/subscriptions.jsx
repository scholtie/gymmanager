import {Link, Outlet} from "react-router-dom";
import {useLoaderData} from "react-router-dom";
import Moment from 'moment';

export async function loader() {
    const results = await fetch('http://localhost:8081/subscriptions/findByGym/1')

    if (!results.ok) throw new Error('Something went wrong!');

    return await results.json();
}

export default function Subscriptions() {
    Moment.locale('en');
    const data = useLoaderData();
    return (
        <>
            <div>
                {data.length ? (
                    <ul>
                        {data.map((sub) => (
                            <li key={sub.id}>
                                <Link to={`/subscriptions/${sub.id}`}>
                                    {sub.customer.firstName || sub.customer.lastName ? (
                                        <>
                                            {sub.customer.firstName} {sub.customer.lastName}
                                        </>
                                    ) : (
                                        <i>No Name</i>
                                    )}{" "}
                                </Link>
                                <p>Current Period Start: {sub.currentPeriodStart}</p>
                                <p>Current Period End: {sub.currentPeriodEnd}</p>
                                <p>Cancel at the end of the period: {String(sub.cancelAtPeriodEnd)}</p>
                                <p>Ongoing: {String(sub.ongoing)}</p>
                                <p>Payment Method: {sub.defaultPaymentMethod}</p>
                                {/*<p>{this.address.map((address) => {address})}</p>*/}
                                {/*<p>*/}
                                {/*    <Link to="/subscribe" state={{ data: {gym} }} >*/}
                                {/*        Subscribe to this gym*/}
                                {/*    </Link>*/}
                                {/*</p>*/}
                            </li>
                        ))}
                    </ul>
                ) : (
                    <p>
                        <i>No contacts</i>
                    </p>
                )}
            </div>
            <div id="detail">
                <Outlet />
            </div>
        </>
    );
}