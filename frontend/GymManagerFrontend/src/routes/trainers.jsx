import {Link, Outlet} from "react-router-dom";
import {useLoaderData} from "react-router-dom";

export async function loader() {
    const results = await fetch('http://localhost:8081/trainers/')

    if (!results.ok) throw new Error('Something went wrong!');

    return await results.json();
}

export default function Trainers() {
    const data = useLoaderData();
    return (
        <>
            <div>
                    {data.length ? (
                        <ul>
                            {data.map((trainer) => (
                                <li key={trainer.id}>
                                    <p>
                                        <img src={trainer.imgPath}/>
                                    </p>
                                    <Link to={`/trainer/${trainer.id}`}>
                                        {trainer.firstName || trainer.lastName ? (
                                            <>
                                                {trainer.firstName} {trainer.lastName}

                                            </>
                                        ) : (
                                            <i>No Name</i>
                                        )}{" "}
                                    </Link>
                                    <p>{trainer.introduction}</p>
                                    <p>{trainer.rating ? (trainer.rating) : <i>{trainer.firstName} {trainer.lastName} has no reviews yet.</i>}</p>
                                    <Link to="/booksession" state={{ data: {trainer} }} >
                                        Book Session
                                    </Link>
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