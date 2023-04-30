import {Link, Outlet, useLoaderData} from "react-router-dom";

export async function loader() {
    const results = await fetch('http://localhost:8081/gyms/')
    if (!results.ok) throw new Error('Something went wrong!');
    return await results.json();
}

export default function Gyms() {
    const data = useLoaderData();
    return (
        <>
            <div>
                {data.length ? (
                    <ul>
                        {data.map((gym) => (
                            <li key={gym.id}>
                                <Link to={`/gyms/${gym.id}`}>
                                    <p>
                                        <img src={gym.avatarImgPath}/>
                                    </p>
                                    {gym.name ? (
                                        <>
                                            {gym.name}
                                        </>
                                    ) : (
                                        <i>No Name</i>
                                    )}{" "}
                                </Link>
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
                        <i>No gyms</i>
                    </p>
                )}
            </div>
            <div id="detail">
                <Outlet/>
            </div>
        </>
    );
}
