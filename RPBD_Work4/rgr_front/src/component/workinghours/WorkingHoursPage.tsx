import {workinghoursApi} from "../../api/WorkingHoursApi";
import {useEntities} from "../../util/useEntities";
import {WorkingHours} from "../../api/base";
import {useState} from "react";
import {WorkingHoursForm} from "./WorkingHoursForm";
import {WorkingHoursCard} from "./WorkingHoursCard";
import { BaseApi } from "../../api/BaseApi";



export const WorkingHoursPage: React.FC = () => {
    let workingHoursApi: BaseApi<unknown>;
    const [workingHours, _, refresh] = useEntities(workinghoursApi)
    const [addFormShow, setAddFormShow] = useState(false)

    const onAddSubmit = (client: WorkingHours) => {
        workingHoursApi.create(client).finally(refresh)
        setAddFormShow(false)
    }

    const onEdit = (id?: number, client?: WorkingHours) => {
        if (!id || !client) return
        workingHoursApi.edit(id, client).finally(refresh)
    }

    const onDelete = (id?: number) => {
        if (!id) return
        workingHoursApi.delete(id).finally(refresh)
    }

    return (
        <div className="workingHours-page">
            <div className="card">
                <button className="button" onClick={() => setAddFormShow(!addFormShow)}>{`${addFormShow ? 'Закрыть' : 'Добавить'}`}</button>
                {addFormShow &&
                    <WorkingHoursForm onSubmit={onAddSubmit} />
                }
            </div>
            <div>
                {workingHours?.map(c => <WorkingHoursCard key={c.id} workingHours={c} onEdit={onEdit} onDelete={onDelete} />)}
            </div>
        </div>
    )
}