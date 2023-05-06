import {Link, useLoaderData} from "react-router-dom";
import {Map, Marker} from "pigeon-maps"
import {Button} from "@mui/material";
import moment from "moment";


export async function loader({params}) {
    const config = {
        headers: {
            Authorization: localStorage.getItem('SavedToken')
        }
    }
    let gym;
    let average;
    let [reviews] = []
    let [subscriptionPlans] = [];
    let [businessHours] = [];
    const gymId = params.gymId;
    await fetch('http://localhost:8081/gyms/' + gymId)
        .then((res) => res.json())
        .then((data) => {
            gym = data;
        });
    await fetch('http://localhost:8081/review/gym/' + gymId)
        .then((res) => res.json())
        .then((data) => {
            reviews = data;
        });
    await fetch('http://localhost:8081/subscriptionplans/findByGym/' + gymId)
        .then((res) => res.json())
        .then((data) => {
            subscriptionPlans = data;
        });
    await fetch('http://localhost:8081/gyms/businessHours/' + gymId)
        .then((res) => res.json())
        .then((data) => {
            businessHours = data;
        });
        await fetch('http://localhost:8081/review/gym/' + gymId + '/average')
            .then((res) => res)
            .then((data) => {
                average = data;
            });
    return [gym, subscriptionPlans, reviews, average, businessHours];
}

export default function Gym() {
    const data = useLoaderData();
    const gym = data[0];
    const [subscriptionPlans] = [data[1]];
    const [reviews] = [data[2]];
    const averageRating = data[3];
    const businessHours = data[4];
    return (
        <div id="gym">
            <div>
                <img
                    key={gym.avatarImgPath}
                    src={gym.avatarImgPath || null}
                />
            </div>
            <div>
                <h1>
                    {gym.name ? (
                        <>
                            {gym.name}
                        </>
                    ) : (
                        <i>No Name</i>
                    )}{" "}
                </h1>
                <h2>{gym.address.city}</h2>
                <Map height={300} defaultCenter={[gym.address.geo.lat, gym.address.geo.lng]} defaultZoom={11}>
                    <Marker width={50} anchor={[gym.address.geo.lat, gym.address.geo.lng]}/>
                </Map>
                <p>{gym.address.street}</p>
                <p>{gym.address.suite}</p>
                <p>{gym.about}</p>
            </div>
            <div>
                <h1>Business Hours</h1>
                {businessHours.length ? (
                    <ul>
                        {businessHours.map((businessHour) => (
                            <li key={businessHour.id}>
                                <h2>{businessHour.day}</h2>
                                {businessHour.openTime ? (
                                    <>
                                        <p>Open: {moment(businessHour.openTime, "HH:mm").format("hh:mm A")}</p>
                                        <p>Close: {moment(businessHour.closeTime, "HH:mm").format("hh:mm A")}</p>
                                    </>
                                ) : (
                                    <p>CLOSED</p>
                                )}
                            </li>
                        ))}
                    </ul>
                ) : (
                    <p>
                        <i>No business hours specified</i>
                    </p>
                )}
            </div>
            <div>
                <h1>Subscription Plans</h1>
                    {subscriptionPlans.length ? (
                        <ul>
                            {subscriptionPlans.map((subPlan) => (
                                <Button>
                                    <Link to="/subscribe" state={{data: {subPlan}}}>
                                <dl>
                                    <p>{subPlan.name}</p>
                                    <p>{subPlan.description}</p>
                                    <p>Length: {subPlan.durationInDays} days</p>
                                    <p>Price: {subPlan.price} Huf</p>
                                </dl>
                                    </Link>
                                </Button>
                            ))}
                        </ul>
                    ) : (
                        <p>
                            <i>No subscription plans</i>
                        </p>
                    )}
            </div>
            <div>
                <h1>Reviews</h1>
                {reviews.length ? (
                    <ul>
                        Average rating: {averageRating}
                        {reviews.map((review) => (
                            <li key={review.id}>
                                <p>{review.customer.firstName}</p>
                                <p>{review.rating}</p>
                                <p>{review.comment}</p>
                            </li>
                        ))}
                    </ul>
                ) : (
                    <p>
                        <i>No reviews</i>
                    </p>
                )}
            </div>
            <div>
                <p><Button><Link to='/review/gym' state={{data: {gym}}}>Write a review</Link></Button></p>
            </div>
        </div>
    );
}
