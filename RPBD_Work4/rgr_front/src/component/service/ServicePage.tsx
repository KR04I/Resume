import {Services} from "../../api/base";
import {serviceApi} from "../../api/ServiceApi";
import {useEntities} from "../../util/useEntities";
import {ServiceForm} from "./ServiceForm";
import {useState} from "react";
import {ServicesCard} from "./ServiceCard";


export const ServicePage: React.FC = () => {
    const [services, _, refresh] = useEntities(serviceApi)
    const [addFormShow, setAddFormShow] = useState(false)

    const onAddSubmit = (client: Services) => {
        serviceApi.create(client).finally(refresh)
        setAddFormShow(false)
    }

    const onEdit = (id?: number, client?: Services) => {
        if (!id || !client) return
        serviceApi.edit(id, client).finally(refresh)
    }

    const onDelete = (id?: number) => {
        if (!id) return
        serviceApi.delete(id).finally(refresh)
    }

    return (
        <div className="service-page">
            <div className="card">
                <button className="button" onClick={() => setAddFormShow(!addFormShow)}>{`${addFormShow ? 'Закрыть' : 'Добавить'}`}</button>
                {addFormShow &&
                    <ServiceForm onSubmit={onAddSubmit} />
                }
            </div>
            <div>
                {services?.map(c => <ServicesCard key={c.id} service={c} onEdit={onEdit} onDelete={onDelete} />)}
            </div>
        </div>
    )
}