import axios from "axios";
import {useState} from 'react';

export default function CallApi() {
    const [data, setData] = useState([]);
    const getData = () => {
        axios.get('http://localhost:8081/customers/')
            .then(res => {
                setData(res.data)
            }).catch(err => {
                console.log(err)
        })
    }
    return (
        <div className = "CallApi">
            <button onClick={getData}>Get Data</button>
            <div>
                {data.map((d) => (
                    <p>{d.email}</p>
                ))}
            </div>
        </div>
    );
}