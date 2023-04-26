import {Link, Outlet} from "react-router-dom";
import {useLoaderData} from "react-router-dom";

export async function loader() {
    const headers = {'Content-Type':'application/json',
        'Access-Control-Allow-Origin':'*',
        'Access-Control-Allow-Methods':'POST,PATCH,OPTIONS,GET',}
    const results = await fetch('http://localhost:8081/gyms/',
        { headers: { Authorization:localStorage.getItem('SavedToken') }})
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
