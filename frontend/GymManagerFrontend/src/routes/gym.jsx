import {Link, useLoaderData} from "react-router-dom";


export async function loader({ params }) {
    let gym;
    let [subscriptionPlans] = [];
    const gymId = params.gymId;
    await fetch('http://localhost:8081/gyms/' + gymId).then((res) => res.json())
        .then((data) => {
            gym = data;
        });
    await fetch('http://localhost:8081/subscriptionplans/findByGym/' + gymId).then((res) => res.json())
        .then((data) => {
            subscriptionPlans = data;
        });
    return [gym, subscriptionPlans];
}
export default function Gym() {
    const data = useLoaderData();
    const gym = data[0];
     const [subscriptionPlans] = [data[1]];
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
                <p>{gym.address.zipcode}</p>
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
        </div>
    );
}