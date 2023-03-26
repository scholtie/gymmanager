import {useLoaderData} from "react-router-dom";


export async function loader({ params }) {
    const results = await fetch('http://localhost:8081/gyms/' + params.gymId)

    if (!results.ok) throw new Error('Something went wrong!');

    return await results.json();
}
export default function Gym() {
    const gym = useLoaderData();

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
        </div>
    );
}