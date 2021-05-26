import axios from "axios";
import { useEffect, useState } from "react";
import { SalePage } from "types/sale";
import { BASE_URL } from "utils/requests";

function DataTable() {

    const [page, setPage] = useState<SalePage>({
        first: true,
        last: true,
        number: 0,
        totalElements: 0,
        totalPages: 0
    });

    useEffect(() => {
        axios.get(`${BASE_URL}/sales?page=0&size=20&sort=date,desc`).then(response => {
            setPage(response.data);
        });
    }, []);

    return (
        <div className="table-responsive">
            <table className="table table-striped table-sm">
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Seller</th>
                        <th>Customers visited</th>
                        <th>Closed deals</th>
                        <th>Value</th>
                    </tr>
                </thead>
                <tbody>
                    {page.content?.map(item => (
                        <tr>
                            <td>{item.date}</td>
                            <td>{item.seller.name}</td>
                            <td>{item.visited}</td>
                            <td>{item.deals}</td>
                            <td>{item.amount.toFixed(2)}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default DataTable;