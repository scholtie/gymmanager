import {Link, useLoaderData} from "react-router-dom";
import {Map, Marker} from "pigeon-maps"


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
    try {
        await fetch('http://localhost:8081/review/gym/' + gymId + '/average')
            .then((res) => res.json())
            .then((data) => {
                average = data;
            });
    } catch (e) {
    }
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
                <p>
                    {businessHours.length ? (
                        <ul>
                            {businessHours.map((businessHour) => (
                                <li key={businessHour.id}>
                                    <p>{businessHour.day}</p>
                                    <p>{businessHour.openTime}</p>
                                    <p>{businessHour.closeTime}</p>
                                </li>
                            ))}
                        </ul>
                    ) : (
                        <p>
                            <i>No Business Hours Specified</i>
                        </p>
                    )}

                </p>
            </div>
            <div>
                <h1>Subscription Plans</h1>
                <p>
                    {subscriptionPlans.map((subPlan) => (
                        <Link to="/subscribe" state={{data: {subPlan}}}>
                            <dl>
                                <dt>{subPlan.name}</dt>
                                <dd>{subPlan.description}</dd>
                                <dd>{subPlan.durationInDays}</dd>
                                <dd>{subPlan.price}</dd>
                            </dl>
                        </Link>
                    ))}

                </p>
            </div>
            <div>
                Average rating : {averageRating}
                {reviews.map((review) => (
                    <dl>
                        <dt>{review.rating}</dt>
                        <dd>{review.comment}</dd>
                        <dd>{review.customer.firstName}</dd>
                    </dl>
                ))}
            </div>
        </div>
    );
}
