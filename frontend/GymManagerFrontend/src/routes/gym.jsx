import {Link, useLoaderData} from "react-router-dom";
import { Map, Marker } from "pigeon-maps"


export async function loader({ params }) {
    let gym;
    let average;
    let [reviews] = []
    let [subscriptionPlans] = [];
    const gymId = params.gymId;
    const config = {
        headers: {
            Authorization:localStorage.getItem('SavedToken')
        }
    }
    await fetch('http://localhost:8081/gyms/' + gymId, config)
        .then((res) => res.json())
        .then((data) => {
            gym = data;
        });
    await fetch('http://localhost:8081/review/gym/' + gymId, config)
        .then((res) => res.json())
        .then((data) => {
            reviews = data;
        });
    await fetch('http://localhost:8081/subscriptionplans/findByGym/' + gymId, config)
        .then((res) => res.json())
        .then((data) => {
            subscriptionPlans = data;
        });
    // await fetch('http://localhost:8081/review/gym/' + gymId + '/average', config)
    //     .then((res) => res.json())
    //     .then((data) => {
    //         average = data;
    //     });
    return [gym, subscriptionPlans, reviews, average];
}
export default function Gym() {
    const data = useLoaderData();
    const gym = data[0];
     const [subscriptionPlans] = [data[1]];
     const [reviews] = [data[2]];
     const averageRating = data[3];
     console.log(gym, subscriptionPlans, averageRating, reviews)
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
                    <Marker width={50} anchor={[gym.address.geo.lat, gym.address.geo.lng]} />
                </Map>
                <p>{gym.address.street}</p>
                <p>{gym.address.suite}</p>
                <p>{gym.about}</p>
                </div>
            <div>
                <h1>Subscription Plans</h1>
                <p>
                        {subscriptionPlans.map((subPlan) => (
                            <Link to="/subscribe" state={{ data: {subPlan} }} >
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
